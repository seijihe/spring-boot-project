package com.seijihe.rest_with_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seijihe.rest_with_spring_boot.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}

