package com.binggol.utils;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.jdbc.pool.OracleConnectionCacheManager;
import oracle.jdbc.pool.OracleDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class DBConnection {
    private static final Log logger = LogFactory.getLog(DBConnection.class);
    private final static String CACHE_NAME = "TRADE_CACHE" + new Random().nextLong();
    private static OracleDataSource ds;
    //是否开启缓存
    public static boolean isUseCache = true;

    /**
     * 开启缓存
     *
     * @throws SQLException
     */
    private static void openCache() throws SQLException {
        OracleConnectionCacheManager oracleConnectionCacheManager = OracleConnectionCacheManager
                .getConnectionCacheManagerInstance();
        if (oracleConnectionCacheManager.existsCache(CACHE_NAME)) {
            // 重载后移除缓存
            oracleConnectionCacheManager.removeCache(CACHE_NAME, 0);
            logger.error(Calendar.getInstance().getTime() + " 重新建立数据库连接缓存 !");
        } else {
            ds.setConnectionCachingEnabled(true);
        }
        ds.setConnectionCacheName(CACHE_NAME);
    }

    /**
     * 初始化OracleDataSource
     */
    static {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            if (envCtx == null) {
                logger.error("error：JNDI 查找失败 !");
            }
            ds = (OracleDataSource) envCtx.lookup("dap");
            if (ds == null) {
                logger.error("error：初始化 OracleDataSource 为空 !");
            }

            if (isUseCache)
                openCache();
            else
                ds.setConnectionCachingEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得(无参)数据库链接
     *
     * @return
     */
    public static synchronized Connection getConnection() {
        Connection conn = null;
        try {
            conn = ds.getConnection();
            if (conn == null) {
                logger.error("error：没有获得数据库连接 !");
            }
        } catch (Exception e) {
            if (conn == null) {
                logger.error("error：数据库连接异常 !");
            }
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 获得（有参）数据库链接
     * @param schema
     * @return
     */
    /*public static synchronized Connection getConnection(String schema) {
        Connection conn = null;
		try {
			if ("TradeAdmin".equals(schema)) {
				conn = dsTradeAdmin.getConnection();
			} else if (ConstantUtils.STKSettleAdmin.equals(schema)) {
				conn = stk.getConnection();
			} else {
				conn = ds.getConnection();
			}
			if (conn == null) {
				logger.error("error：没有获得数据库连接 !");
			}
		} catch (SQLException e) {
			logger.error("error：没有获得数据库{" + schema + "}连接,SQLState:{"
					+ e.getSQLState() + "},ERRCODE{" + e.getErrorCode() + "}");
			e.printStackTrace();
		} catch (NullPointerException e1) {
			logger.error("error：没有获得数据库中{" + schema + "}的连接 !");
			e1.printStackTrace();
		}
		return conn;
	}*/

    /**
     * 释放数据库链接
     *
     * @param conn
     */
    public static synchronized void releaseConnection(ResultSet rs, CallableStatement cs, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看运行时连接信息
     *
     * @throws SQLException
     */
    public static void listCacheInfos() throws SQLException {
        OracleConnectionCacheManager oracleConnectionCacheManager = OracleConnectionCacheManager
                .getConnectionCacheManagerInstance();
        oracleConnectionCacheManager.getCacheNameList();
    }
}

