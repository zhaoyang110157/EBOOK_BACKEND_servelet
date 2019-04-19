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

@WebServlet(name = "Users")
public class Users extends HttpServlet {
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost:3306/ebook";
    // 数据库的用户名与密码
    String USER = "root";
    String PASS = "110157";
    private static final long serialVersionUID = 1L;
    public Users() {
        super();
        // TODO Auto-generated constructor stub

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        PrintWriter out = response.getWriter();

        Connection conn  = null;
        Statement stmt = null;
        // 注册 JDBC 驱动
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(sql);
            JSONArray array = new JSONArray();
            while (rs.next()) {
                // 通过字段检索
                JSONObject tmp = new JSONObject();
                tmp.put("account", rs.getString("account"));
                System.out.print(rs.getString("account")+"/n");
                tmp.put("password", rs.getString("password"));
                tmp.put("allowed", rs.getByte("allowed"));
                tmp.put("role", rs.getString("role"));
                array.add(tmp);

            }
            JSONObject resp = new JSONObject();
            resp.put("users", array);
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

    }
}
