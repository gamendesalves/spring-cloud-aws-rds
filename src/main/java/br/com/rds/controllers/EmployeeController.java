package br.com.rds.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rds.controllers.dto.EmployeeDTO;
import br.com.rds.exception.EmployeeNotFoundException;
import br.com.rds.repository.model.Employee;
import br.com.rds.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@RequestMapping("/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

  @Autowired
  private EmployeeService service;

  @GetMapping
  public List<Employee> getEmployees() {

    log.info("Listing employees");

    return this.service.getEmployees();

  }

  @GetMapping("/{id}")
  public Employee getEmployeeById(@PathVariable("id") final long id) {

    log.info("Getting employee by id {}", id);

    return this.service.getEmployeeById(id).orElseThrow(EmployeeNotFoundException::new);
    
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Employee createEmployee(@RequestBody EmployeeDTO dto) {

    log.info("Creating new Employee {}", dto);

    var employeeSaved = this.service.createEmployee(new Employee(dto.getName(), dto.getLastName()));

    log.info("Employee {} created success", employeeSaved);

    return employeeSaved;

  }

}
