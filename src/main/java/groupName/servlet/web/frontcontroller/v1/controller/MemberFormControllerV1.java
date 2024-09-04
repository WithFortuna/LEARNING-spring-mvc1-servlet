package groupName.servlet.web.frontcontroller.v1.controller;

import groupName.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); //requestDispatcher
        dispatcher.forward(request, response); //jsp파일로 포워딩 ( redirect와 유사하지만 사용자에게 response로 30x를 보내지 않는다는 게 차이점)

    }


}
