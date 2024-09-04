package groupName.servlet.web.frontcontroller.v3.controller;

import groupName.servlet.web.frontcontroller.ModelView;
import groupName.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) { //request파라미터는 Map객체로 받는다
        return new ModelView("new-form");
    }
}
