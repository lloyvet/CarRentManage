package com.lloyvet.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lloyvet.sys.domain.Menu;
import com.lloyvet.sys.domain.MenuExample;
import com.lloyvet.sys.mapper.MenuMapper;
import com.lloyvet.sys.service.MenuService;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> queryAllMenuForList(MenuVo menuVo) {
        return menuMapper.queryAllMenu(menuVo);
    }

    @Override
    public List<Menu> queryMenuByUserIdForList(MenuVo menuVo, Integer userId) {
        return menuMapper.queryMenuByuid(menuVo.getAvailable(),userId);
    }

    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        Page<Object> page = PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
        List<Menu> data = menuMapper.queryAllMenu(menuVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addMenu(MenuVo menuVo) {
        menuMapper.insertSelective(menuVo);
    }

    @Override
    public void updateMenu(MenuVo menuVo) {
        menuMapper.updateByPrimaryKeySelective(menuVo);
    }

    @Override
    public Integer queryMenuByPid(Integer id) {

        return menuMapper.queryMenuByPid(id);
    }

    @Override
    public void deleteMenu(MenuVo menuVo) {
        //删除菜单表的数据
        menuMapper.deleteByPrimaryKey(menuVo.getId());
        //根据菜单id删除sys_role_menu里面的数据
        menuMapper.deleteRoleMenuByMid(menuVo.getId());
    }
}
