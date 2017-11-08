package com.binggol.controller;


import com.binggol.entity.Test;
import com.binggol.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @Autowired
    private TestService testService;

    @RequestMapping("/list")
    public List<Test> getStus(){
        System.out.println("结果："+testService.getList());
        return testService.getList();
    }

}
