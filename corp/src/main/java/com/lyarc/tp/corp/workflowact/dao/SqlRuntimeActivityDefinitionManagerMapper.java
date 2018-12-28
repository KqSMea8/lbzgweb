package com.lyarc.tp.corp.workflowact.dao;

import com.lyarc.tp.corp.workflowact.ctrl.RuntimeActivityDefinitionEntity;
import com.lyarc.tp.corp.workflowact.ctrl.common.SimpleRuntimeActivityDefinitionEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SqlRuntimeActivityDefinitionManagerMapper
{
	@Delete("delete from t_wf_activity_creation")
	void deleteAll();

	@Select("select * from t_wf_activity_creation")
	@Results(value = { @Result(property = "factoryName", column = "factory_name"),
			@Result(property = "processDefinitionId", column = "process_definition_id"),
			@Result(property = "processInstanceId", column = "process_instance_id"),
			@Result(property = "propertiesText", column = "properties_text") })
	List<SimpleRuntimeActivityDefinitionEntity> findAll();

	@Insert("insert into t_wf_activity_creation (factory_name,process_definition_id,process_instance_id,properties_text) values (#{factoryName},#{processDefinitionId},#{processInstanceId},#{propertiesText})")
	void save(RuntimeActivityDefinitionEntity entity);
}
