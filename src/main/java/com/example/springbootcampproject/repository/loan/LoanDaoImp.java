package com.example.springbootcampproject.repository.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanDaoImp implements LoanDao{

    private final LoanJpaRepository loanJpaRepository;


    @Override
    public Optional<LoanEntity> retrieveLoanByTcAndIncome(Long tcNo, Integer income) {
        Optional<LoanEntity> loanEntity = Optional.ofNullable(loanJpaRepository.findByTcNoAndIncome(tcNo, income));
        return loanEntity;
    }

    @Override
    public LoanEntity applyLoan(LoanEntity loanEntity) {
        LoanEntity appliedLoanEntity = loanJpaRepository.save(loanEntity);
        return appliedLoanEntity;
    }

    @Override
    public List<LoanEntity> retrieveLoan(Long tcNo) {
        List<LoanEntity> loanEntities = loanJpaRepository.findAllByTcNo(tcNo);
        return loanEntities;
    }
}
