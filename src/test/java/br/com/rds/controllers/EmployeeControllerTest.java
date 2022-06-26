package br.com.rds.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.rds.controllers.dto.EmployeeDTO;
import br.com.rds.repository.model.Employee;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/create-data.sql")
@Sql(scripts = "/cleanup-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
class EmployeeControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testGetEmployees_thenReturns200() throws Exception {

    ResponseEntity<List<Employee>> employees = this.restTemplate.exchange(this.getUrl(), HttpMethod.GET, null,
        new ParameterizedTypeReference<List<Employee>>() {
        });

    assertFalse(employees.getBody().isEmpty());

  }

  @Test
  void testCreateEmployee_thenReturns200() throws Exception {

    EmployeeDTO dto = new EmployeeDTO("Pedro", "Mendes");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");

    HttpEntity<EmployeeDTO> request = new HttpEntity<>(dto, headers);

    ResponseEntity<Employee> employeeCreated = this.restTemplate.postForEntity(this.getUrl(), request, Employee.class);

    assertNotNull(employeeCreated);

  }

  @Test
  void testGetEmployeeById_thenReturns200() throws Exception {

    ResponseEntity<Employee> employee = this.restTemplate.getForEntity(this.getUrl() + "/1", Employee.class);

    assertNotNull(employee);

  }

  private String getUrl() {
    return "http://localhost:" + port + "/employee";
  }

}
