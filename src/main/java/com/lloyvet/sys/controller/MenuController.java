package com.lloyvet.sys.controller;

import com.lloyvet.sys.constast.SysConstast;
import com.lloyvet.sys.domain.Menu;
import com.lloyvet.sys.domain.User;
import com.lloyvet.sys.service.MenuService;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.utils.ResultObj;
import com.lloyvet.sys.utils.TreeNode;
import com.lloyvet.sys.utils.WebUtils;
import com.lloyvet.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理控制器
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("loadIndexLeftMenuJson")
    public List<TreeNode> loadIndexLeftMenuJson(MenuVo menuVo){
        //得到当前登录的用户对象
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        List<Menu> list = null;
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        if(user.getType()==SysConstast.USER_TYPE_SUPER){
            //超级管理员
            list = menuService.queryAllMenuForList(menuVo);
        }else{
            list = menuService.queryMenuByUserIdForList(menuVo,user.getUserid());
        }
        List<TreeNode> nodes = new ArrayList<>();
        //把list里面的数据放到nodes
        for (Menu menu : list) {
            nodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),menu.getIcon(),menu.getHref(),menu.getSpread()==SysConstast.SPREAD_TRUE?true:false,menu.getTarget()));
        }
        Integer topPid = 1;
        List<TreeNode> treeNodes = new ArrayList<>();
        for (TreeNode n1 : nodes) {
            if(n1.getPid()==topPid){
                treeNodes.add(n1);
            }
            for (TreeNode n2 : nodes) {
                if(n2.getPid()==n1.getId()){
                    n1.getChildren().add(n2);
                }
            }
        }
        return treeNodes;
    }

    /**
     * 加载菜单管理左边的菜单树
     */
    @RequestMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(MenuVo menuVo){
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        List<Menu> list = menuService.queryAllMenuForList(menuVo);
        List<TreeNode> nodes = new ArrayList<>();
        //把list里面的数据放到nodes
        for (Menu menu : list) {
            nodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),menu.getIcon(),menu.getHref(),menu.getSpread()==SysConstast.SPREAD_TRUE?true:false,menu.getTarget()));
        }
        return new DataGridView(nodes);
    }
    /**
     * 加载菜单列表返回DataGridView
     */
    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(MenuVo menuVo){
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        return menuService.queryAllMenu(menuVo);
    }
    /**
     * 添加菜单
     */
    @RequestMapping("addMenu")
    public ResultObj addMenu(MenuVo menuVo){
        try {
            menuService.addMenu(menuVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }
    /**
     * 修改菜单
     */
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(MenuVo menuVo){
        try {
            menuService.updateMenu(menuVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除菜单
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(MenuVo menuVo){
        try {
            menuService.deleteMenu(menuVo);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 根据id判断当前菜单有没有子节点
     * 有返回code>=0
     */
    @RequestMapping("checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(MenuVo menuVo){
        //根据pid查询菜单数量
        Integer count = menuService.queryMenuByPid(menuVo.getId());
        if(count>0){
            return ResultObj.STATUS_TRUE;
        }else {
            return ResultObj.STATUS_FALSE;
        }
    }
}
