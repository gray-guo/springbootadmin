package com.sbm.admin.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sbm.admin.model.Users;
import com.sbm.admin.mapper.UsersMapper;
import com.sbm.admin.model.dto.LoginDTO;
import com.sbm.admin.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sbm.admin.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guoxiaoyong
 * @since 2023-06-17
 */
@Service
@Slf4j
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Override
    public R login(LoginDTO dto) {
        log.info("请求的dto参数 -> {}", dto);
        LambdaQueryWrapper<Users> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Users::getName, dto.getUserName());
        Users row = this.getOne(wrapper);
        if (Objects.nonNull(row)){
            boolean checkpw = BCrypt.checkpw(dto.getPassword(), row.getPassword());
            if (checkpw == true){
                StpUtil.login(row.getId());
                return R.ok("登录成功");
            }
        }
        return R.error("账号密码错误");
    }
}
