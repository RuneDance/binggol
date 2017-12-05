package com.binggol.controller;

import com.binggol.csp.EmpSP;
import com.binggol.entity.QueryEmp;
import com.binggol.entity.ResultEmp;
import com.binggol.utils.SPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private EmpSP empSP;

    QueryEmp queryEmp = new QueryEmp();

    @RequestMapping(value = "/edit")
    public String edit() {
        return "edit";
    }

    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/full-home")
    public String full_home() {
        return "full-home";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        SPResult spResult = empSP.search(queryEmp);
        List<ResultEmp> empList = spResult.getRsList();
        if (empList != null) {
            model.addAttribute("empList", empList);
            return "/test";
        } else {
            return "/test";
        }

    }
}
