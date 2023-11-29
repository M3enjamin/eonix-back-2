package be.m3soft.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.m3soft.demo.controller.PersonController;
import be.m3soft.demo.model.Person;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private PersonController personController;

	@Test
	void test_FilterOnFirstNameOnly_ShouldPass() {
		String searchCriterion = "ma";
		List<Person> persons = personController.findPersons(searchCriterion,
				null);
		assertEquals(3, persons.size());

		List<Person> intruder = persons.stream()
				.filter(p -> {
					String value = p.getFirstName().toLowerCase();
					return !value.startsWith(searchCriterion.toLowerCase())
							&& !value.endsWith(searchCriterion.toLowerCase());
				})
				.toList();

		assertEquals(intruder.size(), 0);
	}

	@Test
	void test_FilterOnlastNameOnly_ShouldPass() {
		String searchCriterion = "br";
		List<Person> persons = personController.findPersons(null,
				searchCriterion);
		assertEquals(2, persons.size());

		List<Person> intruder = persons.stream()
				.filter(p -> {
					String value = p.getLastName().toLowerCase();
					return !value.startsWith(searchCriterion.toLowerCase())
							&& !value.endsWith(searchCriterion.toLowerCase());
				})
				.toList();

		assertEquals(intruder.size(), 0);
	}

	@Test
	void test_FilterOnlastNameAndFirstName_ShouldPass() {
		String firstNameCriterion = "ma";
		String lastNameCriterion = "br";
		List<Person> persons = personController.findPersons(firstNameCriterion,
				lastNameCriterion);
		assertEquals(1, persons.size());

		List<Person> intruder = persons.stream()
				.filter(p -> {
					String firstName = p.getFirstName().toLowerCase();
					String lastName = p.getLastName().toLowerCase();
					return !firstName.startsWith(firstNameCriterion.toLowerCase())
							&& !firstName.endsWith(firstNameCriterion.toLowerCase())
							&& !lastName.startsWith(lastNameCriterion.toLowerCase())
							&& !lastName.endsWith(lastNameCriterion.toLowerCase());
				})
				.toList();

		assertEquals(intruder.size(), 0);
	}
}
