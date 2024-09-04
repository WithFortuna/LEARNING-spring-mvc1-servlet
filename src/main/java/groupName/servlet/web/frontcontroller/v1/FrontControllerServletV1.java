package groupName.servlet.web.frontcontroller.v1;

import groupName.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import groupName.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import groupName.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//프론트 컨트롤러
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*") // *에 어떤 리소스가 들어와도 여기로 맵핑된다.
public class FrontControllerServletV1 extends HttpServlet {
    //url - controller 맵핑 정보를 저장
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());

    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=======================frontController.service=======================");

        //url뽑아내기
        String requestURI = request.getRequestURI();
        //인터페이스로 구현체를 담을 수 있다.
        ControllerV1 controller = controllerMap.get(requestURI); //url과 맵핑된 controller 가져오기

        if (requestURI == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);//40x오류;
            return;
        }

        else
            controller.process(request,response);


    }
}
