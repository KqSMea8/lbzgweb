package com.lyarc.tp.corp.purchase.order.service;

import com.lyarc.tp.corp.common.DateUtil;
import com.lyarc.tp.corp.purchase.order.bean.InStockApply;
import com.lyarc.tp.corp.purchase.order.bean.InStockApplyDetail;
import com.lyarc.tp.corp.purchase.order.bean.InStockApplyQueryBean;
import com.lyarc.tp.corp.purchase.order.dao.InStockApplyDetailMapper;
import com.lyarc.tp.corp.purchase.order.dao.InStockApplyMapper;

import com.lyarc.tp.corp.purchase.order.form.InStockApplyDetailForm;
import com.lyarc.tp.corp.purchase.order.form.InStockApplyForm;
import com.lyarc.tp.corp.purchase.order.form.InStockApplyStatusRejectForm;
import com.lyarc.tp.corp.util.JMapperUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

@Service
public class InStockApplyService {
    @Autowired
    private InStockApplyMapper inStockApplyMapper;
    @Autowired
    private InStockApplyDetailMapper inStockApplyDetailMapper;

    public List<InStockApply> list(InStockApplyQueryBean queryBean) {
        List<InStockApply> inStockApplyList = inStockApplyMapper.list(queryBean);
        return inStockApplyList;
    }

    public Long count(InStockApplyQueryBean queryBean) {
        Long count =  inStockApplyMapper.count(queryBean);
        return count;
    }
    
    @Transactional
    public Integer add(InStockApplyForm inStockApplyForm) {
        InStockApply inStockApply = JMapperUtils.getDestination(inStockApplyForm, InStockApply.class, InStockApplyForm.class);
        String inStockApplyId = getInStockApplyId();
        inStockApply.setInStockApplyId(inStockApplyId);
        int row = inStockApplyMapper.insertSelective(inStockApply);

        //插入明细
        List<InStockApplyDetailForm> detailList = inStockApplyForm.getDetailList();
        detailList.forEach(d->{
            d.setInStockApplyId(inStockApplyId);
            InStockApplyDetail inStockApplyDetail =
                    JMapperUtils.getDestination(d, InStockApplyDetail.class, InStockApplyDetailForm.class);
            inStockApplyDetail.setInStockApplyId(inStockApplyId);
            inStockApplyDetail.setStatus(1);
            inStockApplyDetailMapper.insertSelective(inStockApplyDetail);
        });
        return row;
    }

    public String getInStockApplyId(){
        //获取当前日期
        String currentDate = DateUtil.getDateStr(new Date(), "yyyyMMdd");
        Integer sequence = 0;
        synchronized (this) {
            sequence = inStockApplyMapper.createSequence(currentDate);
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

    /**
     *
     * @description 获取询价单详情
     * @param
     * @return
     * @author wangting
     * @date 2018/7/27 0027 16:39
     *
     */
    public InStockApply getInStockApply(String inStockApplyId) {
        InStockApply inStockApply = inStockApplyMapper.selectAndDetail(inStockApplyId);
        return inStockApply;
    }

	public Integer selectedId(String inStockApplyId) {
		return inStockApplyMapper.selectedId(inStockApplyId);

	}
	public Integer rejectStatus(InStockApplyStatusRejectForm form) {
		return inStockApplyMapper.rejectStatus(form);
	} 
}
