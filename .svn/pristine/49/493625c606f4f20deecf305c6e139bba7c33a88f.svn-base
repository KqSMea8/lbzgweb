package com.lyarc.tp.corp.system.material.dao;

import com.lyarc.tp.corp.system.material.bean.MaterialCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaterialCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialCategory record);

    int insertSelective(MaterialCategory record);

    MaterialCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialCategory record);

    int updateByPrimaryKey(MaterialCategory record);

    List<MaterialCategory> listCategory(MaterialCategory record);

    MaterialCategory getRootCategory();

    List<MaterialCategory> selectByParentId(Integer id);
}