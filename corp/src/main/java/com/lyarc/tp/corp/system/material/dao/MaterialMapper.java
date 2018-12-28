package com.lyarc.tp.corp.system.material.dao;

import com.lyarc.tp.corp.system.material.bean.Material;
import com.lyarc.tp.corp.system.material.bean.MaterialQueryBean;
import com.lyarc.tp.corp.system.material.dto.MaterialDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaterialMapper {
    int deleteByPrimaryKey(String materId);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(String materId);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);

    List<Material> list(MaterialQueryBean queryBean);

    Long count(MaterialQueryBean queryBean);

    Material selectMaterial(MaterialQueryBean queryBean);

    List<Material> selectByCateId(Integer id);
}