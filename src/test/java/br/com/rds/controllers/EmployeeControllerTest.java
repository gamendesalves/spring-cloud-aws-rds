package br.com.rds.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rds.controllers.dto.EmployeeDTO;
import br.com.rds.repository.model.Employee;
import br.com.rds.services.EmployeeService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EmployeeService service;

  @Test
  void testGetEmployees_thenReturns200() throws Exception {

    MvcResult mvcResult = mockMvc.perform(get("/employee")
                                 .contentType("application/json"))
                                 .andExpect(status().isOk())
                                 .andReturn();

    assertNotNull(mvcResult.getResponse());
  }

  @Test
  void testGetEmployeeById_thenReturns200() throws Exception {

    when(service.getEmployeeById(1)).thenReturn(Optional.of(new Employee()));

    MvcResult mvcResult = mockMvc.perform(get("/employee/{id}", "1")
                                 .contentType("application/json"))
                                 .andExpect(status().isOk())
                                 .andReturn();

    assertNotNull(mvcResult.getResponse());
  }

  @Test
  void testCreateEmployee_thenReturns201() throws Exception {

    MvcResult mvcResult = mockMvc.perform(post("/employee")
                                 .contentType("application/json")
                                 .content(this.asJsonString(new EmployeeDTO("Gabriel", "Alves"))))
                                 .andExpect(status().isCreated())
                                 .andReturn();

    assertNotNull(mvcResult.getResponse());
  }

  private String asJsonString(final Object dto) {
    try {
      return new ObjectMapper().writeValueAsString(dto);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
