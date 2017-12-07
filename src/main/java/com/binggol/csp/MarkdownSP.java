package com.binggol.csp;

import com.binggol.entity.ResultMarkdown;
import com.binggol.utils.DBConstant;
import com.binggol.utils.SPResult;
import com.binggol.utils.TypeConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.Types;

@Component
public class MarkdownSP {
    private String spkName = DBConstant.PKGB_MANAGEMARKDOWN;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 新增markdown内容
     *
     * @param resultMarkdown
     * @return
     */
    public SPResult addMarkdown(ResultMarkdown resultMarkdown) {
        SPResult spResult = (SPResult) jdbcTemplate.execute(conn -> {
            CallableStatement cs = conn.prepareCall("{call " + spkName + ".up_AddMarkdown(?,?,?,?,?,?)}");
            if (resultMarkdown.getId() != null) {
                cs.setString(1, resultMarkdown.getId());
            } else {
                new Exception("Id不能为空！");
            }
            if (resultMarkdown.getDatetime() != null) {
                cs.setDate(2, new Date(resultMarkdown.getDatetime().getDate()));
            } else {
                new Exception("日期不能为空！");
            }
            if (resultMarkdown.getContent() != null) {
                cs.setClob(3, TypeConvertUtil.stringToClob(resultMarkdown.getContent()));
                //cs.setString(3,resultMarkdown.getContent());
            } else {
                new Exception("内容不能为空！");
            }
            if (resultMarkdown.getImage() == null) {
                cs.setNull(4, Types.CHAR);
            } else {
                cs.setString(4, resultMarkdown.getImage());
            }
            cs.registerOutParameter(5, Types.INTEGER);
            cs.registerOutParameter(6, Types.CHAR);
            return cs;
        }, (CallableStatementCallback) cs -> {
            SPResult result = new SPResult();
            int resCode = cs.executeUpdate();
            int retCode = cs.getInt(5);
            String msg = cs.getString(6);
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


}
