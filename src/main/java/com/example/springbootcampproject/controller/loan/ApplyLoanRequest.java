package com.example.springbootcampproject.controller.loan;

import com.example.springbootcampproject.service.loan.Loan;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class ApplyLoanRequest {

    @NotNull
    private Long id;

    @NotNull
    private Long tcNo;

    public Loan convertToLoan(){
        return Loan.builder()
                .id(id)
                .tcNo(tcNo)
                .build();
    }
}
