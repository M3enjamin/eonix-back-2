package be.m3soft.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import be.m3soft.demo.model.Person;
import jakarta.annotation.Nullable;

public interface PersonsRepository extends JpaRepository<Person, UUID>, JpaSpecificationExecutor<Person> {
    @Query("SELECT p FROM Person p "
            + "WHERE ("
            + "LOWER(p.firstName) LIKE LOWER(CONCAT(:firstNameFilter, '%')) "
            + "OR LOWER(p.firstName) LIKE LOWER(CONCAT('%', :firstNameFilter))"
            + ") "
            + "AND ("
            + "LOWER(p.lastName) LIKE LOWER(CONCAT(:lastNameFilter, '%')) "
            + "OR LOWER(p.lastName) LIKE LOWER(CONCAT('%',:lastNameFilter))"
            + ")")
    List<Person> findByByFirstNameAndLastName(String firstNameFilter, @Nullable String lastNameFilter);
}