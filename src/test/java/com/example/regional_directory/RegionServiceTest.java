package com.example.regional_directory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class RegionServiceTest {

    @Mock
    private RegionMapper regionMapper;

    @InjectMocks
    private RegionService regionService;

    RegionServiceTest() {
    }

    @Test
    void testFindAll() {
        List<Region> mockRegions = List.of(
                new Region(1L, "Region1", "R1"),
                new Region(2L, "Region2", "R2"));
        Mockito.when(regionMapper.findAll()).thenReturn(mockRegions);

        List<Region> regions = regionService.findAll();

        Assertions.assertEquals(2, regions.size());
        Assertions.assertEquals("Region1", regions.get(0).getName());
        Assertions.assertEquals("R1", regions.get(0).getShortName());
        Assertions.assertEquals("Region2", regions.get(1).getName());
        Assertions.assertEquals("R2", regions.get(1).getShortName());
        Mockito.verify(regionMapper, Mockito.times(1)).findAll();
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Region region = new Region(id, "New Region", "NR");
        Mockito.when(regionMapper.findById(id)).thenReturn(region);

        Region foundRegion = regionService.findById(id);

        Assertions.assertEquals("New Region", foundRegion.getName());
        Assertions.assertEquals("NR", foundRegion.getShortName());
        Mockito.verify(regionMapper, Mockito.times(1)).findById(id);
    }

    @Test
    void testInsert() {
        Region region = new Region(1L, "Inserted Region", "IR");
        regionService.insert(region);
        Mockito.verify(regionMapper, Mockito.times(1)).insert(region);
    }

    @Test
    void testUpdate() {
        Region region = new Region(1L, "Updated Region", "UR");
        regionService.update(region);
        Mockito.verify(regionMapper, Mockito.times(1)).update(region);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        regionService.delete(id);
        Mockito.verify(regionMapper, Mockito.times(1)).delete(id);
    }
}
