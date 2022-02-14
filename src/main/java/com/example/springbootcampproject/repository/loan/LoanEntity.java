package com.example.springbootcampproject.repository.loan;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "loan")
@Table(name = "loan")
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long tcNo;

    @Column(nullable = false)
    private Integer income;

    @Column(nullable = false)
    private Integer creditScore;

    @Column(nullable = false)
    private boolean isApproved;

    @Column(nullable = false)
    private Long amountOfLoan;
}
