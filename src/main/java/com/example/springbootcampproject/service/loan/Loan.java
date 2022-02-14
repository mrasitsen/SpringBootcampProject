package com.example.springbootcampproject.service.loan;

import com.example.springbootcampproject.repository.loan.LoanEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class Loan {

    private Long id;
    private Long tcNo;
    private Integer income;
    private Integer creditScore;
    private boolean isApproved;
    private Long amountOfLoan;

    public static Loan convertFromLoanEntity(LoanEntity loanEntity){
        return Loan.builder()
                .id(loanEntity.getId())
                .tcNo(loanEntity.getTcNo())
                .income(loanEntity.getIncome())
                .creditScore(loanEntity.getCreditScore())
                .isApproved(loanEntity.isApproved())
                .amountOfLoan(loanEntity.getAmountOfLoan())
                .build();
    }

    public static List<Loan> convertFromLoanEntities(List<LoanEntity> loanEntities){
        return loanEntities.stream()
                .map(Loan::convertFromLoanEntity)
                .collect(Collectors.toList());
    }
}
