package org.polytech.covid.center;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.polytech.covid.domain.VaccinationCenter;
import org.polytech.covid.security.services.VaccinationCenterService;

@SpringBootTest
@AutoConfigureMockMvc
public class VaccinationCenterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VaccinationCenterService vaccinationCenterService;
    private static VaccinationCenter center1;
    private static VaccinationCenter center2;
    private static List<VaccinationCenter> centers;

    @BeforeAll
    public static void setup() {
        center1 = new VaccinationCenter("centerTest1", "addressTest1", "cityTest1", "postalCodeTest1");
        center2 = new VaccinationCenter("centerTest2", "addressTest2", "cityTest2", "postalCodeTest2");
        centers =  new java.util.ArrayList<VaccinationCenter>();
        centers.add(center1);
        centers.add(center2);
    }

    @Test
    public void testGetCenters() throws Exception {
        doReturn(centers).when(vaccinationCenterService).findAll();
        mockMvc.perform(get("/public/centers"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().json("{'data':[{'id':1,'name':'centerTest1','address':'addressTest1','city':'cityTest1','postalCode':'postalCodeTest1','rdvs':[]}," +
                    "{'id':2,'name':'centerTest2','address':'addressTest2','city':'cityTest2','postalCode':'postalCodeTest2','rdvs':[]}]}"));
    }

    @Test
    public void testGetCenter() throws Exception {
        doReturn(center1).when(vaccinationCenterService).findById(1);
        mockMvc.perform(get("/public/center/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
            // .andExpect(MockMvcResultMatchers.content().json("{'id':1,'name':'centerTest1','address':'addressTest1','city':'cityTest1','postalCode':'postalCodeTest1','rdvs':[]}"));
    }

    @Test
    public void testGetCenterUnknownId() throws Exception {
        mockMvc.perform(get("/public/center/1000000")).andExpect(status().isNotFound());
    }


    @Test
    public void testPostCenter() throws Exception {
        // créer @RequestBody VaccinationCenter
        mockMvc.perform(post("/public/center/")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"centerTest1\",\"address\":\"addressTest1\",\"city\":\"cityTest1\",\"postalCode\":\"postalCodeTest1\"}"))
            .andExpect(status().isCreated());
    }

    @Test
    public void testPostCenterBadRequest() throws Exception {
        mockMvc.perform(post("/public/center/")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"centerTest2\",\"address\":\"addressTest2\",\"city\":\"cityTest2\",\"postalCode\":\"postalCodeTest2\",\"autre\":\"postFailed}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testPutCenter() throws Exception {
        doReturn(center1).when(vaccinationCenterService).findById(1);
        mockMvc.perform(MockMvcRequestBuilders.put("/public/center/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\": 1,\"name\":\"centerTestNew\",\"address\":\"addressTestNew\",\"city\":\"cityTestNew\",\"postalCode\":\"postalCodeTestNew\"}"))
            .andExpect(status().isOk());
    }

    @Test
    public void testPutCenterBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/public/center/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"centerTestFailed\",\"address\":\"addressTestFailed\",\"city\":\"cityTestFailed\",\"postalCode\":\"postalCodeTestFailed\",\"autre\":\"putFailed}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testPutCenterUnknownId() throws Exception {
        doReturn(centers).when(vaccinationCenterService).findAll();
        mockMvc.perform(MockMvcRequestBuilders.put("/public/center/1000000")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"centerTestUnknown\",\"address\":\"addressTestUnknown\",\"city\":\"cityTestUnknown\",\"postalCode\":\"postalCodeTestUnknown\"}"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteCenter() throws Exception {
        doReturn(center2).when(vaccinationCenterService).findById(2);
        mockMvc.perform(MockMvcRequestBuilders.delete("/public/center/2"))
            .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCenterUnknownId() throws Exception {
        doReturn(centers).when(vaccinationCenterService).findAll();
        mockMvc.perform(MockMvcRequestBuilders.delete("/public/center/1000000"))
            .andExpect(status().isNotFound());
    }



}
