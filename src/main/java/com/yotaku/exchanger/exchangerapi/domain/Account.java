package com.yotaku.exchanger.exchangerapi.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import static com.yotaku.exchanger.exchangerapi.domain.User.TABLE_NAME;

import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name = TABLE_NAME)
public class Account {
    public static final String TABLE_NAME = "accounts";

    public static class Properties {
        public static final String ID = "id";
        public static final String USER = "user";
        public static final String TRANSACTIONS = "transactions";
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = User.Properties.ID)
    @NotNull
    @JsonBackReference
    private User user;

    @OneToMany(
            mappedBy = ExchangeTransaction.Properties.ACCOUNT,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @NotEmpty
    @UniqueElements
    @JsonManagedReference
    private List<@Valid ExchangeTransaction> transactions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(@Valid User user) {
        this.user = user;
    }

    public List<ExchangeTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(@NotEmpty @UniqueElements List<@Valid ExchangeTransaction> transactions) {
        this.transactions = transactions;
    }

}
