package groupName.servlet.web.frontcontroller.v4.controller;

import groupName.servlet.domain.member.Member;
import groupName.servlet.domain.member.MemberRepository;
import groupName.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        //paramMap에서 member 정보 꺼내오기 & 저장
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);
        
        
        //Model에 member담기
        model.put("member", member);
        return "save-result";
    }
}
