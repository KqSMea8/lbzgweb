package com.lyarc.tp.corp.warehouse.service;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.warehouse.bean.StockOpertype;
import com.lyarc.tp.corp.warehouse.bean.StockOpertypeQueryBean;
import com.lyarc.tp.corp.warehouse.dao.StockOpertypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockOpertypeService {
    @Autowired
    private StockOpertypeMapper stockOpertypeMapper;

    public List<StockOpertype> list(StockOpertypeQueryBean queryBean) {
        List<StockOpertype> stockOpertypeList = stockOpertypeMapper.list(queryBean);
        return stockOpertypeList;
    }


    public Integer delete(String stockOpertypeId) {
        int rows = stockOpertypeMapper.deleteByPrimaryKey(stockOpertypeId);
        return rows;
    }

    public Integer update(StockOpertype stockOpertype) {
        int rows = stockOpertypeMapper.updateByPrimaryKeySelective(stockOpertype);
        return rows;
    }

    public Integer add(StockOpertype stockOpertype) {
        //校验id 是否重复
        String stockOpertypeId = stockOpertype.getOperId();
        StockOpertype existStockOpertype = stockOpertypeMapper.selectByPrimaryKey(stockOpertypeId);
        if(existStockOpertype != null){
            throw new CorpException(ResultVo.failure("操作类型代码已存在"));
        }
        int row = stockOpertypeMapper.insertSelective(stockOpertype);
        return row;
    }

    public StockOpertype getStockOpertype(String stockOpertypeId) {
        StockOpertype stockOpertype = stockOpertypeMapper.selectByPrimaryKey(stockOpertypeId);
        return stockOpertype;
    }

}
