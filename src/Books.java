import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@WebServlet(name = "Books")
public class Books extends HttpServlet {
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost:3306/ebook";
    // 数据库的用户名与密码
    String USER = "root";
    String PASS = "110157";
    private static final long serialVersionUID = 1L;
    public Books() {
        super();
        // TODO Auto-generated constructor stub

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        Statement stmt = null;
        // 注册 JDBC 驱动
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM book";
            ResultSet rs = stmt.executeQuery(sql);
            JSONArray array = new JSONArray();
            while (rs.next()) {
                // 通过字段检索
                JSONObject tmp = new JSONObject();
                tmp.put("title", rs.getString("title"));
                tmp.put("image", rs.getString("image"));
                tmp.put("ISBN", rs.getString("ISBN"));
                tmp.put("writer", rs.getString("writer"));
                tmp.put("price", rs.getFloat("price"));
                tmp.put("inventory", rs.getInt("inventory"));
                tmp.put("group", rs.getString("tranch"));
                tmp.put("intro", rs.getString("introduction"));
                array.add(tmp);
            }
            JSONObject resp = new JSONObject();
            resp.put("books", array);
            out.print(resp);
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
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title="",writer="",image="",ISBN="";
        int inventory=0;
        float price=0;
        String introduction="";
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM book";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

            }
            JSONObject resp = new JSONObject();
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
}}
