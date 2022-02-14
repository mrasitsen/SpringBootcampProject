package com.example.springbootcampproject.service.loan;

import com.example.springbootcampproject.repository.loan.LoanDao;
import com.example.springbootcampproject.repository.loan.LoanEntity;
import com.example.springbootcampproject.repository.user.UserDao;
import com.example.springbootcampproject.repository.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoanServiceImp implements LoanService{

    private final UserDao userDao;
    private final LoanDao loanDao;
    private final int KREDI_LIMIT_CARPANI = 4;

    @Override
    public Loan applyLoan(Loan loan) {
        UserEntity userEntity = userDao.retrieveUser(loan.getId());
        Optional<LoanEntity> loanEntity = loanDao.retrieveLoanByTcAndIncome(loan.getTcNo(), userEntity.getIncome());

        if(!loanEntity.isPresent()){
            //There is no record for this TC and this income level, so this will be evaluated again
            LoanEntity newLoanEntity = new LoanEntity();
            newLoanEntity.setTcNo(loan.getTcNo());
            newLoanEntity.setIncome(userEntity.getIncome());

            //Get credit score: Since there is no database for this, this number will be produced as random
            //This number vary from 0 to 1500
            Random random = new Random();
            Integer creditScore =  random.nextInt(1501);
            newLoanEntity.setCreditScore(creditScore);

            //Can have Loan ?
            boolean isApproved = creditScore >= 500;
            newLoanEntity.setApproved(isApproved);

            //calculate amount
            if(!isApproved){
                newLoanEntity.setAmountOfLoan(0L);
            }else{
                Long amountOfLoan = calculateAmountOfLoan(userEntity.getIncome(), creditScore);
                newLoanEntity.setAmountOfLoan(amountOfLoan);
            }

            //save to DB
            LoanEntity appliedLoanEntity = loanDao.applyLoan(newLoanEntity);

            //return result
            return Loan.convertFromLoanEntity(appliedLoanEntity);
        }else{
            //There is already an application for Loan
            return Loan.convertFromLoanEntity(loanEntity.get());
        }
    }



    private Long calculateAmountOfLoan(Integer income, Integer creditScore){
        if(creditScore < 1000 && income < 5000){
            return 10000L;
        }
        else if(creditScore < 1000){
            return 20000L;
        }
        else{
            return (long) KREDI_LIMIT_CARPANI * income;
        }

    }

    @Override
    public List<Loan> retrieveLoan(Long tcNo) {
        List<LoanEntity> loanEntities = loanDao.retrieveLoan(tcNo);
        List<Loan> loans = Loan.convertFromLoanEntities(loanEntities);
        return loans;
    }
}
