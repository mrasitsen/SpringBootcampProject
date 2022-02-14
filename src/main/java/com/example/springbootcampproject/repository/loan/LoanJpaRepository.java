package com.example.springbootcampproject.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanJpaRepository extends JpaRepository<LoanEntity, Long> {

    LoanEntity findByTcNoAndIncome(Long tcNo, Integer income);

    List<LoanEntity> findAllByTcNo(Long tcNo);
}
