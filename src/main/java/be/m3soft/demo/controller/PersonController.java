package be.m3soft.demo.controller;

import java.util.List;
import java.util.UUID;

import org.apache.el.stream.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.m3soft.demo.model.Person;
import be.m3soft.demo.service.PersonService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personneService;

    public PersonController(PersonService personneService) {
        this.personneService = personneService;
    }

    @GetMapping
    public List<Person> findPersons(@RequestParam(required = false) String firstNameFilter, @RequestParam(required = false) String lastNameFilter) {
        return personneService.findPersonsByFirstNameAndLastName(firstNameFilter, lastNameFilter);
    }

    @PostMapping
    public Person create(@RequestBody Person personne) {
        return personneService.create(personne);
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable UUID id, @RequestBody @Valid Person personne) {
        return personneService.update(id, personne);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        personneService.delete(id);
    }
}
