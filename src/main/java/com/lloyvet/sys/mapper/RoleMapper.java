package com.lloyvet.sys.mapper;

import com.lloyvet.sys.domain.Role;
import com.lloyvet.sys.domain.RoleExample;
import java.util.List;

import com.lloyvet.sys.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer roleid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> queryAllRole(RoleVo roleVo);
    //根据角色id删除sys_role_user里面的数据
    void deleteRoleUserById(Integer roleid);
    //根据角色id删除sys_role_menu里面的数据
    void deleteRoleMenuById(Integer roleid);
}