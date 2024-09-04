package groupName.servlet.web.frontcontroller.v1.controller;

import groupName.servlet.domain.member.Member;
import groupName.servlet.domain.member.MemberRepository;
import groupName.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MemberSaveControllerV1 implements ControllerV1 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request의 데이터 추출하기
        String username = request.getParameter("username"); //유저가 입력한 username, age를 HttpServletRequest.getParameter()
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);

        memberRepository.save(member);

        List<Member> members = memberRepository.findAll();
        //Model에 데이터 보관
        request.setAttribute("member", member);

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); //jsp파일로 포워딩

    }
}
