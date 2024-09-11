package com.example.regional_directory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegionController.class)
class RegionControllerTest {

    @MockBean
    private RegionService regionService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllRegions() throws Exception {
        List<Region> regions = List.of(
                new Region(1L, "Region1", "R1"),
                new Region(2L, "Region2", "R2"));
        Mockito.when(regionService.findAll()).thenReturn(regions);

        mockMvc.perform(get("/regions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Region1"))
                .andExpect(jsonPath("$[0].shortName").value("R1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Region2"))
                .andExpect(jsonPath("$[1].shortName").value("R2"));

        Mockito.verify(regionService, Mockito.times(1)).findAll();
    }

    @Test
    void TestGetById() throws Exception {
        Region region = new Region(1L, "New Region", "NR");
        Mockito.when(regionService.findById(1L)).thenReturn(region);

        mockMvc.perform(get("/regions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("New Region"))
                .andExpect(jsonPath("$.shortName").value("NR"));

        Mockito.verify(regionService, Mockito.times(1)).findById(1L);
    }

    @Test
    void testCreateRegion() throws Exception {
        Region region = new Region(1L, "Created Region", "CR");

        mockMvc.perform(post("/regions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(region)))
                .andExpect(status().isCreated());

        Mockito.verify(regionService, Mockito.times(1)).insert(argThat(r ->
                r.getId().equals(region.getId()) &&
                        r.getName().equals(region.getName()) &&
                        r.getShortName().equals(region.getShortName())
        ));
    }

    @Test
    void testUpdateRegion() throws Exception {
        Region updatedRegion = new Region(1L, "Updated Region", "UR");

        mockMvc.perform(put("/regions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRegion)))
                .andExpect(status().isOk());

        Mockito.verify(regionService, Mockito.times(1)).update(argThat(r ->
                r.getId().equals(updatedRegion.getId()) &&
                        r.getName().equals(updatedRegion.getName()) &&
                        r.getShortName().equals(updatedRegion.getShortName())
        ));
    }

    @Test
    void testDeleteRegion() throws Exception {
        mockMvc.perform(delete("/regions/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(regionService, Mockito.times(1)).delete(1L);
    }
}
