package com.binggol.utils;

import javax.sql.rowset.serial.SerialClob;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

/**
 * SqlUtil 类
 */
public class TypeConvertUtil {
    /**
     * 将String类型转换为Clob类型
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static Clob stringToClob(String str) {
        if (null == str)
            return null;
        else {
            try {
                Clob clob = new SerialClob(str.toCharArray());
                return clob;
            } catch (Exception e) {
                return null;
            }
        }
    }

    /**
     * 将Clob类型转换为String类型
     *
     * @param clob
     * @return
     */
    public String clobToString(Clob clob) {
        String string = "";
        Reader reader = null;
        try {
            reader = clob.getCharacterStream();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(reader);
        String str = null;
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        while (str != null) {
            sb.append(str);
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        string = sb.toString();
        return string;
    }

}
