package com.lloyvet.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lloyvet.sys.constast.SysConstast;
import com.lloyvet.sys.domain.Menu;
import com.lloyvet.sys.domain.Role;
import com.lloyvet.sys.mapper.MenuMapper;
import com.lloyvet.sys.mapper.RoleMapper;
import com.lloyvet.sys.service.RoleService;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.utils.ResultObj;
import com.lloyvet.sys.utils.TreeNode;
import com.lloyvet.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

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

    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleId) {
        //查询所有的可用菜单
        Menu menu = new Menu();
        menu.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Menu> allMenu = menuMapper.queryAllMenu(menu);
        //根据角色id查询当前角色拥有的菜单
        List<Menu> roleMenu = menuMapper.queryMenuByRoleId(SysConstast.AVAILABLE_TRUE,roleId);
        List<TreeNode> data = new ArrayList<>();
        for (Menu m1 : allMenu) {
            String checkArr = SysConstast.CODE_ZERO+"";
            for (Menu m2 : roleMenu) {
                if(m1.getId()==m2.getId()){
                    checkArr = SysConstast.CODE_ONE+"";
                    break;
                }
            }
            data.add(new TreeNode(m1.getId(),m1.getPid(),m1.getTitle(),m1.getSpread()==SysConstast.SPREAD_TRUE?true:false,checkArr));
        }
        return new DataGridView(data);
    }

    /**
     * 保存角色和菜单的关系
     * @param roleVo
     */
    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        //根据角色id删除sys_role_menu里面的数据
        roleMapper.deleteRoleMenuById(roleVo.getRoleid());
        Integer rid = roleVo.getRoleid();
        Integer[] mids = roleVo.getIds();
        for (Integer mid : mids) {
            roleMapper.insertRoleMenu(rid,mid);
        }
    }
}
