package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Model.Warehouse;
import com.example.rentingsystem.Service.WarehouseSerivce;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = WarehouseController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class WarehouseControllerTest {

    @MockBean
    WarehouseSerivce warehouseSerivce;

    @Autowired
    MockMvc mockMvc;

    Warehouse warehouse;

    List<Warehouse> warehouseList;

    @BeforeEach
    void setup(){
        warehouse = new Warehouse(1,"4*4","Medina",null,null);
        warehouseList = Arrays.asList(warehouse);
    }
    // Three
    @Test
    public void getWarehouse()throws Exception {
        Mockito.when(warehouseSerivce.getWarehouses()).thenReturn(warehouseList);
        mockMvc.perform(get("/api/v1/warehouses/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].location").value("Medina"));
    }
    // Four
    @Test
    public void deletWarehouse()throws Exception{
        mockMvc.perform(delete("/api/v1/warehouses/{warehouseId}",warehouse.getId()))
                .andExpect(status().isOk());
    }

    // Five
    @Test
    public void addWarehouse()throws Exception{
        mockMvc.perform(post("/api/v1/warehouses/add").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(warehouse)))
                .andExpect(status().isOk());
    }




}
