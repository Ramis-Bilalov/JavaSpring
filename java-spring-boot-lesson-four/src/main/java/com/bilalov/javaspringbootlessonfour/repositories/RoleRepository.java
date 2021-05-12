package com.bilalov.javaspringbootlessonfour.repositories;

import com.bilalov.javaspringbootlessonfour.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
