package com.lloyvet.sys.service;

import com.lloyvet.sys.domain.User;
import com.lloyvet.sys.vo.UserVo;

/**
 * 用户服务接口
 * @author lloyvet
 */
public interface UserService {

    User login(UserVo userVo);

}
