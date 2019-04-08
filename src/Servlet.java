
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = "图书详情";
        String docType =
                "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><meta charset=\"utf-8\"><title>" + title + "</title></head>\n"+
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
                "<tr bgcolor=\"#949494\">\n" +
                "<th>title</th><th>ISBN</th><th>writer</th><th>inventory</th><th>price</th><th>group</th>\n"+
                "</tr>\n");
        out.print("<tr><td>"+ "挪威的森林"+ "</td>\n");
        out.println("<td> " + "9787532755387" + "</td>\n");
        out.println("<td> " + " 村上春树 著，林少华 译" + "</td>\n");
        out.println("<td> " + "10" + "</td>\n");
        out.println("<td> " + "50" + "</td>\n");
        out.println("<td> " + "literature" + "</td></tr>\n");
        out.print("<tr><td>"+ "泥土之界"+ "</td>\n");
        out.println("<td> " + "9787533955588" + "</td>\n");
        out.println("<td> " + "[美] 希拉莉·乔顿 著 ； 房小然 译" + "</td>\n");
        out.println("<td> " + "10" + "</td>\n");
        out.println("<td> " + "50" + "</td>\n");
        out.println("<td> " + "literature" + "</td></tr>\n");
        out.print("<tr><td>"+ "哈佛管理学"+ "</td>\n");
        out.println("<td> " + "9787511339065" + "</td>\n");
        out.println("<td> " + " 杜晗" + "</td>\n");
        out.println("<td> " + "10" + "</td>\n");
        out.println("<td> " + "50" + "</td>\n");
        out.println("<td> " + "science" + "</td></tr>\n");
        out.print("<tr><td>"+ "哲思"+ "</td>\n");
        out.println("<td> " + "1003-3483" + "</td>\n");
        out.println("<td> " + "时代青年杂志社" + "</td>\n");
        out.println("<td> " + "10" + "</td>\n");
        out.println("<td> " + "50" + "</td>\n");
        out.println("<td> " + "magazine" + "</td></tr>\n");
        out.println("</table>\n</body></html>");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
