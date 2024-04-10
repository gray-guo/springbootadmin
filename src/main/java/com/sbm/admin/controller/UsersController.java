package com.sbm.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.sbm.admin.model.dto.LoginDTO;
import com.sbm.admin.service.UsersService;
import com.sbm.admin.util.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author guoxiaoyong
 * @since 2023-06-17
 */
@RestController
@RequestMapping("/user")
public class UsersController {

    @Resource
    private UsersService usersService;

    /**
     *
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody LoginDTO dto){
        R r = usersService.login(dto);
        return r;

    }

    /** 判断是否登录
     *
     * @return
     */
    @GetMapping("/isLogin")
    public R isLogin(){

        boolean login = StpUtil.isLogin();

        return R.ok("请求返回状态" + login);
    }

}
