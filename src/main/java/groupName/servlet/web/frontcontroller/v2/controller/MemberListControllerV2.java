package groupName.servlet.web.frontcontroller.v2.controller;

import groupName.servlet.domain.member.Member;
import groupName.servlet.domain.member.MemberRepository;
import groupName.servlet.web.frontcontroller.MyView;
import groupName.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        //Model에 전달
        request.setAttribute("members", members);

        return new MyView("/WEB-INF/views/members.jsp");
    }
}
