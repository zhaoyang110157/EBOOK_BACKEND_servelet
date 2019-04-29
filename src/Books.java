import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@WebServlet(name = "Books")
public class Books extends HttpServlet {
    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost:3306/ebook?characterEncoding=utf8&useSSL=false";
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
            sql = "SELECT * FROM book ";
            ResultSet rs = stmt.executeQuery(sql);
            JSONArray array = new JSONArray();
            while (rs.next()) {
                // 通过字段检索
                JSONObject tmp = new JSONObject();
                tmp.put("title", rs.getString("title"));
                tmp.put("bid",rs.getInt("bid"));
                tmp.put("image", rs.getString("image"));
                tmp.put("ISBN", rs.getString("ISBN"));
                tmp.put("writer", rs.getString("writer"));
                tmp.put("price", rs.getFloat("price"));
                tmp.put("inventory", rs.getInt("inventory"));
                tmp.put("tranch", rs.getString("tranch"));
                tmp.put("introduction", rs.getString("introduction"));
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

        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }

        //System.out.println(wholeStr);
        JSONObject req = JSONObject.parseObject(wholeStr);
        //System.out.println(req);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        Statement stmt = null;
        String title="",writer="",image="",ISBN="",tranch = "";
        int inventory=0,id=-1;
        float price=0;
        String introduction="";
        int type = req.getInteger("type");
       // id = req.getInteger("id");
        //System.out.print(id);
        title = req.getString("title");
        int bid = req.getInteger("bid");
        //System.out.print(title);
        image = req.getString("image");
        //System.out.print(image);
        ISBN = req.getString("ISBN");
        //System.out.print(ISBN);
        writer = req.getString("writer");
        //System.out.print(writer);
        price = req.getFloat("price");
        //System.out.print(price);
        inventory = req.getInteger("inventory");
        //System.out.print(inventory);
        tranch  = req.getString("tranch");
        //System.out.print(tranch);
        introduction = req.getString("introduction");
        System.out.print(type);
        if(type == 0)
        {
            try {
                System.out.print(type);
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();
                String sql;
                sql = "UPDATE `book` SET title = '"+title+"',writer = '"+writer+"',price="+price+",inventory = "+inventory+",tranch = '"+tranch+"' ,introduction = '"+introduction+"' WHERE ISBN = " +
                        "'"+ISBN+"'";
                System.out.print(sql);
                System.out.print("\n");
                int rs = stmt.executeUpdate(sql);

                JSONObject resp = new JSONObject();
                resp.put("message", "Update bookInf successfully!");
                System.out.println(2);
                System.out.println(1);
                out.println(resp);
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
        else{
            try {
                System.out.print(type);
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();
                String sql;
                sql = "INSERT INTO book(title,bid,image,ISBN,writer,price,inventory,tranch,introduction) VALUES('"+title+"',"+bid+",null,'"+ISBN+"','"+writer+"','"+price+"','"+inventory+"','"+tranch+"', '"+introduction+"')";
                System.out.print(sql);
                System.out.print("\n");
                int rs = stmt.executeUpdate(sql);

                JSONObject resp = new JSONObject();
                resp.put("message", "Update bookInf successfully!");
                System.out.println(2);
                System.out.println(1);
                out.println(resp);
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
    }

}

