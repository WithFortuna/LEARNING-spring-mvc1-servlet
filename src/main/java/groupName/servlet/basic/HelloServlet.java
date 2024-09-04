package groupName.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//HttpServlet 상속받음으로써 커스텀 서블릿 생성
@WebServlet(name = "helloServlet", urlPatterns = "/hello") //urlPatterns필드에 해당하는 url에 GET,POST 모두 맵핑
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
        System.out.println("HelloServlet.service");
        System.out.println("request:"+ req);
        System.out.println("response: "+ resp);

        //request
        String username = req.getParameter("username");
        System.out.println("username: "+username);
        //response
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write("hello  "+username);

    }
}
