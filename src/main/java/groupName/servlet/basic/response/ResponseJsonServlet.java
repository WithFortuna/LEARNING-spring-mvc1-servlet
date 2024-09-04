package groupName.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import groupName.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setAge(100);
        helloData.setUsername("olaf");

        String str = objectMapper.writeValueAsString(helloData); //객체->String 변환 : json과 같은 형식이 되는듯
        response.getWriter().write(str);
    }
}
