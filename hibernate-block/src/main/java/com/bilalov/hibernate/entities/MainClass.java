package com.bilalov.hibernate.entities;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        try {
            productDao.createAndFill();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CustomerDao customerDao = new CustomerDao();
        try {
            customerDao.createAndFill();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(productDao.getCostumers(1L));
        System.out.println(customerDao.getProducts(3L));


    }
}
