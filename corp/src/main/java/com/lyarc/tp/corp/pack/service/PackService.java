package com.lyarc.tp.corp.pack.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.pack.bean.PackBean;
import com.lyarc.tp.corp.pack.bean.PackDetailBean;
import com.lyarc.tp.corp.pack.bean.PackQueryBean;
import com.lyarc.tp.corp.pack.dao.PackDetailMapper;
import com.lyarc.tp.corp.pack.dao.PackMapper;
import com.lyarc.tp.corp.seq.service.SeqService;

@Service
public class PackService {
	
	@Autowired
	private PackMapper packMapper;
	@Autowired
	private PackDetailMapper packDetailMapper;
    @Autowired
    private SeqService seqService;
	
	@SuppressWarnings("rawtypes")
	public PageResultBean query(PackQueryBean packQueryBean){
		List<PackBean> packList = packMapper.query(packQueryBean);
		Long packCount = packMapper.count(packQueryBean);
		return PageResultBean.success(packCount, packList);
	}
	
	public PackBean get(String packId) {
		PackBean packBean = packMapper.get(packId);
		packBean.setDetailList(packDetailMapper.query(packId));
		return packBean;
	}
	
	public Integer add(PackBean packBean) {
		if(null==packBean.getDepartment()) {
			return 0;
		}
		if(null==packBean.getOperator()||"".equals(packBean.getOperator())) {
			return 0;
		}
		LoginUser loginUser = UserHolder.getUser();
		packBean.setCreator(loginUser.getUserId());
		//String packId = getContractId();
		//packBean.setPackId(packId);
		
		Integer packNum = packMapper.add(packBean);
		
		List<PackDetailBean> packDetailList = packBean.getDetailList();
		packDetailList.stream().forEach(e->{
			if(null==e.getMaterId()||"".equals(e.getMaterId())) {
				return;
			}
			if(null==e.getContractId()||"".equals(e.getContractId())) {
				return;
			}
			if(null==e.getCasting()||"".equals(e.getCasting())) {
				return;
			}
			if(null==e.getBarcode()||"".equals(e.getBarcode())) {
				return;
			}
			if(null==e.getQuantity()) {
				return;
			}
			
			e.setPackId(packBean.getPackId());
			e.setCreator(loginUser.getUserId());
			packDetailMapper.add(e);
		});
		return packNum;
	}
	
	public Integer update(PackBean packBean) {
		if(null==packBean.getDepartment()) {
			return 0;
		}
		if(null==packBean.getOperator()||"".equals(packBean.getOperator())) {
			return 0;
		}
		packBean.setUpdateTime(new Date());
		Integer packNum = packMapper.update(packBean);
		
		List<PackDetailBean> oldPackDetailList = packDetailMapper.query(packBean.getPackId());
		List<PackDetailBean> packDetailList = packBean.getDetailList();
		Map<Integer,PackDetailBean> map = new HashMap<Integer,PackDetailBean>();
		
		if(oldPackDetailList.size()<=packDetailList.size()) {
			for (PackDetailBean oldPackDetailBean : oldPackDetailList) {
				map.put(oldPackDetailBean.getId(), oldPackDetailBean);
			}
			for (PackDetailBean packDetailBean : packDetailList) {
				if(map.containsKey(packDetailBean.getId())) {
					packDetailBean.setUpdateTime(new Date());
					packDetailMapper.update(packDetailBean);
				}else {
					LoginUser loginUser = UserHolder.getUser();
					packDetailBean.setPackId(packBean.getPackId());
					packDetailBean.setCreator(loginUser.getUserId());
					packDetailMapper.add(packDetailBean);
				}
			}
		}else {
			for (PackDetailBean packDetailBean : packDetailList) {
				map.put(packDetailBean.getId(), packDetailBean);
			}
			for (PackDetailBean oldPackDetailBean : oldPackDetailList) {
				if(map.containsKey(oldPackDetailBean.getId())) {
					oldPackDetailBean.setUpdateTime(new Date());
					packDetailMapper.update(oldPackDetailBean);
				}else {
					packDetailMapper.del(oldPackDetailBean.getId());
				}
			}
		}
		
		return packNum;
	}
	
	public Integer del(String packId) {
		PackBean packBean = packMapper.get(packId);
		packBean.setDetailList(packDetailMapper.query(packId));
		List<PackDetailBean> packDetailList = packBean.getDetailList();
		if(null!=packDetailList && 0<packDetailList.size()) {
			packDetailList.stream().forEach(e->{
				packDetailMapper.del(e.getId());
			});
		}
		return packMapper.del(packId);
	}
	
	public Integer updateStatus(PackBean packBean) {
		if(null==packBean.getDepartment()) {
			return 0;
		}
		if(null==packBean.getOperator()||"".equals(packBean.getOperator())) {
			return 0;
		}
		return packMapper.add(packBean);
	}
	
	public String getContractId() {
		 return seqService.getBillId(new Date(), "t_pack", "");
	}
	
}
