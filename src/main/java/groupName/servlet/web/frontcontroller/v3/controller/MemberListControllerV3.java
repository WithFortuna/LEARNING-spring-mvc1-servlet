package groupName.servlet.web.frontcontroller.v3.controller;

import groupName.servlet.domain.member.Member;
import groupName.servlet.domain.member.MemberRepository;
import groupName.servlet.web.frontcontroller.ModelView;
import groupName.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        //내부로직 수행
        List<Member> members = memberRepository.findAll();
        //View 호출을 위한 경로 설정 + model 전달
        ModelView modelView = new ModelView("members");
        modelView.getModel().put("members", members);

        return modelView;
    }
}
