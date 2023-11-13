package org.polytech.covid.center;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.polytech.covid.security.services.VaccinationCenterService;

@SpringBootTest
@AutoConfigureMockMvc
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
    public void testPostCenter() throws Exception {
        mockMvc.perform(post("/public/centers")).andExpect(status().isOk());
    }

    @Test
    public void testGetCenter() throws Exception {
        mockMvc.perform(get("/public/centers/1")).andExpect(status().isOk());
    }


}
