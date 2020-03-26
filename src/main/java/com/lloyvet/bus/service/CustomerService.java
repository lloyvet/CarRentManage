package com.lloyvet.bus.service;

import com.lloyvet.bus.vo.CustomerVo;
import com.lloyvet.sys.utils.DataGridView;


/**
 * 用户管理服务接口
 */


public interface CustomerService {

    /**
     * 查询所有
     * @param customerVo
     * @return
     */
    DataGridView queryAllCustomer(CustomerVo customerVo);

    /**
     * 添加用户
     * @param customerVo
     */
    void addCustomer(CustomerVo customerVo);

    /**
     * 修改用户
     * @param customerVo
     */
    void updateCustomer(CustomerVo customerVo);
    /**
     * 根据id删除用户
     * @return
     */
    void deleteCustomer(String identity);

    /**
     * 批量删除用户
     */
    void deleteBatchCustomer(String[] identity);

}
