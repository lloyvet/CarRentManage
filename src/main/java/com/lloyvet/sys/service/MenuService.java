package com.lloyvet.sys.service;

import com.lloyvet.sys.domain.Menu;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.vo.MenuVo;

import java.util.List;

/**
 * 菜单管理服务接口
 */


public interface MenuService {
    /**
     * 查询所有菜单返回
     */
    public List<Menu> queryAllMenuForList(MenuVo menuVo);

    /**
     * 根据用户id查询用户的可用菜单
     */
    public List<Menu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId);

    /**
     * 查询所有菜单
     * @param menuVo
     * @return
     */
    DataGridView queryAllMenu(MenuVo menuVo);

    void addMenu(MenuVo menuVo);

    void updateMenu(MenuVo menuVo);

    Integer queryMenuByPid(Integer id);

    /**
     * 根据id删除菜单
     * @param menuVo
     */
    void deleteMenu(MenuVo menuVo);
}
