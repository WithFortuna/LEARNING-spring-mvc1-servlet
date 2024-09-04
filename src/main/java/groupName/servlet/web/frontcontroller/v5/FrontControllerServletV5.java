package groupName.servlet.web.frontcontroller.v5;

import groupName.servlet.web.frontcontroller.ModelView;
import groupName.servlet.web.frontcontroller.MyView;
import groupName.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import groupName.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import groupName.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import groupName.servlet.web.frontcontroller.v4.FrontControllerServletV4;
import groupName.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import groupName.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import groupName.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import groupName.servlet.web.frontcontroller.v5.adapter.ControllerV3Adapter;
import groupName.servlet.web.frontcontroller.v5.adapter.ControllerV4Adapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    //핸들러맵(컨트롤러맵) 멤벼변수, 핸들러어댑터 리스트(url별로 request 들어오면 handler찾아야되고 또 그에 맞게 adapter 찾아야함)
    private Map<String, Object> handlerMap = new HashMap<>(); //Controller의 버전 상관없이 넣을 것이므로 Object
    private List<MyHandlerAdapter> adapters = new ArrayList<>();

    public FrontControllerServletV5() {
        //handlerMap 초기화
        handlerMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        adapters.add(new ControllerV3Adapter());

        handlerMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
        adapters.add(new ControllerV4Adapter());


    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //handler 및 adapter 찾아오기
        Object handler = getHandler(request);
        MyHandlerAdapter adapter = getAdapter(handler);
        //adapter 실행
        ModelView modelView = adapter.handle(request, response, handler);
        //MyView 실행
        MyView myView = viewResolver(modelView);

        myView.render(modelView.getModel(), request, response);


    }

    private MyHandlerAdapter getAdapter(Object handler) {
        for (MyHandlerAdapter adapter : adapters) {
            if (adapter.supprots(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("해당 handler에 대한 adapter를 찾을 수 없습니다. handler = " + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMap.get(requestURI);
    }
    private static MyView viewResolver(ModelView modelView) {
        String viewName = modelView.getViewName(); //논리 이름가져옴 (controller -> FrontController)
        MyView myView = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return myView;
    }
}
