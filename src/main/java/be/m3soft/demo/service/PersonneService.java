package be.m3soft.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import be.m3soft.demo.model.Personne;
import be.m3soft.demo.repository.PersonnesRepository;

@Service
public class PersonneService {

    private final PersonnesRepository personnesRepository;

    public PersonneService(PersonnesRepository personnesRepository) {
        this.personnesRepository = personnesRepository;
    }

    public Personne create(Personne personne) {
        personne.setId(UUID.randomUUID());
        return personnesRepository.save(personne);
    }

    public List<Personne> getAll() {
        return personnesRepository.findAll();
    }

    public Personne update(UUID id, Personne personne) {
        Personne updatedPersonne = personnesRepository.findById(id).orElseThrow();
        updatedPersonne.setNom(personne.getNom());
        updatedPersonne.setPrenom(personne.getPrenom());
        return personnesRepository.save(updatedPersonne);
    }

    public void delete(UUID id) {
        Personne toBeUpdated = personnesRepository.findById(id).orElseThrow();
        personnesRepository.delete(toBeUpdated);
    }

}
