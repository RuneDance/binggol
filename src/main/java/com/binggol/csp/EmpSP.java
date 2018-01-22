package com.binggol.csp;

import com.binggol.entity.QueryEmp;
import com.binggol.entity.ResultEmp;
import com.binggol.utils.DBConstant;
import com.binggol.utils.SPResult;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


@Component
public class EmpSP {
    private String spkName = DBConstant.PKGB_MANAGEEMP;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询员工信息
     *
     * @param queryEmp
     * @return
     */
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
            cs.close();
            return result;
        });
        return spResult;
    }

    /**
     * 新增员工信息
     *
     * @param resultEmp
     * @return
     */
    public SPResult add(ResultEmp resultEmp) {
        SPResult spResult = (SPResult) jdbcTemplate.execute(conn -> {
            CallableStatement cs = conn.prepareCall("{call " + spkName + ".up_AddEmpInformation(?,?,?,?,?,?,?,?,?,?)}");
            if (resultEmp.getEmpno() != null) {
                cs.setInt(1, resultEmp.getEmpno());
            } else {
                new Exception("员工编号不能为空");
            }
            cs.setString(2, resultEmp.getEname());
            cs.setString(3, resultEmp.getJob());
            if (resultEmp.getMgr() == null) {
                cs.setNull(4, Types.INTEGER);
            } else {
                cs.setInt(4, resultEmp.getMgr());
            }
            if (resultEmp.getHiredate() == null) {
                cs.setNull(5, Types.INTEGER);
            } else {
                cs.setDate(5, new Date(resultEmp.getHiredate().getDate()));
            }
            if (resultEmp.getSal() == null) {
                cs.setNull(6, Types.INTEGER);
            } else {
                cs.setInt(6, resultEmp.getSal());
            }
            if (resultEmp.getComm() == null) {
                cs.setNull(7, Types.INTEGER);
            } else {
                cs.setInt(7, resultEmp.getComm());
            }
            if (resultEmp.getDeptno() == null) {
                cs.setNull(8, Types.INTEGER);
            } else {
                cs.setInt(8, resultEmp.getDeptno());
            }
            cs.registerOutParameter(9, Types.INTEGER);
            cs.registerOutParameter(10, Types.CHAR);
            return cs;
        }, (CallableStatementCallback) cs -> {
            SPResult result = new SPResult();
            int resCode = cs.executeUpdate();
            int retCode = cs.getInt(9);
            String msg = cs.getString(10);
            if (resCode != 1) {
                result.setRetCode(resCode);
                result.setMsg("新增失败");
            } else {
                result.setRetCode(retCode);
                result.setMsg(msg);
            }
            cs.close();
            return result;
        });
        return spResult;
    }

    /**
     * 修改员工信息
     *
     * @param resultEmp
     * @return
     */
    public SPResult update(ResultEmp resultEmp) {
        SPResult spResult = (SPResult) jdbcTemplate.execute(conn -> {
            CallableStatement cs = conn.prepareCall("{call " + spkName + ".up_UpdateEmpInformation(?,?,?,?,?,?,?,?,?,?)}");
            if (resultEmp.getEmpno() != null) {
                cs.setInt(1, resultEmp.getEmpno());
            } else {
                new Exception("员工编号不能为空");
            }
            cs.setString(2, resultEmp.getEname());
            cs.setString(3, resultEmp.getJob());
            if (resultEmp.getMgr() == null) {
                cs.setNull(4, Types.INTEGER);
            } else {
                cs.setInt(4, resultEmp.getMgr());
            }
            if (resultEmp.getHiredate() == null) {
                cs.setNull(5, Types.INTEGER);
            } else {
                cs.setDate(5, new Date(resultEmp.getHiredate().getTime()));
            }
            if (resultEmp.getSal() == null) {
                cs.setNull(6, Types.INTEGER);
            } else {
                cs.setInt(6, resultEmp.getSal());
            }
            if (resultEmp.getComm() == null) {
                cs.setNull(7, Types.INTEGER);
            } else {
                cs.setInt(7, resultEmp.getComm());
            }
            if (resultEmp.getDeptno() == null) {
                cs.setNull(8, Types.INTEGER);
            } else {
                cs.setInt(8, resultEmp.getDeptno());
            }
            cs.registerOutParameter(9, Types.INTEGER);
            cs.registerOutParameter(10, Types.CHAR);
            return cs;
        }, (CallableStatementCallback) cs -> {
            SPResult result = new SPResult();
            int resCode = cs.executeUpdate();
            int retCode = cs.getInt(9);
            String msg = cs.getString(10);
            if (resCode != 1) {
                result.setRetCode(resCode);
                result.setMsg("修改失败");
            } else {
                result.setRetCode(retCode);
                result.setMsg(msg);
            }
            cs.close();
            return result;
        });
        return spResult;
    }

    /**
     * 删除员工信息
     *
     * @param empno
     * @return
     */
    public SPResult delete(Integer empno) {
        SPResult spResult = (SPResult) jdbcTemplate.execute(conn -> {
            CallableStatement cs = conn.prepareCall("{call " + spkName + ".up_DeleteEmpInformation(?,?,?)}");
            if (empno != null) {
                cs.setInt(1, empno);
            } else {
                new Exception("员工编号不能为空");
            }
            cs.registerOutParameter(2, Types.INTEGER);
            cs.registerOutParameter(3, Types.CHAR);
            return cs;
        }, (CallableStatementCallback) cs -> {
            SPResult result = new SPResult();
            int resCode = cs.executeUpdate();
            int retCode = cs.getInt(2);
            String msg = cs.getString(3);
            if (resCode != 1) {
                result.setRetCode(resCode);
                result.setMsg("删除失败");
            } else {
                result.setRetCode(retCode);
                result.setMsg(msg);
            }
            cs.close();
            return result;
        });
        return spResult;
    }
}
