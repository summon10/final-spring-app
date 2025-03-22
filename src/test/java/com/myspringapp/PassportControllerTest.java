package com.myspringapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myspringapp.controller.PassportController;
import com.myspringapp.entity.Passport;
import com.myspringapp.entity.PassportPK;
import com.myspringapp.service.MyUserDetailService;
import com.myspringapp.service.PassportServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class) //
@WebMvcTest(controllers = PassportController.class)
@AutoConfigureMockMvc
public class PassportControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean //одновременно создаёт бин в текстовом контексте и мокает объект
    private PassportServiceImpl passportServiceImp;

    private static final Long Id = 200L;

    @Test
    void passportCreationWithoutAuthorization_whenValidInput_thenReturns403() throws Exception {
        PassportPK passportPK = new PassportPK(3311, 451259);
        Passport passport  = new Passport(passportPK, "Sergey", "Ched", "Alekseevich",
                "orel", 'm', false,false,
                LocalDate.of(1995,12,1));

        mvc.perform(post("/passportNew")
                        .contentType("application/json")
                        .param("passport", objectMapper.writeValueAsString(passport))
                        .content (objectMapper.writeValueAsString(passport)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(userDetailsServiceBeanName = "MyUserDetailService", value = "admin")
    //@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
    void passportCreationWithAuthorization_whenValidInput_thenReturns200() throws Exception {
        PassportPK passportPK = new PassportPK(3311, 451259);
        Passport passport  = new Passport(passportPK, "Sergey", "Ched", "Alekseevich",
                "orel", 'm', false,false,
                LocalDate.of(1995,12,1));

        mvc.perform(post("/passportNew")
                        .contentType("application/json")
                        .param("passport", objectMapper.writeValueAsString(passport))
                        .content (objectMapper.writeValueAsString(passport)))
                .andExpect(status().isOk());
    }


}