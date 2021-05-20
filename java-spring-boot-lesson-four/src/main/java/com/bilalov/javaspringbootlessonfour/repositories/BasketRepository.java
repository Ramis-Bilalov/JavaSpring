package com.bilalov.javaspringbootlessonfour.repositories;

import com.bilalov.javaspringbootlessonfour.entities.Basket;
import com.bilalov.javaspringbootlessonfour.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long>, JpaSpecificationExecutor<Basket> {
}