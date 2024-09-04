package groupName.servlet.web.frontcontroller.v3;

import groupName.servlet.web.frontcontroller.ModelView;
import groupName.servlet.web.frontcontroller.MyView;
import groupName.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import groupName.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import groupName.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import groupName.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import groupName.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import groupName.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/members/*")
public class FrontControllerServletV3 extends HttpServlet {
    //url - controller 맵핑 관리
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. url 매칭되는 controller 호출
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //2. controller 실행 및 파라미터Map객체 전달
        Map<String, String> paramMap = createParamMap(request);

        ModelView modelView = controller.process(paramMap);

        //3. MyView생성 및 render() 수행
        MyView myView = viewResolver(modelView); //modelView에서 경로가져옴

        Map<String, Object> model = modelView.getModel(); //modelView에서 데이터(model)가져옴
        myView.render(model, request,response);
    }

    private static MyView viewResolver(ModelView modelView) {
        String viewName = modelView.getViewName(); //논리 이름가져옴 (controller -> FrontController)
        MyView myView = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return myView;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
