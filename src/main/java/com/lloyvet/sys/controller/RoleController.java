package com.lloyvet.sys.controller;

import com.lloyvet.sys.constast.SysConstast;
import com.lloyvet.sys.domain.Role;
import com.lloyvet.sys.domain.User;
import com.lloyvet.sys.service.RoleService;
import com.lloyvet.sys.service.RoleService;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.utils.ResultObj;
import com.lloyvet.sys.utils.TreeNode;
import com.lloyvet.sys.utils.WebUtils;
import com.lloyvet.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 *  角色管理控制器
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 加载 角色列表返回DataGridView
     */
    @RequestMapping("loadAllRole")
    public DataGridView loadAllRole(RoleVo roleVo){
        roleVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        return roleService.queryAllRole(roleVo);
    }
    /**
     * 添加角色
     */
    @RequestMapping("addRole")
    public ResultObj addRole(RoleVo roleVo){
        try {
            roleService.addRole(roleVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }
    /**
     * 修改角色
     */
    @RequestMapping("updateRole")
    public ResultObj updateRole(RoleVo roleVo){
        try {
            roleService.updateRole(roleVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除一个角色
     */
    @RequestMapping("deleteRole")
    public ResultObj deleteRole(RoleVo roleVo){
        try {
            roleService.deleteRole(roleVo.getRoleid());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 删除多个角色
     */
    @RequestMapping("deleteBatchRole")
    public ResultObj deleteBatchRole(RoleVo roleVo){
        try {
            roleService.deleteBatchRole(roleVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 初始化角色分配菜单的json
     */
    @RequestMapping("initRoleMenuTreeJson")
    public DataGridView initRoleMenuTreeJson(Integer roleId){
        return roleService.initRoleMenuTreeJson(roleId);
    }
    /**
     * 保存角色分配
     */
    @RequestMapping("saveRoleMenu")
    public ResultObj saveRoleMenu(RoleVo roleVo){
        try {
            roleService.saveRoleMenu(roleVo);
            return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }
}
