package com.lyarc.tp.corp.quality.Instrument.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.purchase.order.bean.InStockApply;
import com.lyarc.tp.corp.purchase.order.bean.InStockApplyQueryBean;
import com.lyarc.tp.corp.quality.Instrument.bean.Instrument;
import com.lyarc.tp.corp.quality.Instrument.bean.InstrumentQueryBean;
import com.lyarc.tp.corp.quality.Instrument.form.InstrumentForm;
import com.lyarc.tp.corp.warehouse.bean.StockBillDetail;
@Mapper
public interface InstrumentMapper {

    int deleteByPrimaryKey(String instrumentId);

    int insert(InstrumentForm instrumentForm);

    int insertSelective(InstrumentForm instrumentForm);


    /*List<Instrument> selectByPrimaryKey(Instrument instrumentId);*/

    Instrument selectByPrimaryKey(@Param("instrumentId") String instrumentId);

    int updateByPrimaryKey(Instrument record);
    
	Long countInstrument(InstrumentQueryBean queryBean);

	List<Instrument> listInstrument(InstrumentQueryBean queryBean);
	
	Integer createSequence(@Param("currentDate") String currentDate);
	
	/*Integer selectedId(@Param("instrumentId") String instrumentId);*/
	
	
	
	List<Instrument> selectId(Instrument InstrumentId);
	
	List<Instrument> selectMaterId(Instrument materId);
	
	Long countSBD(InstrumentQueryBean queryBean);
	
	List<StockBillDetail> find(InstrumentQueryBean bean);

	Integer updateUseCase(Instrument record);

	int updateByPrimaryKeySelective(Instrument record);

	Integer updateAdd(Instrument instrument);

	
	
	
	
	
	
	
	
	

	
}