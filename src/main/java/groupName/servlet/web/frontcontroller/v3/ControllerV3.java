package groupName.servlet.web.frontcontroller.v3;

import groupName.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    public ModelView process(Map<String, String> paramMap ); //HttpServletRequest(Response)를 제거
}
