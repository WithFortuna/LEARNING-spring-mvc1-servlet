package groupName.servlet.web.frontcontroller.v3.controller;

import groupName.servlet.domain.member.Member;
import groupName.servlet.domain.member.MemberRepository;
import groupName.servlet.web.frontcontroller.ModelView;
import groupName.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        //내부로직
        //파라미터 Map객체에서 파라미터 가져옴
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);

        //jsp호출 대신 modelView반환
        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", member);
        return modelView;
    }
}
