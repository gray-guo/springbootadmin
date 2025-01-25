package com.sbm.admin.controller;


import cn.dev33.satoken.annotation.SaIgnore;
import com.sbm.admin.model.Rules;
import com.sbm.admin.pojo.MatchResult;
import com.sbm.admin.util.R;
import org.apache.commons.codec.language.bm.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sbm.admin.service.RulesService;

@RestController
@RequestMapping("/test")
public class TestController {



    @Autowired
    private RulesService rulesService;

    @SaIgnore
    @PostMapping("/test")
    public R test(@RequestBody MatchResult matchResult){

        Integer match = rulesService.match(matchResult);
        R r = new R();
        r.setData(match);
        return r;

    }
}
