package be.m3soft.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import be.m3soft.demo.model.Personne;

public interface PersonnesRepository extends JpaRepository<Personne, UUID> {
}