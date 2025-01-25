package com.sbm.admin.service.impl;

import com.sbm.admin.pojo.MatchResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sbm.admin.model.Rules;
import com.sbm.admin.mapper.RulesMapper;
import com.sbm.admin.service.RulesService;

@Slf4j
@Service
public class RulesServiceImpl extends ServiceImpl<RulesMapper, Rules> implements RulesService {

    @Autowired
    private RulesMapper rulesMapper;

    @Override
    public int updateBatch(List<Rules> list) {
        return baseMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Rules> list) {
        return baseMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Rules> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public List<Rules> getList() {
        return rulesMapper.selectByList();
    }

    @Override
    public Integer match(MatchResult matchResult) {
        List<Rules> list = this.getList();
        Map<Integer, List<Rules>> groupByMap = list.stream().collect(Collectors.groupingBy(Rules::getGroupId));

        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        for (Map.Entry<Integer, List<Rules>> entry : groupByMap.entrySet()) {
            Integer key = entry.getKey();
            List<Rules> listRules = entry.getValue();
            String resultString = generateDroolsRule(listRules);

            // 将生成的规则字符串写入 .drl 文件
            String drlFilePath = "src/main/resources/droolsrules/dynamic_rules_" + key + ".drl";
            writeDrlFile(drlFilePath, resultString);

            // 将 .drl 文件添加到 KieFileSystem
            kieFileSystem.write(drlFilePath, kieServices.getResources().newFileSystemResource(drlFilePath));
        }

        // 构建 KieContainer
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            log.error(results.getMessages().toString());
            throw new IllegalStateException("### errors ###");
        }
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());

        // 获取 KieSession 并执行规则匹配
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(matchResult);
        kieSession.fireAllRules();

        return matchResult.getGroupId();
    }

    private String generateDroolsRule(List<Rules> rules) {
        if (rules == null || rules.size() == 0) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();

        // 添加导入语句
        stringBuilder.append("import com.sbm.admin.pojo.MatchResult; \n");
        stringBuilder.append("import com.sbm.admin.model.Rules; \n");
        stringBuilder.append(String.format("rule \"ksession-rules %s\" \n", rules.get(0).getGroupId()));
        stringBuilder.append("when\n");
        stringBuilder.append("    $matchresult: MatchResult(");

        for (int i = 0; i < rules.size(); i++) {
            Rules rule = rules.get(i);
            String andSymbol;
            if (i == 0) {
                andSymbol = Strings.EMPTY;
            } else {
                andSymbol = " && ";
            }
            stringBuilder.append(andSymbol + rule.getKey() + " " + rule.getCondition() + " " + rule.getValue());
        }

        stringBuilder.append(") \n");
        stringBuilder.append("then \n");
        stringBuilder.append("    $matchresult.setGroupId(" + rules.get(0).getGroupId() + "); \n");
        stringBuilder.append("end");

        String resultString = stringBuilder.toString();
        log.info("组装完成的报文 {}", resultString);
        return resultString;
    }

    private void writeDrlFile(String filePath, String content) {
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            log.error("Failed to write DRL file", e);
        }
    }
}