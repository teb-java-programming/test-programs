package com.teb.practice.service;

import com.teb.practice.model.Customer;
import com.teb.practice.model.Product;
import com.teb.practice.model.Stock;
import com.teb.practice.util.PriceUtil;

public class PurchaseService {

    private static final PriceUtil priceUtil = new PriceUtil();

    public double purchase(Customer customer, Product product, Stock stock) {

        validateStock(stock);

        double finalPrice = calculateFinalPrice(product);

        validateBalance(customer, finalPrice);

        applyTransaction(customer, stock, finalPrice);

        return finalPrice;
    }

    private void validateStock(Stock stock) {

        if (stock.getQuantity() <= 0) {
            throw new IllegalStateException("Product out of stock");
        }
    }

    private double calculateFinalPrice(Product product) {

        return priceUtil.applyDiscount(product.price());
    }

    private void validateBalance(Customer customer, double finalPrice) {

        if (customer.getBalance() < finalPrice) {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }

    private void applyTransaction(Customer customer, Stock stock, double finalPrice) {

        customer.deduct(finalPrice);
        stock.setQuantity(stock.getQuantity() - 1);
    }
}
