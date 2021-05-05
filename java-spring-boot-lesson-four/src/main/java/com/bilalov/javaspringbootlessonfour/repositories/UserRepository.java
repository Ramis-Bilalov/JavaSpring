package com.bilalov.javaspringbootlessonfour.repositories;

import com.bilalov.javaspringbootlessonfour.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
