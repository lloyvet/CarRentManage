package com.lloyvet.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lloyvet.sys.constast.SysConstast;
import com.lloyvet.sys.domain.Role;
import com.lloyvet.sys.domain.User;
import com.lloyvet.sys.domain.User;
import com.lloyvet.sys.mapper.RoleMapper;
import com.lloyvet.sys.mapper.UserMapper;
import com.lloyvet.sys.service.UserService;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.vo.RoleVo;
import com.lloyvet.sys.vo.UserVo;
import com.lloyvet.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        Page<Object> page = PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        List<User> users = userMapper.queryAllUser(userVo);
        return new DataGridView(page.getTotal(),users);
    }

    @Override
    public void addUser(UserVo userVo) {
        //设置默认密码
        userVo.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
        //用户类型都为普通用户
        userVo.setType(SysConstast.USER_TYPE_NORMAL);
        userMapper.insertSelective(userVo);
    }

    @Override
    public void updateUser(UserVo userVo) {
        userMapper.updateByPrimaryKeySelective(userVo);
    }

    @Override
    public void deleteUser(Integer userid) {
        //删除用户
        userMapper.deleteByPrimaryKey(userid);
        //删除根据用户id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByUid(userid);
    }

    @Override
    public void deleteBatchUser(Integer[] ids) {
        for (Integer id : ids) {
            deleteUser(id);
        }
    }



    public User login(UserVo userVo) {
        //明文123456
        //生成密文
        String pwd = DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
        userVo.setPwd(pwd);
        return userMapper.login(userVo);
    }

    /**
     * 重置密码
     * @param userid
     */
    @Override
    public void resetUserPwd(Integer userid) {
        User user = new User();
        user.setUserid(userid);
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
        userMapper.updateByPrimaryKeySelective(user);
    }
    @Override
    public DataGridView initUserRole(Integer userid) {
        //查询所有可用的角色
        Role role = new Role();
        role.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Role> roles = roleMapper.queryAllRole( role);
        //根据用户id查询已拥有的角色
        List<Role> roleList = roleMapper.queryRoleByUid(SysConstast.AVAILABLE_TRUE,userid);
        List<Map<String,Object>> data = new ArrayList<>();
        for (Role r1 : roles) {
            Boolean LAY_CHECKED=false;
            for (Role r2  : roleList) {
                if(r1.getRoleid()==r2.getRoleid()){
                    LAY_CHECKED=true;
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("roleid",r1.getRoleid());
            map.put("rolename",r1.getRolename());
            map.put("roledesc",r1.getRoledesc());
            map.put("LAY_CHECKED",LAY_CHECKED);
            data.add(map);
        }

        return new DataGridView(data);
    }

    @Override
    public void saveUserRole(UserVo userVo) {
        Integer userid = userVo.getUserid();
        Integer[] roleIds = userVo.getIds();
        //根据用户id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByUid(userid);
        //保存关系
        if(roleIds!=null && roleIds.length>0){
            for (Integer rid : roleIds) {
                userMapper.insertUserRole(userid,rid);
            }        
        }
    }

}
