package com.lyarc.tp.corp.workflowact.util;


import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.lang.reflect.Field;

/**
 * 实现对象的克隆功能
 * 
 * @author bluejoe2008@gmail.com
 *
 */
public abstract class CloneUtils
{
	public static void copyFields(Object source, Object target, String... fieldNames)
	{
		Assert.notNull(source,"");
		Assert.notNull(target,"");
//		Assert.(source.getClass(), target.getClass());

		for (String fieldName : fieldNames)
		{
			try
			{
				Field field = FieldUtils.getField(source.getClass(), fieldName, true);
				field.setAccessible(true);
				field.set(target, field.get(source));
			}
			catch (Exception e)
			{
				LoggerFactory.getLogger(CloneUtils.class).warn(e.getMessage());
			}
		}
	}
}
