package com.sbm.admin.service;

import java.util.List;
import com.sbm.admin.model.Rules;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sbm.admin.pojo.MatchResult;

public interface RulesService extends IService<Rules>{


    int updateBatch(List<Rules> list);

    int updateBatchSelective(List<Rules> list);

    int batchInsert(List<Rules> list);


    List<Rules> getList();

    Integer match(MatchResult matchResult);




}
