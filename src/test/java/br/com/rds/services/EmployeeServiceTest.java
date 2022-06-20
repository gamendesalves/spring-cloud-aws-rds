package br.com.rds.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.rds.repository.EmployeeRepository;
import br.com.rds.repository.model.Employee;

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

  @TestConfiguration
  static class EmployeeServiceTestContextConfiguration {
    @Bean
    public EmployeeService employeeService() {
      return new EmployeeService();
    }
  }

  @MockBean
  private EmployeeRepository repository;

  @Autowired
  private EmployeeService service;

  private Employee employee;

  @BeforeEach
  public void setUp() {
    this.employee = new Employee("Gabriel", "Alves");

    when(repository.findById(1L)).thenReturn(Optional.of(employee));
    when(repository.findAll()).thenReturn(Arrays.asList(employee));
    when(repository.save(employee)).thenReturn(employee);
  }

  @Test
  void testCreateEmployee_success() {

    Employee employeeSaved = this.service.createEmployee(this.employee);

    assertNotNull(employeeSaved);
  }

  @Test
  void testgetEmployees_success() {

    List<Employee> employees = this.service.getEmployees();

    assertNotNull(employees);
  }

  @Test
  void testgetEmployeeById_success() {

    Optional<Employee> employee = this.service.getEmployeeById(1L);

    assertNotNull(employee.get());
  }

}
