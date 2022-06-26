package br.com.rds.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.rds.SpringBootRdsServiceApplication;
import br.com.rds.repository.model.Employee;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootRdsServiceApplication.class)
@Sql(scripts = "/create-data.sql")
@Sql(scripts = "/cleanup-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
class EmployeeRepositoryTest {

  @Autowired
  private EmployeeRepository repository;

  @Test
  void testGetAllEmployees_returnSuccess() {

    List<Employee> employees = this.repository.findAll();

    assertFalse(employees.isEmpty());
  }
  
  @Test
  void testGetEmployeeById_returnSuccess() {
    Employee employeeCreated = this.repository.save(new Employee("Gabriel", "Alves"));

    Optional<Employee> employee = this.repository.findById(employeeCreated.getId());

    assertTrue(employee.isPresent());
    assertEquals(employeeCreated.getName(), employee.get().getName());
    assertEquals(employeeCreated.getLastName(), employee.get().getLastName());
  }

}
