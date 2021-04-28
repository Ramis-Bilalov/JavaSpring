package com.bilalov.hibernate.entities;

import org.hibernate.Session;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerDao {


    public CustomerDao() {

    }

    public List<String> getProducts(Long id) {
        SessionFactoryClass factory = new SessionFactoryClass();
        Session session = factory.getFactory().getCurrentSession();
        session.beginTransaction();

        Customer customer = session.get(Customer.class, id);

        List<String> products = session.createQuery("SELECT id FROM Product WHERE customer_id = " + id).getResultList();
        List<String> productsTitles = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            productsTitles.add(customer.product);
        }

        session.getTransaction().commit();
        return productsTitles;
    }

    public void createAndFill() throws IOException {
        SessionFactoryClass factoryClass = new SessionFactoryClass();
        Session session = factoryClass.getFactory().getCurrentSession();
        String sql = Files.lines(Paths.get("hibernate-block/create-and-fill.sql")).collect(Collectors.joining(" "));
        session.beginTransaction();
        session.createNativeQuery(sql).executeUpdate();
        session.getTransaction().commit();
    }
}
