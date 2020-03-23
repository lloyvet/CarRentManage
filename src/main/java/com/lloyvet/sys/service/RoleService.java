package com.lloyvet.sys.service;

import com.lloyvet.sys.domain.Menu;
import com.lloyvet.sys.domain.Role;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.vo.RoleVo;

import java.util.List;

/**
 * 角色管理服务接口
 */


public interface RoleService {
    /**
     * 查询所有菜单返回
     */
    public List<Role> queryAllRoleForList(RoleVo roleVo);

    /**
     * 根据用户id查询用户的可用角色
     */
    public List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId);

    /**
     * 查询所有
     * @param roleVo
     * @return
     */
    DataGridView queryAllRole(RoleVo roleVo);

    /**
     * 添加角色
     * @param roleVo
     */
    void addRole(RoleVo roleVo);

    /**
     * 修改角色
     * @param roleVo
     */
    void updateRole(RoleVo roleVo);

    /**
     * 根据id删除角色
     * @param roleid
     * @return
     */
    void deleteRole(Integer roleid);

    /**
     * 批量删除角色
     * @param ids
     */
    void deleteBatchRole(Integer[] ids);
}
