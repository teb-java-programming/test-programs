package com.teb.practice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.teb.practice.model.Customer;
import com.teb.practice.model.Product;
import com.teb.practice.model.Stock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PurchaseServiceTest {

    private final PurchaseService service = new PurchaseService();

    static Stream<Arguments> priceProvider() {
        return Stream.of(
                Arguments.of("Robin", 200, "Book", 40, 40, 160, 10, 9),
                Arguments.of("Tom", 200, "Laptop", 200, 160, 40, 4, 3),
                Arguments.of("Meryl", 200, "Camera", 100, 100, 100, 8, 7),
                Arguments.of("Robert", 100, "Phone", 100, 100, 0, 2, 1));
    }

    @ParameterizedTest
    @MethodSource("priceProvider")
    void testDeductsBalanceWhenPurchaseSucceeds(
            String customerName,
            double balance,
            String productName,
            double price,
            double finalPrice,
            double updatedBalance,
            int quantity,
            int updatedStock) {

        Customer customer = new Customer("CXX", customerName, balance);
        Product product = new Product("PXX", productName, price);
        Stock stock = new Stock(productName, quantity);

        assertEquals(finalPrice, service.purchase(customer, product, stock));
        assertEquals(updatedBalance, customer.getBalance());
        assertEquals(updatedStock, stock.getQuantity());
    }

    @Test
    void testThrowsExceptionWhenOutOfStock() {

        Customer customer = new Customer("C04", "Jim", 200);
        Product product = new Product("P04", "Phone", 80);
        Stock stock = new Stock("Tablet", 0);

        Exception e =
                assertThrows(
                        RuntimeException.class, () -> service.purchase(customer, product, stock));
        assertInstanceOf(IllegalStateException.class, e);
        assertTrue(e.getMessage().contains("Product out of stock"));
        assertEquals("C04", customer.getId());
        assertEquals("Jim", customer.getName());
    }

    @Test
    void testThrowsExceptionWhenBalanceIsInsufficient() {

        Customer customer = new Customer("C08", "Clint", 40);
        Product product = new Product("P08", "Tablet", 80);
        Stock stock = new Stock("Phone", 2);

        Exception e =
                assertThrows(
                        RuntimeException.class, () -> service.purchase(customer, product, stock));
        assertInstanceOf(IllegalArgumentException.class, e);
        assertTrue(e.getMessage().contains("Insufficient balance"));
        assertEquals(2, stock.getQuantity());
    }
}
