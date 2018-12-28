package com.lyarc.tp.corp.quality.revision.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.quality.revision.bean.RevisedInspection;
import com.lyarc.tp.corp.quality.revision.bean.RevisedInspectionQueryBean;
import com.lyarc.tp.corp.quality.revision.bean.TcheckRevisionLog;
import com.lyarc.tp.corp.quality.revision.bean.TcheckRevisionLogQueryBean;
@Mapper
public interface TcheckRevisionLogMapper {

    int deleteByPrimaryKey(String revisionId);

    int insert(TcheckRevisionLog record);

    int insertSelective(TcheckRevisionLog record);

    TcheckRevisionLog selectByPrimaryKey(String revisionId);
    
    int updateByPrimaryKeySelective(TcheckRevisionLog record);

    int updateByPrimaryKey(TcheckRevisionLog record);
    
    List<TcheckRevisionLog> list(TcheckRevisionLogQueryBean queryBean);

    Long count(TcheckRevisionLogQueryBean queryBean);
    
    

}