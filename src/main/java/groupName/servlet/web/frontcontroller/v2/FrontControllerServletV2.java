package groupName.servlet.web.frontcontroller.v2;

import groupName.servlet.web.frontcontroller.MyView;
import groupName.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import groupName.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import groupName.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/members/*")
public class FrontControllerServletV2 extends HttpServlet {
    //url 맵핑정보
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() { //기본생성자를 통해 url 및 컨트롤러 초기화
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request의 url에 매칭되는 controller가져오기
        String requestURI = request.getRequestURI();
        ControllerV2 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //view 전달받기(view는 해당 controller의 jsp정보를 담는다)
        MyView view = controller.process(request, response);
        view.render(request,response);

    }
}
