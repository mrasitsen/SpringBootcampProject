package com.example.springbootcampproject.controller;

import com.example.springbootcampproject.controller.loan.ApplyLoanRequest;
import com.example.springbootcampproject.controller.loan.ApplyLoanResponse;
import com.example.springbootcampproject.controller.loan.RetrieveLoanResponse;
import com.example.springbootcampproject.service.loan.Loan;
import com.example.springbootcampproject.service.loan.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping(value = "/apply")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplyLoanResponse applyLoan(@RequestBody @Valid ApplyLoanRequest applyLoanRequest){
        Loan requestLoan = applyLoanRequest.convertToLoan();
        Loan responseLoan = loanService.applyLoan(requestLoan);
        return ApplyLoanResponse.createResponse(responseLoan);
    }

    @GetMapping(value = "/apply/{userTcNo}")
    @ResponseStatus(HttpStatus.OK)
    public List<RetrieveLoanResponse> retrieveLoan(@PathVariable Long userTcNo){
        List<Loan> loans = loanService.retrieveLoan(userTcNo);
        return RetrieveLoanResponse.createLoanResponses(loans);
    }
}
