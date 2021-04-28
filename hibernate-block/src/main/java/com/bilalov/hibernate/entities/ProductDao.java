package com.bilalov.hibernate.entities;

import org.hibernate.Session;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDao {

    public ProductDao() {
    }

    public List<String> getCostumers(Long id) {
        SessionFactoryClass factory = new SessionFactoryClass();
        Session session = factory.getFactory().getCurrentSession();
        session.beginTransaction();

        Product product = session.get(Product.class, id);

        List<String> costumers = session.createQuery("SELECT id FROM Product WHERE customer_id = " + id).getResultList();
        List<String> customersNames = new ArrayList<>();

        for (int i = 0; i < costumers.size(); i++) {
            customersNames.add(product.customer);
        }

        session.getTransaction().commit();
        return customersNames;
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
