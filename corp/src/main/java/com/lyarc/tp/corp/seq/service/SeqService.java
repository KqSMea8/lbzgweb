package com.lyarc.tp.corp.seq.service;

import java.text.NumberFormat; 
import java.text.ParseException; 
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.common.DateUtil;
import com.lyarc.tp.corp.seq.dao.SeqMapper;

@Service
public class SeqService {
	@Autowired
	private SeqMapper seqMapper;
	
	private Integer createSeq(String key){
		synchronized (this) {
			return seqMapper.createSeq(key);
		}
    }
	 
	/**
	 * 生成仓库单据号
	 * @param operType 1.入库 2.出库 3.移库 4.调库
	 * @param billDate 操作日期
	 * @return I201811110001
	 */
	public String getStockBillId(Integer operType, Date billDate) {// 单号调用存储过程，按一定规则，新生成
		// 获取当前日期
		String currentDate = DateUtil.getDateStr(billDate, "yyyyMMdd");
		Integer sequence = createSeq(currentDate+"_t_stock_bill");
		// 得到一个NumberFormat的实例
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(4);
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(4);
		String billNo = currentDate + nf.format(sequence);
		if (operType == 1) {
			return 'I' + billNo;
		}
		if (operType == 2) {
			return 'O' + billNo;
		}
		if (operType == 3) {
			return 'M' + billNo;
		}
		if (operType == 4) {
			return 'A' + billNo;
		}
		return billNo;
	}
	
	/**
	 * 生成单据号
	 * @param billDate	单据时间
	 * @param module	单据模块
	 * @param prefix	单据头-可为空
	 * @return			单据号
	 */
    public String getBillId(Date billDate,String module,String prefix){
        //获取当前日期
        String currentDate = DateUtil.getDateStr(billDate, "yyyyMMdd");
        Integer sequence = createSeq(currentDate+"_"+module);
        //得到一个NumberFormat的实例
        NumberFormat nf = NumberFormat.getInstance();
        //设置是否使用分组
        nf.setGroupingUsed(false);
        //设置最大整数位数
        nf.setMaximumIntegerDigits(4);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(4);
        //输出测试语句
        return prefix + currentDate + nf.format(sequence); 
    }
    
	/**
	 * 生成流水号
	 * @param module	模块
	 * @param prefix	流水头-可为空
	 * @return			流水号
	 * @throws ParseException 
	 */
    public  String getSerial(String module,Integer seqLength){
        Integer sequence = createSeq(module);
        //得到一个NumberFormat的实例
        NumberFormat nf = NumberFormat.getInstance();
        //设置是否使用分组
        nf.setGroupingUsed(false);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(seqLength);
        //输出测试语句
        return nf.format(sequence);
    } 
}
