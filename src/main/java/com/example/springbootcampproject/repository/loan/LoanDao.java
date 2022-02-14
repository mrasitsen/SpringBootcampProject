package com.example.springbootcampproject.repository.loan;

import java.util.List;
import java.util.Optional;

public interface LoanDao {

    Optional<LoanEntity> retrieveLoanByTcAndIncome(Long tcNo, Integer income);

    LoanEntity applyLoan(LoanEntity loanEntity);

    List<LoanEntity> retrieveLoan(Long tcNo);
}
