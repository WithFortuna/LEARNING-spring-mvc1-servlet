package groupName.servlet.web.frontcontroller.v4;

import groupName.servlet.web.frontcontroller.MyView;
import groupName.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import groupName.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import groupName.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import groupName.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import groupName.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import groupName.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/members/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap = new HashMap<>(); //url-controller맵핑정보 저장

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);
        Map<String, String> paramMap = createParamMap(request); //쿼리 파라미터 저장
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, Object> model = new HashMap<>(); //ModelView 대신 model을 꺼내서 전달
        String viewName = controller.process(paramMap, model); //논리 viewName만 전달. model에 들어갈 데이터는 model에 저장
        MyView myView = viewResolver(viewName);

        myView.render(model, request, response);

    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }


    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
