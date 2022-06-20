package br.com.rds.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rds.repository.EmployeeRepository;
import br.com.rds.repository.model.Employee;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {

  @Autowired
  private EmployeeRepository repository;

  // Read data from read replicas
  @Transactional(readOnly = true)
  public List<Employee> getEmployees() {
    
    log.info("Getting all employees");
    
    return this.repository.findAll();
  }

  //Read data from read replicas
  @Transactional(readOnly = true)
  public Optional<Employee> getEmployeeById(long id) {
   
    log.info("Getting employee by id {}", id);
    
    return this.repository.findById(id);
  }

  @Transactional
  public Employee createEmployee(Employee employee) {
    
    log.info("Saving {}", employee);
    
    return this.repository.save(employee);
  }

}
