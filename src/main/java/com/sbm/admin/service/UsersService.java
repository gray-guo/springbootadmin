package com.sbm.admin.service;

import com.sbm.admin.model.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sbm.admin.model.dto.LoginDTO;
import com.sbm.admin.util.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author guoxiaoyong
 * @since 2023-06-17
 */
public interface UsersService extends IService<Users> {

    R login(LoginDTO dto);
}
