package com.bilalov.javaspringbootlessonfour.repositories;

import com.bilalov.javaspringbootlessonfour.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Long>,
        PagingAndSortingRepository<Item, Long> {

    Item findByTitle(String title);
    List<Item> findByCostBetween(int min, int max);
}