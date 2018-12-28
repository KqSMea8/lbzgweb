package com.lyarc.tp.corp.user.dao;

import com.lyarc.tp.corp.user.bean.User;
import com.lyarc.tp.corp.user.dto.GetUserDTO;
import com.lyarc.tp.corp.user.dto.QueryUserListDTO;
import com.lyarc.tp.corp.user.form.ModifyPasswordForm;
import com.lyarc.tp.corp.user.form.UpdateStatusForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    User selectByEmpId(@Param("empId") String empId);

    User selectExistByEmpId(@Param("empId") String empId, @Param("userId") Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getByLoginNameAndPwd(@Param("loginName") String loginName, @Param("password") String password);

    void modifyPwd(ModifyPasswordForm form);

    User countByLoginName(@Param("loginName") String loginName);

    /**
     *
     * 通过账号名称查询子账号
     *
     *
     */
    GetUserDTO queryByLoginName(String loginName);


    GetUserDTO get(@Param("userId") Integer userId);

    Integer count(Map<String, Object> map);

    /**
     *
     * 获取子账号列表
     *
     * @param map
     * @return
     *
     * @author zhangjingtao
     * @date 2017年9月4日 下午5:48:19
     */
    List<QueryUserListDTO> list(Map<String, Object> map);


    /**
     *
     * 启用/禁用 子账户
     *
     * @param form
     *
     * @author zhangjingtao
     * @date 2017年9月10日 下午2:00:58
     */
    void updateStatus(@RequestBody UpdateStatusForm form);

    Integer removeList(List<Integer> list);
    
}