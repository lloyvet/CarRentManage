package com.lloyvet.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lloyvet.sys.domain.Menu;
import com.lloyvet.sys.domain.Role;
import com.lloyvet.sys.mapper.RoleMapper;
import com.lloyvet.sys.service.RoleService;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryAllRoleForList(RoleVo roleVo) {
        return roleMapper.queryAllRole(roleVo);
    }

    @Override
    public List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId) {
        return roleMapper.queryAllRole(roleVo);
    }

    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        Page<Object> page = PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
        List<Role> roles = roleMapper.queryAllRole(roleVo);
        return new DataGridView(page.getTotal(),roles);
    }

    @Override
    public void addRole(RoleVo roleVo) {
        roleMapper.insertSelective(roleVo);
    }

    @Override
    public void updateRole(RoleVo roleVo) {
        roleMapper.updateByPrimaryKeySelective(roleVo);
    }

    @Override
    public void deleteRole(Integer roleid) {
        //删除角色表数据
        roleMapper.deleteByPrimaryKey(roleid);
        //根据角色id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserById(roleid);
        //根据角色id删除sys_role_menu里面的数据
        roleMapper.deleteRoleMenuById(roleid);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer id : ids) {
            deleteRole(id);
        }
    }
}
