import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String title = "图书详情";
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/ebook";
        // 数据库的用户名与密码
        String USER = "root";
        String PASS = "110157";
        Connection conn = null;
        Statement stmt = null;
        // 注册 JDBC 驱动
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT title, image FROM book";
            ResultSet rs = stmt.executeQuery(sql);
            out.println("<!DOCTYPE html>\n");
            out.println("<html>\n");
            out.println("<head>"+"<meta charset=\"utf-8\"><title>" + "</title></head>\n");
            out.println("</head>\n");
            out.println("<body>");
            out.println("<table>");
            out.println("<tr>+ <th> 书籍名称</th> + <th> 图片</th> +</tr>");
            while (rs.next()) {
                // 通过字段检索
                String ttitle = rs.getString("title");
                String image = rs.getString("image");
                // 输出数据
                out.println("<tr>+<td>" + ttitle+"</td>" );
                out.println("<td> " + image+"</td>+<tr>");
                out.println("\n");
            }
            // 完成后关闭
            out.println("</table>\n");
            out.println("</body>\n");
            out.println("</html>\n");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
