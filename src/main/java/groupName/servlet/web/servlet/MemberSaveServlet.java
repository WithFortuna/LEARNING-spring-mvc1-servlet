package groupName.servlet.web.servlet;

import groupName.servlet.basic.HelloData;
import groupName.servlet.domain.member.Member;
import groupName.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//Controller의 @PostMapping 기능
@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //user가 입력한 이름과 age를 로컬DB에 저장
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //request의 데이터 추출하기
        String username = request.getParameter("username"); //유저가 입력한 username, age를 HttpServletRequest.getParameter()
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);

        memberRepository.save(member);

        //response 작성하기
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");

    }

}
