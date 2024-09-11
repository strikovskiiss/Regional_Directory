package com.example.regional_directory;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegionMapper {

    @Select("SELECT * FROM regions")
    List<Region> findAll();

    @Select("SELECT * FROM regions WHERE id = #{id}")
    Region findById(Long id);

    @Insert("INSERT INTO regions(name, shortname) VALUES(#{name}, #{shortName})")
    void insert(Region region);

    @Update("UPDATE regions SET name = #{name}, shortname = #{shortName} WHERE id = #{id}")
    void update(Region region);

    @Delete("DELETE FROM regions WHERE id = #{id}")
    void delete(Long id);
}
