package solution;


import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;
import com.betrybe.agrix.services.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class PersonServiceTest {
  @Autowired
  private PersonService personService;
  private Person person;

  @BeforeAll
  public void createPerson() {
    person = new Person();
    person.setUsername("user");
    person.setPassword("user123");
    person.setRole(Role.ADMIN);
  }

  @Test
  @DisplayName("Se cria uma nova pessoa")
  public void createPersonTest() {
    Person newPerson = personService.create(person);

    Assertions.assertEquals(newPerson.getId(), person.getId());
    Assertions.assertEquals(newPerson.getUsername(), person.getUsername());
    Assertions.assertEquals(newPerson.getPassword(), person.getPassword());
    Assertions.assertEquals(newPerson.getRole(), person.getRole());
  }

  @Test
  @DisplayName("Se busca uma pessoa por Username")
  public void personByUsernameTest() {
    String personUsername = personService.create(person).getUsername();
    Person newPerson = personService.getPersonByUsername(personUsername);

    Assertions.assertEquals(newPerson.getId(), person.getId());
    Assertions.assertEquals(newPerson.getUsername(), person.getUsername());
    Assertions.assertEquals(newPerson.getPassword(), person.getPassword());
    Assertions.assertEquals(newPerson.getRole(), person.getRole());
  }

  @Test
  @DisplayName("Se busca uma pessoa por Id")
  public void testPersonById() {
    Long personId = personService.create(person).getId();
    Person newPerson = personService.getPersonById(personId);

    Assertions.assertEquals(newPerson.getId(), person.getId());
    Assertions.assertEquals(newPerson.getUsername(), person.getUsername());
    Assertions.assertEquals(newPerson.getPassword(), person.getPassword());
    Assertions.assertEquals(newPerson.getRole(), person.getRole());
  }
}
