package groupName.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//쿼리 파라미터 데이터 조회
@WebServlet(name = "requestParamServelet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("contentType:"+ request.getContentType());
        //request.getParamterNames() => username, age 등의 파라미터 변수명 출력
        System.out.println("전체 파라미터 조회 -start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName +"="+request.getParameter(paramName)));
        System.out.println("전체 파라미터 조회 -end \n");

        //request.getParameter("parameterName")
        System.out.println("단일 파라미터 조회 -start");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username: "+username+"\nage: "+age);
        System.out.println("단일 파라미터 조회 -end");

        //request.getParameterValeus("parameterName")
        System.out.println("복수 파라미터 조회 -start");
        String[] usernames = request.getParameterValues("username");
//        System.out.println(usernames);
        System.out.println("복수 파라미터 조회 -end");

    }
}
