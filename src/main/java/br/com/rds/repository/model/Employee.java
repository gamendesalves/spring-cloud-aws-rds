package br.com.rds.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "employee")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long id;

  @Column(nullable = false)
  public String name;

  @Column(nullable = false)
  public String lastName;

  public Employee(String name, String lastName) {
    this.name = name;
    this.lastName = lastName;
  }

}
