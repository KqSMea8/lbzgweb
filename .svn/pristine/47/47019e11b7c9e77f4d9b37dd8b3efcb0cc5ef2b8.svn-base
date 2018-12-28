package com.lyarc.tp.corp.attachfile.service;

import com.lyarc.tp.corp.attachfile.bean.AttachFileBean;
import com.lyarc.tp.corp.attachfile.bean.QueryFileBean;
import com.lyarc.tp.corp.attachfile.dao.AttachFileMapper;
import com.lyarc.tp.corp.attachfile.dto.AttachFileDTO;
import com.lyarc.tp.corp.util.JMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 附件
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月31日 14:47:17
 */
@Service
public class AttachFileService {

    @Autowired
    private AttachFileMapper attachFileMapper;

    /**
     * 方法描述 查询文件列表
     * 
     * @param queryFileBean 查询参数
     * @return List<AttachFileDTO>
     *
     * @author huanghaoqi
     * @date 2017年08月31日 16:07:05
     */
    public List<AttachFileDTO> list(QueryFileBean queryFileBean) {
        List<AttachFileBean> attachFileBeanList = attachFileMapper.list(queryFileBean);
        return JMapperUtils.getDestinations(attachFileBeanList, AttachFileDTO.class, AttachFileBean.class);
    }


    public List<AttachFileDTO> listByFileIds(String[] fileIds) {
        List<AttachFileBean> attachFileBeanList = attachFileMapper.listByIds(fileIds);
        return JMapperUtils.getDestinations(attachFileBeanList, AttachFileDTO.class, AttachFileBean.class);
    }
    
    /**
     * 方法描述 添加
     * 
     * @param attachFileBean 附件对象
     *
     * @author huanghaoqi
     * @date 2017年09月01日 11:12:46
     */
    public void insert(AttachFileBean attachFileBean) {
        attachFileMapper.insert(attachFileBean);
    }

    /**
     * 方法描述 根据文件ID获取详情
     * 
     * @param fileId 文件ID
     * @return AttachFileBean
     *
     * @author huanghaoqi
     * @date 2017年09月01日 12:51:58
     */
    public AttachFileBean getDetail(String fileId) {
        return attachFileMapper.get(fileId);
    }

    /**
     * 方法描述 修改附件
     * 
     * @param fileIds 附件ID集合
     * @param attachFileBean 对象
     *
     * @author huanghaoqi
     * @date 2017年09月04日 11:36:55
     */
    public void update(AttachFileBean attachFileBean, String[] fileIds) {
        attachFileMapper.update(attachFileBean, fileIds);
    }
}
