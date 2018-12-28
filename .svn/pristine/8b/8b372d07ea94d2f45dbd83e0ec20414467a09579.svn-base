package com.lyarc.tp.corp.workflowact.ctrl.creator;

import com.lyarc.tp.corp.workflowact.ctrl.RuntimeActivityDefinitionEntity;
import com.lyarc.tp.corp.workflowact.util.CloneUtils;
import com.lyarc.tp.corp.workflowact.util.ProcessDefinitionUtils;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.el.FixedValue;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChainedActivitiesCreator extends RuntimeActivityCreatorSupport implements RuntimeActivityCreator
{
	@Override
	public ActivityImpl[] createActivities(ProcessEngine processEngine, ProcessDefinitionEntity processDefinition,
			RuntimeActivityDefinitionEntity info)
	{
		info.setFactoryName(ChainedActivitiesCreator.class.getName());
		RuntimeActivityDefinitionEntityIntepreter radei = new RuntimeActivityDefinitionEntityIntepreter(info);

		if (radei.getCloneActivityIds() == null)
		{
			radei.setCloneActivityIds(CollectionUtils.arrayToList(new String[radei.getAssignees().size()]));
		}

		return createActivities(processEngine, processDefinition, info.getProcessInstanceId(),
			radei.getPrototypeActivityId(), radei.getNextActivityId(), radei.getAssignees(),
			radei.getCloneActivityIds());
	}

	private ActivityImpl[] createActivities(ProcessEngine processEngine, ProcessDefinitionEntity processDefinition,
			String processInstanceId, String prototypeActivityId, String nextActivityId, List<String> assignees,
			List<String> activityIds)
	{
		ActivityImpl prototypeActivity = ProcessDefinitionUtils.getActivity(processEngine, processDefinition.getId(),
			prototypeActivityId);

		Map<String, TaskDefinition> taskDefinitions = processDefinition.getTaskDefinitions();
		TaskDefinition prototypeTd = taskDefinitions.get(prototypeActivityId);

		List<ActivityImpl> activities = new ArrayList<ActivityImpl>();
		for (int i = 0; i < assignees.size(); i++)
		{
			if (activityIds.get(i) == null)
			{
				String activityId = createUniqueActivityId(processInstanceId, prototypeActivityId);
				activityIds.set(i, activityId);
			}

			ActivityImpl clone = createActivity(processEngine, processDefinition, prototypeActivity,
				activityIds.get(i), assignees.get(i));
			activities.add(clone);
			//将任务定义添加到流程任务定义map 中
			TaskDefinition taskDefinition = cloneTaskDefinition(prototypeTd);
			taskDefinition.setKey(activityIds.get(i));
			if (assignees.get(i) != null)
			{
				taskDefinition.setAssigneeExpression(new FixedValue(assignees.get(i)));
			}
			taskDefinitions.put(activityIds.get(i),taskDefinition);
		}

		ActivityImpl nextActivity = ProcessDefinitionUtils.getActivity(processEngine, processDefinition.getId(),
			nextActivityId);
		createActivityChain(activities, nextActivity);

		return activities.toArray(new ActivityImpl[0]);
	}
}
