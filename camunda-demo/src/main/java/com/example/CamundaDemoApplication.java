package com.example;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@SpringBootApplication
@EnableProcessApplication
public class CamundaDemoApplication implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(CamundaDemoApplication.class);

    @Autowired
    RepositoryService repositoryService;

    public static void main(String[] args) {
        SpringApplication.run(CamundaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().active().latestVersion().list();
        logger.info("> 处于激活状态的最新版本的流程定义数量: " + processDefinitionList.size());
        for (ProcessDefinition pd : processDefinitionList) {
            logger.info("\t ===> Process definition: " + pd.getKey() + " 版本：" + pd.getVersion());

        }
    }
}
