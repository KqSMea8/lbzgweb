package com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean.UnqualifiedPeoductBillQueryBean;
import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean.UnqualifiedProductBill;
import com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean.UnqualifiedProductBillKey;
@Mapper
public interface UnqualifiedProductBillMapper {
    int deleteByPrimaryKey(UnqualifiedProductBillKey key);

    int insert(UnqualifiedProductBill record);

    int insertSelective(UnqualifiedProductBill record);

    UnqualifiedProductBill selectByPrimaryKey(UnqualifiedProductBillKey key);

    int updateByPrimaryKeySelective(UnqualifiedProductBill record);

    int updateByPrimaryKey(UnqualifiedProductBill record);

	Long count(UnqualifiedPeoductBillQueryBean queryBean);

	List<UnqualifiedPeoductBillQueryBean> list(UnqualifiedPeoductBillQueryBean queryBean);
}