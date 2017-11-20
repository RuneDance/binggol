package com.binggol.csp;

import com.binggol.entity.QueryEmp;
import com.binggol.entity.ResultEmp;
import com.binggol.utils.DBConstant;
import com.binggol.utils.SPResult;
import oracle.jdbc.driver.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


@Component
public class EmpSP {
    private String spkName = DBConstant.PKGB_QUERYEMP;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public SPResult search(QueryEmp queryEmp) {
        SPResult spResult = (SPResult) jdbcTemplate.execute(conn -> {
            CallableStatement cs = conn.prepareCall("{call " + spkName + ".up_QueryEmpInformation(?,?,?,?,?,?,?,?)}");
            if (queryEmp.getEmpno() == null) {
                cs.setNull(1, Types.INTEGER);
            } else {
                cs.setInt(1, queryEmp.getEmpno());
            }
            cs.setString(2, queryEmp.getEname());
            cs.setString(3, queryEmp.getJob());
            if (queryEmp.getMgr() == null) {
                cs.setNull(4, Types.INTEGER);
            } else {
                cs.setInt(4, queryEmp.getMgr());
            }
            if (queryEmp.getSal() == null) {
                cs.setNull(5, Types.INTEGER);
            } else {
                cs.setInt(5, queryEmp.getSal());
            }
            cs.registerOutParameter(6, OracleTypes.CURSOR);
            cs.registerOutParameter(7, Types.INTEGER);
            cs.registerOutParameter(8, Types.CHAR);
            return cs;
        }, (CallableStatementCallback) cs -> {
            List<ResultEmp> list = new ArrayList();
            SPResult result = new SPResult();
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(6);
            while (rs.next()) {
                ResultEmp resultEmp = new ResultEmp();
                resultEmp.setEmpno(rs.getInt(1));
                resultEmp.setEname(rs.getString(2));
                resultEmp.setJob(rs.getString(3));
                resultEmp.setMgr(rs.getInt(4));
                resultEmp.setHiredate(rs.getDate(5));
                resultEmp.setSal(rs.getInt(6));
                resultEmp.setComm(rs.getInt(7));
                resultEmp.setDeptno(rs.getInt(8));
                list.add(resultEmp);
            }
            result.setRsList(list);
            rs.close();
            return result;
        });
        return spResult;
    }
}
