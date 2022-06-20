package br.com.rds.exception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException() {
    super("Employee not founded");
  }
}
