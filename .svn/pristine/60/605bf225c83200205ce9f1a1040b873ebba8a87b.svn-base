package com.lyarc.tp.corp.system.customer.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.system.customer.bean.Customer;
import com.lyarc.tp.corp.system.customer.bean.CustomerQueryBean;
import com.lyarc.tp.corp.system.customer.service.CustomerService;
import com.lyarc.tp.corp.system.material.dto.CustomerDto;

@ResponseBody
@Controller
@RequestMapping(path = "/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	@ResponseBody
	public ResultVo<PageResultBean> list(@RequestBody CustomerQueryBean queryBean) {
		Integer page = queryBean.getPage();
		Integer pageSize = queryBean.getPageSize();
		if (page != null && pageSize != null) {
			queryBean.setOffset((page - 1) * pageSize);
		}

		List<CustomerDto> customerList = customerService.list(queryBean);
		Long count = customerService.count(queryBean);
		return ResultVo.success(PageResultBean.success(count, customerList));
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResultVo<Integer> add(@Valid @RequestBody Customer customer) {

		LoginUser loginUser = UserHolder.getUser();
		customer.setCreator(loginUser.getUserId());
		Integer id = customerService.add(customer);
		return ResultVo.success(id);
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResultVo<Integer> update(@Valid @RequestBody Customer customer) {

		customer.setUpdateTime(new Date());
		Integer rows = customerService.update(customer);
		return ResultVo.success(rows);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Integer> delete(@RequestParam("customerId") String customerId) {
		Integer rows = customerService.delete(customerId);
		return ResultVo.success(rows);
	}

	@RequestMapping("/get")
	@ResponseBody
	public ResultVo<Customer> detail(@RequestParam("customerId") String customerId) {
		Customer customer = customerService.getCustomer(customerId);
		return ResultVo.success(customer);
	}

	@RequestMapping("/search")
	@ResponseBody
	public ResultVo<List<CustomerDto>> search(@RequestBody CustomerQueryBean queryBean) {
		List<CustomerDto> customerList = customerService.list(queryBean);
		return ResultVo.success(customerList);
	}

}
