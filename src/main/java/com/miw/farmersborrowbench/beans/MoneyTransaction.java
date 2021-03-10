package com.miw.farmersborrowbench.beans;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class MoneyTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private int amount;

    private String description;

    @ManyToOne
    private Account debitAccount;

    @ManyToOne
    private Account creditAccount;
}
