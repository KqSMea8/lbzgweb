package com.lyarc.tp.corp.workflowact.ctrl.impl;

import com.lyarc.tp.corp.workflowact.ctrl.RuntimeActivityDefinitionEntity;
import com.lyarc.tp.corp.workflowact.ctrl.RuntimeActivityDefinitionManager;
import com.lyarc.tp.corp.workflowact.dao.SqlRuntimeActivityDefinitionManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SqlRuntimeActivityDefinitionManager implements RuntimeActivityDefinitionManager{
	@Autowired
	private SqlRuntimeActivityDefinitionManagerMapper _mapper;
	@Override
	public List<RuntimeActivityDefinitionEntity> list()
	{
		List<RuntimeActivityDefinitionEntity> list = new ArrayList<RuntimeActivityDefinitionEntity>();
		list.addAll(_mapper.findAll());
		return list;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void removeAll()
	{
		_mapper.deleteAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(RuntimeActivityDefinitionEntity entity)
	{
		_mapper.save(entity);
	}
}
