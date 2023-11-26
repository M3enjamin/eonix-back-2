package be.m3soft.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.m3soft.demo.model.Personne;
import be.m3soft.demo.service.PersonneService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/personnes")
public class PersonneController {

    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping
    public List<Personne> getAll() {
        return personneService.getAll();
    }

    @PostMapping
    public Personne create(@RequestBody Personne personne) {
        return personneService.create(personne);
    }

    @PutMapping("/{id}")
    public Personne update(@PathVariable UUID id, @RequestBody @Valid Personne personne) {
        return personneService.update(id, personne);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        personneService.delete(id);
    }
}
