package com.sbm.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sbm.admin.model.Rules;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RulesMapper extends BaseMapper<Rules> {
    int updateBatch(List<Rules> list);

    int updateBatchSelective(List<Rules> list);

    int batchInsert(@Param("list") List<Rules> list);


    List<Rules> selectByList();
}