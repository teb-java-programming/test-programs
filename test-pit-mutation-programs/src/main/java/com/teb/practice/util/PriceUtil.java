package com.teb.practice.util;

public class PriceUtil {

    public double applyDiscount(double price) {

        if (price > 100) {
            return price * 0.8;
        }

        return price;
    }
}
