package ru.kichenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.kichenko.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
