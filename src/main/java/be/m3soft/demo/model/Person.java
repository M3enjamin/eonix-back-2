package be.m3soft.demo.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    private UUID id;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String firstName;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String lastName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String prenom) {
        firstName = prenom;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String nom) {
        lastName = nom;
    }

}
