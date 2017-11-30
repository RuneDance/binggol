package com.binggol.controller;


import com.binggol.csp.EmpSP;
import com.binggol.entity.QueryEmp;
import com.binggol.entity.ResultEmp;
import com.binggol.utils.SPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询
     *
     * @param queryEmp
     * @param model
     * @param request
     * @return
     */
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
        model.addAttribute("empList", empList);
        return "index";
    }

    /**
     * 新增
     *
     * @param resultEmp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public SPResult add(@RequestBody ResultEmp resultEmp) {
        //ResultEmp rEmp =new ResultEmp();

        //resultEmp.getHiredate();
        SPResult spResult = empSP.add(resultEmp);
        if (spResult == null) {
            /*int retcode = spResult.getRetCode();
            String msg = spResult.getMsg();*/
            new Exception("服务器发生异常！");
        }
        return spResult;
    }

    /**
     * 更新
     *
     * @param resultEmp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SPResult update(@RequestBody ResultEmp resultEmp) {
        SPResult spResult = empSP.update(resultEmp);
        if (spResult == null) {
            /*int retcode = spResult.getRetCode();
            String msg = spResult.getMsg();*/
            new Exception("服务器发生异常！");
        }
        return spResult;
    }


    /**
     * 删除
     *
     * @param empno
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public SPResult delete(@PathVariable("id") Integer empno) {
        SPResult spResult = empSP.delete(empno);
        if (spResult == null) {
            new Exception("服务器发生异常！");
        }
        return spResult;
    }

    /**
     * 刷新
     *
     * @param queryEmp
     * @param model
     * @return
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public String refresh(QueryEmp queryEmp, ModelMap model) {
        SPResult spResult = empSP.search(queryEmp);
        List<ResultEmp> empList = spResult.getRsList();
        model.addAttribute("empList", empList);
        return "index";
    }
}
