package com.lyarc.tp.corp.attachfile.dao;


import com.lyarc.tp.corp.attachfile.bean.AttachFileBean;
import com.lyarc.tp.corp.attachfile.bean.QueryFileBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 附件
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月31日 14:47:57
 */
@Mapper
public interface AttachFileMapper {

    /**
     * 方法描述 查询文件列表
     * 
     * @param queryFileBean 查询参数
     * @return List<AttachFileBean>
     *
     * @author huanghaoqi
     * @date 2017年08月31日 15:46:57
     */
    List<AttachFileBean> list(QueryFileBean queryFileBean);


    List<AttachFileBean> listByIds(@Param("fileIds") String[] fileIds);

    /**
     * 方法描述 添加
     * 
     * @param attachFileBean 附件对象
     *
     * @author huanghaoqi
     * @date 2017年09月01日 11:00:54
     */
    void insert(AttachFileBean attachFileBean);

    /**
     * 方法描述 根据文件ID查询
     * 
     * @param fileId 文件ID
     * @return AttachFileBean
     *
     * @author huanghaoqi
     * @date 2017年09月01日 12:48:31
     */
    AttachFileBean get(@Param("fileId") String fileId);

    /**
     * 方法描述 修改附件
     * 
     * @param attachFileBean 附件对象
     * @param fileIds ID集合
     *
     * @author huanghaoqi
     * @date 2017年09月04日 11:08:52
     */
    void update(@Param("attachFileBean") AttachFileBean attachFileBean, @Param("fileIds") String[] fileIds);
}
