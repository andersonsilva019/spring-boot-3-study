package com.example.loans.models;

import com.example.loans.enumeration.LoansType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "loans")
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    @Column
    private LoansType type;

    @Column(name = "interest_rate")
    private float interestRate;

    public Loans(LoansType type, float interestRate) {
        this.type = type;
        this.interestRate = interestRate;
    }
}
