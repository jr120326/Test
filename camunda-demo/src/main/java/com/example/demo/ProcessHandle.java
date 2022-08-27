package com.example.demo;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ProcessHandle")
@RestController
public class ProcessHandle {

    @Autowired
    RepositoryService repositoryService;

    /**
     * 注册流程,通过name创建流程类，通过id创建流程实例所以不要弄混了
     */
    @RequestMapping("/registerProcesses")
    public void registerProcesses(){
        Deployment deployment = repositoryService.createDeployment().name("studyCamunda").addClasspathResource("BPMN/study.bpmn20.xml").deploy();
        System.out.println("部署ID:" + deployment.getId());
        System.out.println("部署名称" + deployment.getName());
    }



}
