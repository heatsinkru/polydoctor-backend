package org.polytech.covid.center;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.polytech.covid.controller.VaccinationCenterController;
import org.polytech.covid.domain.VaccinationCenter;
import org.polytech.covid.security.WebSecurityConfig;
import org.polytech.covid.security.services.VaccinationCenterService;

@WebMvcTest(controllers = VaccinationCenterController.class)
@ContextConfiguration(classes = { WebSecurityConfig.class, VaccinationCenterController.class })
public class VaccinationCenterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VaccinationCenterService vaccinationCenterService;

    @Test
    public void testGetCenters() throws Exception {
        mockMvc.perform(get("/public/centers")).andExpect(status().isOk());
    }

    @Test
    public void testGetCenter() throws Exception {
        mockMvc.perform(get("/public/centers/1")).andExpect(status().isOk());
    }

    @Test
    public void testPostCenter() throws Exception {
        mockMvc.perform(post("/public/centers")).andExpect(status().isOk());
    }
}
