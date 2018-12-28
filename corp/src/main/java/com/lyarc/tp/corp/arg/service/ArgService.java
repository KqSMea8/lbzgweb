package com.lyarc.tp.corp.arg.service;

import com.lyarc.tp.corp.arg.bean.Arg;
import com.lyarc.tp.corp.arg.bean.ArgQueryBean;
import com.lyarc.tp.corp.arg.dao.ArgMapper;
import com.lyarc.tp.corp.contacts.bean.DeptBean;
import com.lyarc.tp.corp.contacts.bean.EmployeeBean;
import com.lyarc.tp.corp.contacts.bean.QueryEmpBean;
import com.lyarc.tp.corp.contacts.dao.DeptMapper;
import com.lyarc.tp.corp.contacts.dao.EmpMapper;
import com.lyarc.tp.corp.system.material.bean.MaterialCategory;
import com.lyarc.tp.corp.warehouse.bean.StockOpertype;
import com.lyarc.tp.corp.warehouse.bean.StockOpertypeQueryBean;
import com.lyarc.tp.corp.warehouse.bean.Storage;
import com.lyarc.tp.corp.warehouse.bean.StorageQueryBean;
import com.lyarc.tp.corp.warehouse.dao.StockOpertypeMapper;
import com.lyarc.tp.corp.warehouse.dao.StorageMapper;
import com.lyarc.tp.corp.warehouse.dto.StorageDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ArgService {

    private static Logger logger = LoggerFactory.getLogger(ArgService.class);

    @Autowired
    private ArgMapper argMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private StockOpertypeMapper stockOpertypeMapper;


    public List<Arg> queryArgList(ArgQueryBean queryBean){
        List<Arg> argBeanList = argMapper.list(queryBean);
        return argBeanList;
    }

    public Integer add(Arg arg){
        argMapper.insertSelective(arg);
        return arg.getId();
    }

    public void delete(Integer id){
        argMapper.deleteByPrimaryKey(id);
    }
    public void update(Arg arg) {
        argMapper.updateByPrimaryKeySelective(arg);
    }

    public Map<String,List<Arg>> queryArgMap() {
        //将员工和部门也放到list 中 便于前台显示
        List<EmployeeBean> empList = empMapper.list(new QueryEmpBean());
        List<DeptBean> deptList = deptMapper.list();
        
        List<StorageDto> storageList = storageMapper.list(new StorageQueryBean());//仓库列表
        List<StockOpertype> stockOperTypeList = stockOpertypeMapper.list(new StockOpertypeQueryBean());//仓库操作类型

        List<Arg> list = argMapper.list(new ArgQueryBean());

        empList.forEach(e->{
            Arg arg = new Arg();
            arg.setArgGroup("empList");
            arg.setArgCode(e.getEmpId());
            arg.setArgText(e.getTrueName());
            list.add(arg);
        });
        deptList.forEach(e->{
            Arg arg = new Arg();
            arg.setArgGroup("deptList");
            arg.setArgCode(e.getDeptId().toString());
            arg.setArgText(e.getDeptName());
            list.add(arg);
        });
        storageList.forEach(e->{
            Arg arg = new Arg();
            arg.setArgGroup("storageList");
            arg.setArgCode(e.getStorageId().toString());
            arg.setArgText(e.getName());
            list.add(arg);
        });
        stockOperTypeList.forEach(e->{
            Arg arg = new Arg();
            arg.setArgGroup("stockOperTypeList");
            arg.setArgCode(e.getOperId().toString());
            arg.setArgText(e.getName());
            list.add(arg);
        });
        Map<String, List<Arg>> argMap = list.stream().collect(Collectors.groupingBy(Arg::getArgGroup));
        return argMap;
    }
}
