package com.lyarc.tp.corp.system.material.service;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.system.material.bean.Material;
import com.lyarc.tp.corp.system.material.bean.MaterialCategory;
import com.lyarc.tp.corp.system.material.bean.MaterialQueryBean;
import com.lyarc.tp.corp.system.material.dao.MaterialCategoryMapper;
import com.lyarc.tp.corp.system.material.dao.MaterialMapper;
import com.lyarc.tp.corp.system.material.dto.MaterialCategoryNodeDto;
import com.lyarc.tp.corp.util.JMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    @Autowired
    private MaterialCategoryMapper categoryMapper;
    @Autowired
    private MaterialMapper materialMapper;


     /**
      *
      * @description 构建物料分类树
      * @param
      * @return
      * @author wangting
      * @date 2018/7/25 0025 16:06
      *
      */
    public List<MaterialCategoryNodeDto> buildCategoryTree() {
        List<MaterialCategory> list = categoryMapper.listCategory(null);
        Map<Integer, List<MaterialCategory>> collect = list.stream().collect(Collectors.groupingBy(MaterialCategory::getParentId));
        List<MaterialCategoryNodeDto> res = new ArrayList<>();
        createTree(0,collect,res);
        return res;
    }

    private void createTree(Integer id, Map<Integer, List<MaterialCategory>> map,List<MaterialCategoryNodeDto> res) {
        List<MaterialCategory> list = map.get(id);
        if(list == null){
            return;
        }
        List<MaterialCategoryNodeDto> dtoList = JMapperUtils.getDestinations(list, MaterialCategoryNodeDto.class, MaterialCategory.class);
        dtoList.forEach(c->{
            Integer cid = c.getId();
            createTree(cid, map, c.getChildren());
            res.add(c);
        });
    }

    public PageResultBean list(MaterialQueryBean queryBean) {
        List<Material> list = materialMapper.list(queryBean);
        Long count = materialMapper.count(queryBean);
        return PageResultBean.success((long) count, list);

    }

    public void add(Material material) {
        String materId = material.getMaterId();
        if(StringUtils.isEmpty(materId)){
            materId = material.getCustomerId() + "-" + material.getDrawing();
        }
        Material existMaterial = materialMapper.selectByPrimaryKey(materId);
        if(existMaterial!=null){
            throw new CorpException(ResultVo.failure("该物料编码已存在！"));
        }
        materialMapper.insertSelective(material);
    }

    public void update(Material material) {
        materialMapper.updateByPrimaryKeySelective(material);
    }

    public Material getMaterial(String materId) {
        MaterialQueryBean queryBean = new MaterialQueryBean();
        queryBean.setMaterId(materId);
        return materialMapper.selectMaterial(queryBean);
    }

    public Integer delete(String materId) {
        return materialMapper.deleteByPrimaryKey(materId);
    }

    public void addCategory(MaterialCategory materialCategory) {
        categoryMapper.insertSelective(materialCategory);
    }

    public void updateCategory(MaterialCategory materialCategory) {
        categoryMapper.updateByPrimaryKeySelective(materialCategory);
    }

    public Integer deleteCategory(Integer id) {
        //判断该分类下是否存在子节点
        List<MaterialCategory> cateList = categoryMapper.selectByParentId(id);
        if(cateList != null && cateList.size() != 0){
            throw new CorpException(ResultVo.failure("该分类下存在子节点无法删除！"));
        }
        //判断该分类下是否有物料
        List<Material> mateList = materialMapper.selectByCateId(id);
        if(mateList != null && mateList.size() !=0){
            throw new CorpException(ResultVo.failure("该分类下存在物料信息无法删除！"));
        }
        return categoryMapper.deleteByPrimaryKey(id);
    }

    public List<MaterialCategory> listCategory(MaterialCategory materialCategory) {
        List<MaterialCategory> list = categoryMapper.listCategory(materialCategory);
        return list;
    }
    
    public String getCode(Integer cateId) {
        MaterialCategory materialCategory = categoryMapper.selectByPrimaryKey(cateId);
/*        code = materialCategory.getCode();
        if(materialCategory.getParentId()!=0) {
        	MaterialCategory mc = categoryMapper.selectByPrimaryKey(materialCategory.getParentId());
        	return getCode(code,mc.getId()) + code + ".";
        }
        return code + ".";*/
        return materialCategory.getCode();
    }
}
