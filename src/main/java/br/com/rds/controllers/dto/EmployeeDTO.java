package br.com.rds.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

  @NotBlank
  @Size(min = 0, max = 30)
  public String name;

  @NotBlank
  @Size(min = 0, max = 50)
  public String lastName;
}
