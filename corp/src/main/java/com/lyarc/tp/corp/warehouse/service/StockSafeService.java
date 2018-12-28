package com.lyarc.tp.corp.warehouse.service;

import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.warehouse.bean.StockSafe;
import com.lyarc.tp.corp.warehouse.bean.StockSafeKey;
import com.lyarc.tp.corp.warehouse.bean.StockSafeQueryBean;
import com.lyarc.tp.corp.warehouse.dao.StockSafeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockSafeService {
    @Autowired
    private StockSafeMapper stockSafeMapper;

    public PageResultBean list(StockSafeQueryBean stockSafe) {
        List<StockSafe> stockSafeList = stockSafeMapper.list(stockSafe);
        Long count = stockSafeMapper.count(stockSafe);
        return PageResultBean.success(count,stockSafeList);
    }


    public Integer delete(StockSafeKey stockSafeKey) {
        int rows = stockSafeMapper.deleteByPrimaryKey(stockSafeKey);
        return rows;
    }

    public Integer update(StockSafe stockSafe) {
        int rows = stockSafeMapper.updateByPrimaryKeySelective(stockSafe);
        return rows;
    }

    public StockSafe getStockSafe(StockSafeKey spkey) {
        StockSafe stockSafe = stockSafeMapper.selectByPrimaryKey(spkey);
        return stockSafe;
    }

    @Transactional
    public Integer addOrUpdate(List<StockSafe> stockSafes) {
        for (StockSafe stockSafe : stockSafes) {
            StockSafeKey sk = new StockSafeKey();
            sk.setMaterId(stockSafe.getMaterId());
            sk.setStorageId(stockSafe.getStorageId());

            StockSafe existStockSafe = stockSafeMapper.selectByPrimaryKey(sk);
            if(existStockSafe == null){
                stockSafeMapper.insertSelective(stockSafe);
            }else{
                stockSafeMapper.updateByPrimaryKeySelective(stockSafe);
            }
        }
        return 1;
    }
}
