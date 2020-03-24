package com.lloyvet.sys.service;

import com.lloyvet.sys.domain.User;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.vo.UserVo;
import com.lloyvet.sys.vo.UserVo;

/**
 * 用户服务接口
 * @author lloyvet
 */
public interface UserService {
    /**
     * 查询所有
     * @param userVo
     * @return
     */
    DataGridView queryAllUser(UserVo userVo);

    /**
     * 添加用户
     * @param userVo
     */
    void addUser(UserVo userVo);

    /**
     * 修改用户
     * @param userVo
     */
    void updateUser(UserVo userVo);

    /**
     * 根据id删除用户
     * @param userid
     * @return
     */
    void deleteUser(Integer userid);

    /**
     * 用户登录
     * @param userVo
     * @return
     */
    User login(UserVo userVo);
    /**
     * 重置密码
     */
    void resetUserPwd(Integer userid);
    /**
     * 批量删除用户
     * @param ids
     */
    void deleteBatchUser(Integer[] ids);

    /**
     * 加载用户管理分配角色的数据
     * @param userid
     * @return
     */
    DataGridView initUserRole(Integer userid);

    /**
     * 保存用户和角色的关系
     * @param userVo
     */
    void saveUserRole(UserVo userVo);
}
