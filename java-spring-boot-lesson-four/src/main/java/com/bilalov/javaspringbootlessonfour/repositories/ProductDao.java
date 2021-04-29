package com.bilalov.javaspringbootlessonfour.repositories;

import com.bilalov.javaspringbootlessonfour.entities.Product;
import org.hibernate.cfg.Configuration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class ProductDao /*implements Closeable*/ {

//    private static EntityManagerFactory factory;
//    private static EntityManager em;
//
//    public static void main(String[] args) {
//        factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Product.class)
//                .buildSessionFactory();
//
//        em = factory.createEntityManager();
//
//        Product product1 = new Product("IphoneX", "Expensive phone", new BigDecimal(3000));
//        Product product2 = new Product("Iphone11pro", "Very expensive phone", new BigDecimal(5000));
//        Product product3 = new Product("Iphone12", "Very very expensive phone", new BigDecimal(8000));
//        add(product1);
//        add(product2);
//        add(product3);                                          // add
//        Product productSearch = findById(1L);                   // findById
//        System.out.println(productSearch + "product");
//        System.out.println(findAll());                          // findAll
//        deleteById(1L);                                         //deleteById
//        saveOrUpdate(product2);                                 //saveOrUpdate
//    }
//
//    public static void add(Product product) {
//        em.getTransaction().begin();
//        em.persist(product);
//        em.getTransaction().commit();
//    }
//
//    public Product findById(Long id) {
//        em.getTransaction().begin();
//        Product product = em
//                .createQuery("SELECT p from Product p WHERE p.id = " + id, Product.class)
//                .getSingleResult();
//        em.getTransaction().commit();
//        return product;
//    }
//
//    public List<Product> findAll() {
//        em.getTransaction().begin();
//        List<Product> list = em.createQuery("SELECT p from Product p").getResultList();
//        em.getTransaction().commit();
//        return list;
//    }
//
//    public void deleteById(Long id) {
//        em.getTransaction().begin();
//        em.createQuery("DELETE from Product p WHERE p.id =" + id).executeUpdate();
//        em.getTransaction().commit();
//    }
//
//    public void saveOrUpdate(Product product) {
//        em.getTransaction().begin();
//        em.createQuery("UPDATE Product p set " +
//                "p.title=" + "'" + product.getTitle() + "', "
//                + "p.description=" + "'" + product.getDescription() + "' ,"
//                + " p.price=" + product.getPrice()
//                + " WHERE id = " + product.getId())
//                .executeUpdate();
//        em.getTransaction().commit();
//    }
//
//    @Override
//    public void close() throws IOException {
//        em.close();
//        factory.close();
//    }
}