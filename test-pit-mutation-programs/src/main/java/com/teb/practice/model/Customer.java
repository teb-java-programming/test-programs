package com.teb.practice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Customer {

    String id;
    String name;
    double balance;

    public void deduct(double amount) {

        this.balance -= amount;
    }
}
