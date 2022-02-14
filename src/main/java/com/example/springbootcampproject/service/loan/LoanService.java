package com.example.springbootcampproject.service.loan;

import java.util.List;

public interface LoanService {

    Loan applyLoan(Loan loan);

    List<Loan> retrieveLoan(Long tcNo);
}
