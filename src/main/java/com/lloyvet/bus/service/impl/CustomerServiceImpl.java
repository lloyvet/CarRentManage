package com.lloyvet.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lloyvet.bus.domain.Customer;
import com.lloyvet.bus.mapper.CustomerMapper;
import com.lloyvet.bus.service.CustomerService;
import com.lloyvet.bus.vo.CustomerVo;
import com.lloyvet.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public DataGridView queryAllCustomer(CustomerVo customerVo) {
        Page<Object> page = PageHelper.startPage(customerVo.getPage(),customerVo.getLimit());
        List<Customer> customers = customerMapper.queryAllCustomer(customerVo);
        return new DataGridView(page.getTotal(),customers);
    }

    @Override
    public void addCustomer(CustomerVo customerVo) {
        customerVo.setCreatetime(new Date());
        customerMapper.insert(customerVo);
    }

    @Override
    public void updateCustomer(CustomerVo customerVo) {
        customerMapper.updateByPrimaryKeySelective(customerVo);
    }

    @Override
    public void deleteCustomer(String identity) {
        customerMapper.deleteByPrimaryKey(identity);
    }

    @Override
    public void deleteBatchCustomer(String[] identitys) {
        for (String identity : identitys) {
            deleteCustomer(identity);
        }
    }

    @Override
    public Customer queryCustomerByIdentity(String identity) {
        return customerMapper.selectByPrimaryKey(identity);
    }
}
