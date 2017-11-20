package com.binggol.controller;


import com.binggol.csp.EmpSP;
import com.binggol.entity.QueryEmp;
import com.binggol.entity.ResultEmp;
import com.binggol.utils.SPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EmpController {

    @Autowired
    private EmpSP empSP;

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String query(QueryEmp queryEmp, ModelMap model, HttpServletRequest request) {
        String empno = request.getParameter("empno");
        if (empno == null || empno == "") {
            queryEmp.setEmpno(null);
        } else {
            queryEmp.setEmpno(Integer.valueOf(empno));
        }
        queryEmp.setEname(request.getParameter("ename"));
        queryEmp.setJob(request.getParameter("job"));
        String mgr = request.getParameter("mgr");
        if (mgr == null || mgr == "") {
            queryEmp.setMgr(null);
        } else {
            queryEmp.setMgr(Integer.valueOf(mgr));
        }
        String sal = request.getParameter("sal");
        if (sal == null || sal == "") {
            queryEmp.setSal(null);
        } else {
            queryEmp.setSal(Integer.valueOf(sal));
        }
        SPResult spResult = empSP.search(queryEmp);
        List<ResultEmp> empList = spResult.getRsList();
        if (empList != null) {
            model.addAttribute("empList", empList);
            return "index";
        } else {
            return "index";
        }
    }


}
