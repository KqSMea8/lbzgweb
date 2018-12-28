package com.lyarc.tp.corp.workflow.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface WorkflowReportQueryMapper {

    List<HashMap> list(@Param("sql") String sql);

    Long count(@Param("sql") String sql);

}
