package com.lyarc.tp.corp.quality.Instrument.service;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.common.DateUtil;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.quality.Instrument.bean.Instrument;
import com.lyarc.tp.corp.quality.Instrument.bean.InstrumentQueryBean;
import com.lyarc.tp.corp.quality.Instrument.dao.InstrumentMapper;
import com.lyarc.tp.corp.quality.Instrument.form.InstrumentForm;
import com.lyarc.tp.corp.util.JMapperUtils;
import com.lyarc.tp.corp.warehouse.bean.StockBillDetail;
import com.lyarc.tp.corp.warehouse.bean.StockOpertype;

@Service
public class InstrumentService {
	@Autowired
	private InstrumentMapper instrumentMapper;
	
	/**
	 * 	编辑计量器具
	 */
	@Transactional
	public Integer updateByPmaryKey(Instrument instrument) {
		 /*Instrument instrument= JMapperUtils.getDestination(instrumentForm, Instrument.class, InstrumentForm.class);*/
		/*instrument.setUpdateTime();*/
        int rows = instrumentMapper.updateByPrimaryKeySelective(instrument);
        /*String InstrumentId = instrument.getInstrumentId();*/
		return rows;
	}
	
	/**
	 * 	计量器具列表
	 */
	public List<Instrument> list(InstrumentQueryBean queryBean) {
		if(queryBean.getInstrumentIdStr()!=null) {
			queryBean.setInstrumentIdStrArr(queryBean.getInstrumentIdStr().split(","));
		}

        List<Instrument> instrumentList = instrumentMapper.listInstrument(queryBean);
        return instrumentList;
    }

    public Long count(InstrumentQueryBean queryBean) {
        Long count =  instrumentMapper.countInstrument(queryBean);
        return count;
    }


    public String getInstrumentId(){
        //获取当前日期
        String currentDate = DateUtil.getDateStr(new Date(), "yyyy-MM-dd");
        Integer sequence = 0;
        synchronized (this) {
            sequence = instrumentMapper.createSequence(currentDate);
        }
        //得到一个NumberFormat的实例
        NumberFormat nf = NumberFormat.getInstance();
        //设置是否使用分组
        nf.setGroupingUsed(false);
        //设置最大整数位数
        nf.setMaximumIntegerDigits(4);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(4);
        //输出测试语句
        return "C"+currentDate + nf.format(sequence);
    }
    /**
     *	根据计量器具编号查询
     */
    public List<Instrument> selectId(Instrument instrumentId){
    	List<Instrument> listInstrument = instrumentMapper.selectId(instrumentId);
    	return listInstrument;
    }
	
    /**
     * 	添加计量器具
     */
    public Integer add(InstrumentForm instrumentForm) {
    	String instrumentId = instrumentForm.getInstrumentId();
    	Instrument existinstrumentForm = instrumentMapper.selectByPrimaryKey(instrumentId);
        if(existinstrumentForm != null){
            throw new CorpException(ResultVo.failure("操作类型代码已存在"));
        }
    	int row = instrumentMapper.insertSelective(instrumentForm);
    	return row;	
    }

    /**
     * 	选择物料
     */
	public List<StockBillDetail> find(InstrumentQueryBean bean){
		List<StockBillDetail> listStockBillDetail = instrumentMapper.find(bean);
		return listStockBillDetail;
	}
	
	public Long countSBD(InstrumentQueryBean queryBean) {
        Long countSBD =  instrumentMapper.countSBD(queryBean);
        return countSBD;
    }

	/**
	 *	报废状态修改
	 * @Return 
	 */
	public Integer updateUseCase(Instrument record) {
		record.setUseCase("2");
		Integer id = instrumentMapper.updateUseCase(record);
		return id;
	}

	public Integer updateByPrimaryKeySelective(Instrument record) {
		Integer row = instrumentMapper.updateByPrimaryKeySelective(record);
		return row;
		
	}

	
}
