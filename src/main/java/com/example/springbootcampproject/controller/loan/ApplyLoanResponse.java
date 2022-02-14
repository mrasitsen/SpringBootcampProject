package com.example.springbootcampproject.controller.loan;

import com.example.springbootcampproject.service.loan.Loan;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplyLoanResponse {

    private Long id;
    private Long tcNo;
    private Integer income;
    private Integer creditScore;
    private boolean isApproved;
    private Long amountOfLoan;

    public static ApplyLoanResponse createResponse(Loan loan){
        return ApplyLoanResponse.builder()
                .id(loan.getId())
                .tcNo(loan.getTcNo())
                .income(loan.getIncome())
                .creditScore(loan.getCreditScore())
                .isApproved(loan.isApproved())
                .amountOfLoan(loan.getAmountOfLoan())
                .build();
    }
}
