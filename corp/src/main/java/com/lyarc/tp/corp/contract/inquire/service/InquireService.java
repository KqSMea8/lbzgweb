package com.lyarc.tp.corp.contract.inquire.service;
import com.lyarc.tp.corp.contract.inquire.bean.Inquire;
import com.lyarc.tp.corp.contract.inquire.bean.InquireDetail;
import com.lyarc.tp.corp.contract.inquire.bean.InquireQueryBean;
import com.lyarc.tp.corp.contract.inquire.dao.InquireDetailMapper;
import com.lyarc.tp.corp.contract.inquire.dao.InquireMapper;
import com.lyarc.tp.corp.contract.inquire.dto.InquireDto;
import com.lyarc.tp.corp.contract.inquire.form.InquireDetailForm;
import com.lyarc.tp.corp.contract.inquire.form.InquireForm;
import com.lyarc.tp.corp.seq.service.SeqService;
import com.lyarc.tp.corp.system.material.bean.Material;
import com.lyarc.tp.corp.system.material.bean.MaterialQueryBean;
import com.lyarc.tp.corp.system.material.dao.MaterialMapper;
import com.lyarc.tp.corp.system.material.dto.MaterialDto;
import com.lyarc.tp.corp.util.JMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class InquireService {
    @Autowired
    private InquireMapper inquireMapper;
    @Autowired
    private InquireDetailMapper inquireDetailMapper;

    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private SeqService seqService;

    public List<Inquire> list(InquireQueryBean queryBean) {
        List<Inquire> inquireList = inquireMapper.list(queryBean);
        return inquireList;
    }

    public Long count(InquireQueryBean queryBean) {
       Long count =  inquireMapper.count(queryBean);
       return count;
    }

    @Transactional
    public Integer delete(String inquireId) {
        //删除明细
        inquireDetailMapper.deleteByInquireId(inquireId);
        int rows = inquireMapper.deleteByPrimaryKey(inquireId);
        return rows;
    }

    @Transactional
    public Integer update(InquireForm inquireForm) {
        Inquire inquire = JMapperUtils.getDestination(inquireForm, Inquire.class, InquireForm.class);
        inquire.setUpdateTime(LocalDateTime.now());
        int rows = inquireMapper.updateByPrimaryKeySelective(inquire);
        String inquireId = inquire.getInquireId();
        //删除明细
        inquireDetailMapper.deleteByInquireId(inquireId);

        //插入明细
        List<InquireDetailForm> detailList = inquireForm.getDetailList();
        detailList.forEach(d->{
            d.setInquireId(inquireId);
            InquireDetail inquireDetail = JMapperUtils.getDestination(d, InquireDetail.class, InquireDetailForm.class);
            inquireDetailMapper.insertSelective(inquireDetail);
        });
        return rows;
    }

    @Transactional
    public Integer add(InquireForm inquireForm) {
        Inquire inquire = JMapperUtils.getDestination(inquireForm, Inquire.class, InquireForm.class);

        String inquireId = getInquireId();
        inquire.setInquireId(inquireId);
        inquire.setStatus(1);
        int row = inquireMapper.insertSelective(inquire);

        //插入明细
        List<InquireDetailForm> detailList = inquireForm.getDetailList();
        detailList.forEach(d->{
            d.setInquireId(inquireId);
            InquireDetail inquireDetail =
                    JMapperUtils.getDestination(d, InquireDetail.class, InquireDetailForm.class);
            //查询该图号是否在物料中存在
            String drawing = inquireDetail.getDrawing();
            MaterialQueryBean queryBean = new MaterialQueryBean();
            String materId = inquire.getCustomerId() + "-" + drawing;
            queryBean.setMaterId(materId);
            Material material1 = materialMapper.selectMaterial(queryBean);
            //不存在 添加物料
            if(material1 == null || StringUtils.isEmpty(material1.getMaterId())){
                Material material = new Material();
                material.setMaterId(materId);
                material.setCustomerId(inquire.getCustomerId());
                material.setDrawing(drawing);
                material.setType("2");//产成品
                material.setMaterName(inquireDetail.getMaterName());
                material.setUnit(inquireDetail.getUnit());
                material.setCateId(3);
//                material.setStatus(1);
//                material.setIsControl(0);
//                material.setIsAccount(0);
                material.setCreator(inquire.getCreator());
                materialMapper.insertSelective(material);
            }

            inquireDetailMapper.insertSelective(inquireDetail);
        });
        return row;
    }
     /**
      *
      * @description 获取询价单详情
      * @param
      * @return
      * @author wangting
      * @date 2018/7/27 0027 16:39
      *
      */
    public InquireDto getInquire(String inquireId) {
        InquireDto inquire = inquireMapper.selectAndDetail(inquireId);
        return inquire;
    }

    public String getInquireId(){
    	return seqService.getBillId(new Date(), "t_inquire", "");
    }

    @Transactional
    public Integer calcWeightSave(InquireForm inquireForm,Integer submitFlag) {
        Inquire inquire = JMapperUtils.getDestination(inquireForm, Inquire.class, InquireForm.class);
        inquire.setStatus(submitFlag);
        inquire.setCompleteTime(LocalDate.now());
        int rows = inquireMapper.updateByPrimaryKeySelective(inquire);
        String inquireId = inquire.getInquireId();
        //获取明细
        List<InquireDetailForm> detailList = inquireForm.getDetailList();
        detailList.forEach(d->{
            d.setInquireId(inquireId);
            InquireDetail detail = new InquireDetail();
            detail.setId(d.getId());
            detail.setFieldJson(d.getFieldJson());
            inquireDetailMapper.updateByPrimaryKeySelective(detail);
        });
        return rows;
    }
}
