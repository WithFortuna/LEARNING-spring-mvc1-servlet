package groupName.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {
    String process(Map<String, String> paramMap, Map<String, Object> model); //paramMap: request의 쿼리파라미터, model: controller -> view로 전달할 데이터

}
