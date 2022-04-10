package com.lysss.finance;

import org.junit.jupiter.api.Test;

import java.sql.*;

//@SpringBootTest
public class FinanceApplicationTests {

    public int addTest(){
        int a=1;
        a++;
        return a;
    }

    public static void main(String[] args) {
        FinanceApplicationTests test =new FinanceApplicationTests();
        test.contextLoads();
    }
    @Test
    void contextLoads() {
        System.out.println(5 >> 2);
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(7));
        System.out.println(7 >> 2);
        System.out.println(5 >>> 2);
    }


    static class Father {
        public static int a = 1;

        static {
            System.out.println("执行father static object a=" + a);
            a = 2;
        }

        {
            System.out.println("执行 father object a=" + a);
        }

        public Father() {
            System.out.println("执行 Father construct a=" + a);
            a = 6;
        }
    }

    static class Son extends Father {
        static {
            System.out.println("执行 Son static object a=" + a);
            a = 3;
        }

        {
            System.out.println("执行 Son object a=" + a);
        }

        public Son() {
            System.out.println("执行 Son construct a=" + a);
            a = 4;
        }
    }

    @Test
    public void testLoad() {
//        Father father  =new Son();
        System.out.println(Son.a);
    }

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/kai?useUnicode=true&characterEncoding=UTF-8&connectTimeout=60000&socketTimeout=60000&autoReconnect=true&autoReconnectForPools=true&failOverReadOnly=false&&serverTimezone=GMT%2b8";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "0203";

    @Test
    public void testClassLoader(){
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
//            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("username");
                String url = rs.getString("password");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

}
