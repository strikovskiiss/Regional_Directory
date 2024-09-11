package com.example.regional_directory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "regions")
public class RegionService {

    private final RegionMapper regionMapper;

    @Autowired
    public RegionService(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Cacheable
    public List<Region> findAll() {
        return regionMapper.findAll();
    }

    @Cacheable(key = "#id")
    public Region findById(Long id) {
        return regionMapper.findById(id);
    }

    public void insert(Region region) {
        regionMapper.insert(region);
    }

    @CachePut(key = "#region.id")
    public void update(Region region) {
        regionMapper.update(region);
    }

    @CacheEvict(key = "#id")
    public void delete(Long id) {
        regionMapper.delete(id);
    }
}
