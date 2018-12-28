package com.lyarc.tp.corp.production.service;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lyarc.tp.corp.common.DateUtil;
import com.lyarc.tp.corp.production.bean.OutStockApply;
import com.lyarc.tp.corp.production.bean.OutStockApplyDetail;
import com.lyarc.tp.corp.production.bean.OutStockApplyQueryBean;
import com.lyarc.tp.corp.production.dao.OutStockApplyDetailMapper;
import com.lyarc.tp.corp.production.dao.OutStockApplyMapper;


/**
 * 仓库清单业务实现类
 * 
 * @author shiyong
 *
 */
@Service
public class OutStockApplyService {
	@Autowired
	private OutStockApplyMapper outStockApplyMapper;
	@Autowired
	private OutStockApplyDetailMapper outStockApplyDetailMapper;

	 public List<OutStockApply> list(OutStockApplyQueryBean queryBean) {
	        List<OutStockApply> outStockApplyList = outStockApplyMapper.list(queryBean);
	        return outStockApplyList;
	    }

	    public Long count(OutStockApplyQueryBean queryBean) {
	        Long count =  outStockApplyMapper.count(queryBean);
	        return count;
	    }

	    @Transactional
	    public Integer add(OutStockApply outStockApply) {	        
	        String outStockApplyId = getOutStockApplyId();
	        outStockApply.setOutStockApplyId(outStockApplyId);
	        int row = outStockApplyMapper.insertSelective(outStockApply);

	        //插入明细
	        List<OutStockApplyDetail> detailList = outStockApply.getDetailList();
	        detailList.forEach(d->{
	            d.setOutStockApplyId(outStockApplyId);	            
	            outStockApplyDetailMapper.insertSelective(d);
	        });
	        return row;
	    }

	    public String getOutStockApplyId(){
	        //获取当前日期
	        String currentDate = DateUtil.getDateStr(new Date(), "yyyyMMdd");
	        Integer sequence = 0;
	        synchronized (this) {
	            sequence = outStockApplyMapper.createSequence(currentDate);
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
	        return currentDate + nf.format(sequence);
	    }
	    
	    public OutStockApply getOutStockApply(String outStockApplyId) {
	        OutStockApply outStockApply = outStockApplyMapper.selectAndDetail(outStockApplyId);
	        return outStockApply;
	    }	

	// 更新
	@Transactional
	public Integer update(OutStockApply outStockApply) {
		//更新数据
        int row = outStockApplyMapper.update(outStockApply); 
        String outStockApplyId=outStockApply.getOutStockApplyId();
        
        //删除旧数据
        outStockApplyDetailMapper.delete(outStockApplyId);
        
        //插入明细
        List<OutStockApplyDetail> detailList = outStockApply.getDetailList();
        detailList.forEach(d->{ 
        	d.setOutStockApplyId(outStockApplyId);
            outStockApplyDetailMapper.insertSelective(d);
        });
        return row;
	}

	// 删除
	@Transactional
	public Integer delete(String outStockApplyId) {
		Integer id = outStockApplyMapper.delete(outStockApplyId);
		 outStockApplyDetailMapper.delete(outStockApplyId);
		return id;
	}
	
	public Integer selectedId(String outStockApplyId) {
		return outStockApplyMapper.selectedId(outStockApplyId);

	}
	public Integer rejectStatus(OutStockApply outStockApply) {
		return outStockApplyMapper.rejectStatus(outStockApply);
	}

	public Integer submitStatus(String outStockApplyId) {
		
		return outStockApplyMapper.submitStatus(outStockApplyId);
	} 

}
