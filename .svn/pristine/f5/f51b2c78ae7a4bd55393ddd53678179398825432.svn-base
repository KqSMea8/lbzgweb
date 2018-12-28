package com.lyarc.tp.corp.corp.service;

import com.lyarc.tp.corp.corp.bean.Corp;
import com.lyarc.tp.corp.corp.dao.CorpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CorpService {

    @Autowired
    private CorpMapper mapper;

    /**
     * 查询企业列表
     *
     * @return
     * 
     * @author lidoudou
     * @date 2017年8月9日 下午3:35:34
     */
    public List<Corp> list() {
        return mapper.list();
    }

    /**
     * 添加企业
     *
     * @param corp
     * @return
     * 
     * @author lidoudou
     * @date 2017年8月9日 下午3:35:55
     */
    public Integer add(Corp corp) {
        return mapper.insertSelective(corp);
    }

    /**
     * 更新企业信息
     *
     * @param corp
     * @return
     * 
     * @author lidoudou
     * @date 2017年8月9日 下午4:10:56
     */
    public Integer update(Corp corp) {
        return mapper.updateByPrimaryKeySelective(corp);
    }
    
    /**
     * 删除企业信息
     *
     * @param corpId
     * @return
     * 
     * @author lidoudou
     * @date 2017年8月10日 下午3:48:03
     */
    public Integer delete(String corpId){
        return mapper.delete(corpId);
    }
    
    /**
     * 获取企业信息
     *
     * @param corpId
     * @return
     * 
     * @author lidoudou
     * @date 2017年9月1日 上午10:25:07
     */
    public Corp get(String corpId){
        return mapper.get(corpId);
    }
    /**
     * 
     * 注销企业
     *
     * @param corpId
     * @return
     * 
     * @author jianghan
     * @date 2017年9月21日 下午5:22:51
     */
    public void clear(String corpId){
        mapper.clearCorp(corpId);
    }
}
