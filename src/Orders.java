import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Enumeration;

@WebServlet(name = "Orders")
public class Orders extends HttpServlet {
    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost:3306/ebook?useSSL=false&serverTimezone=UTC";
    // 数据库的用户名与密码
    String USER = "root";
    String PASS = "110157";
    private static final long serialVersionUID = 1L;
    public Orders() {
        super();
        // TODO Auto-generated constructor stub

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        PrintWriter out = response.getWriter();

            String account = request.getQueryString();
        System.out.print(account);
        System.out.print('\n');
        int ass =  account.lastIndexOf("=");
        account =  account.substring(ass+1);
        System.out.print(account);
        Connection conn  = null;
        Statement stmt = null;
        // 注册 JDBC 驱动
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM orders where account= '"+account+"'";
            ResultSet rs = stmt.executeQuery(sql);
            JSONArray array = new JSONArray();
            while (rs.next()) {
                // 通过字段检索
                JSONObject tmp = new JSONObject();
                tmp.put("account", rs.getString("account"));
                tmp.put("image", rs.getString("image"));
                tmp.put("price", rs.getString("price"));
                tmp.put("title", rs.getString("title"));
                tmp.put("inventory", rs.getByte("sales"));
                tmp.put("time", rs.getString("time"));
                array.add(tmp);
            }
            System.out.print("ssssss");
            JSONObject resp = new JSONObject();
            resp.put("orders", array);
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
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        JSONObject req = JSONObject.parseObject(wholeStr);
        System.out.println(req );

        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        System.out.println(1);
        PreparedStatement pst = null;
        Connection conn = null;
        Statement stmt = null;

        Integer type = req.getInteger("type");
        if (type == 0) {
            String title = req.getString("title");
            System.out.println(title);
            int buy = req.getInteger("inventory");
            System.out.print(buy);
           // 注册 JDBC 驱动
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();
                String sql;
                System.out.println(21);
                sql = "SELECT inventory from `book` where title = '"+title+"'";
                System.out.println(22);
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                int inv = rs.getInt(1);
                inv -= buy;
                sql = "UPDATE `book` SET inventory = "+inv+" WHERE title = '" + title +"'";
                System.out.println(31);
                stmt.executeUpdate(sql);
                JSONObject resp = new JSONObject();
                resp.put("message", "buy book " + title +" successfully!");
                System.out.println(2);
                out.println(resp);
                stmt.close();
                conn.close();
            } catch (SQLException se) {
                // 处理 JDBC 错误
                se.printStackTrace();
            } catch (Exception e) {
                // 处理 Class.forName 错误
                e.printStackTrace();
            } finally {
                // 关闭资源
                try {
                    if (stmt != null) stmt.close();
                } catch (SQLException se2) {
                }// 什么都不做
                try {
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
        if (type == 1) {
            String title = req.getString("title");
            System.out.println(title);
            String account = req.getString("account");
            int buy = req.getInteger("inventory");
            System.out.print(buy);
            int time = req.getInteger("time");

            // 注册 JDBC 驱动
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();
                String sql;
                System.out.println(21);
                sql = "Insert INTO  orders(title,account.sales,time） values('"+title+"','"+account+"','"+buy+"',"+time+")";
                System.out.println(22);
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println(31);
                stmt.executeUpdate(sql);
                JSONObject resp = new JSONObject();
                resp.put("message", "buy book " + title +" successfully!");
                System.out.println(2);
                out.println(resp);
                stmt.close();
                conn.close();
            } catch (SQLException se) {
                // 处理 JDBC 错误
                se.printStackTrace();
            } catch (Exception e) {
                // 处理 Class.forName 错误
                e.printStackTrace();
            } finally {
                // 关闭资源
                try {
                    if (stmt != null) stmt.close();
                } catch (SQLException se2) {
                }// 什么都不做
                try {
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }
}
