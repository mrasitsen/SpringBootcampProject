package com.example.springbootcampproject.controller.loan;

import com.example.springbootcampproject.service.loan.Loan;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class RetrieveLoanResponse {

    private Long id;
    private Long tcNo;
    private Integer income;
    private Integer creditScore;
    private boolean isApproved;
    private Long amountOfLoan;

    public static RetrieveLoanResponse createLoanResponse(Loan loan){
        return RetrieveLoanResponse.builder()
                .id(loan.getId())
                .tcNo(loan.getTcNo())
                .income(loan.getIncome())
                .creditScore(loan.getCreditScore())
                .isApproved(loan.isApproved())
                .amountOfLoan(loan.getAmountOfLoan())
                .build();
    }

    public static List<RetrieveLoanResponse> createLoanResponses(List<Loan> loans){
        return loans.stream()
                .map(RetrieveLoanResponse::createLoanResponse)
                .collect(Collectors.toList());
    }
}
