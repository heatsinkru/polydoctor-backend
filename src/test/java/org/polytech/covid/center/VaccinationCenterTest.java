package org.polytech.covid.center;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.polytech.covid.domain.VaccinationCenter;
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
    public void testGetCenter() throws Exception {
        mockMvc.perform(get("/public/center/1")).andExpect(status().isOk());
    }

    @Test
    public void testPostCenter() throws Exception {
        mockMvc.perform(post("/public/center/")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"centerTest\",\"address\":\"addressTest\",\"city\":\"cityTest\",\"postalCode\":\"postalCodeTest\"}"))
            .andExpect(status().isCreated());
    }

    @Test
    public void testPostCenterFailed() throws Exception {
        mockMvc.perform(post("/public/center/")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"centerTest\",\"address\":\"addressTest\",\"city\":\"cityTest\",\"postalCode\":\"postalCodeTest\"}"))
            .andExpect(status().isCreated());
    }




}
