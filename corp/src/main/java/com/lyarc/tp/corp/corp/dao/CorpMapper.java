package com.lyarc.tp.corp.corp.dao;

import com.lyarc.tp.corp.corp.bean.Corp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CorpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Corp record);

    int insertSelective(Corp record);

    Corp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Corp record);

    int updateByPrimaryKey(Corp record);



    /**
     * 查询企业列表
     *
     * @return
     *
     * @author lidoudou
     * @date 2017年8月9日 下午3:34:53
     */
    List<Corp> list();


    /**
     * 通过corpId获取企业信息
     *
     * @param corpId
     * @return
     *
     * @author lidoudou
     * @date 2017年8月9日 下午4:10:27
     */
    Corp get(@Param("corpId") String corpId);


    /**
     * 通过corpId获取统计
     *
     * @param corpId
     * @return
     *
     * @author lidoudou
     * @date 2017年8月9日 下午6:04:38
     */
    Integer count(@Param("corpId") String corpId);


    /**
     * 删除
     *
     * @param corpId
     * @return
     *
     * @author lidoudou
     * @date 2017年8月10日 下午3:48:31
     */
    Integer delete(@Param("corpId") String corpId);
    /**
     *
     * 注销企业
     *
     * @param corpId
     * @return
     *
     * @author jianghan
     * @date 2017年9月21日 下午5:21:50
     */
    Integer clearCorp(@Param("corpId") String corpId);
}