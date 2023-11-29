package be.m3soft.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import be.m3soft.demo.model.Person;
import be.m3soft.demo.model.Person_;
import be.m3soft.demo.repository.PersonsRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class PersonService {

    private final PersonsRepository personnesRepository;

    public PersonService(PersonsRepository personnesRepository) {
        this.personnesRepository = personnesRepository;
    }

    public Person create(Person personne) {
        personne.setId(UUID.randomUUID());
        return personnesRepository.save(personne);
    }

    public List<Person> findPersonsByFirstNameAndLastName(String firstNameFilter, String lastNameFilter) {
        Specification<Person> specification = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                Predicate firstNameStartsOrEndsWith;
                Predicate lastNameStartsOrEndsWith;

                if (firstNameFilter != null) {
                    firstNameStartsOrEndsWith = criteriaBuilder.or(
                            criteriaBuilder
                                    .like(criteriaBuilder.lower(root.get(Person_.firstName)),
                                            firstNameFilter.toLowerCase() + "%"),
                            criteriaBuilder
                                    .like(criteriaBuilder.lower(root.get(Person_.firstName)),
                                            "%" + firstNameFilter.toLowerCase()));
                    predicates.add(firstNameStartsOrEndsWith);
                }

                if (lastNameFilter != null) {
                    lastNameStartsOrEndsWith = criteriaBuilder.or(
                            criteriaBuilder
                                    .like(criteriaBuilder.lower(root.get(Person_.lastName)),
                                            lastNameFilter.toLowerCase() + "%"),
                            criteriaBuilder
                                    .like(criteriaBuilder.lower(root.get(Person_.lastName)),
                                            "%" + lastNameFilter.toLowerCase()));
                    predicates.add(lastNameStartsOrEndsWith);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };

        return personnesRepository.findAll(specification);
    }

    public Person update(UUID id, Person personne) {
        Person updatedPersonne = personnesRepository.findById(id).orElseThrow();
        updatedPersonne.setLastName(personne.getLastName());
        updatedPersonne.setFirstName(personne.getFirstName());
        return personnesRepository.save(updatedPersonne);
    }

    public void delete(UUID id) {
        Person toBeUpdated = personnesRepository.findById(id).orElseThrow();
        personnesRepository.delete(toBeUpdated);
    }

}
