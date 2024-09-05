package groupName.servlet.web.springmvc.v3;

import groupName.servlet.domain.member.Member;
import groupName.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//반환형 및 매개변수 개선: ModelAndView -> String 으로 viewName만 전달,
//                     HttpServletRequest -> @RequestParam("키 이름") String username :  HttpServletReqeust를 직접받지 않고 값을 받아옴
@Controller
@RequestMapping("/springmvc/v3/members") //아래의 메소드들 url의 prefix: /springmvc/v3/members
public class SpringMemberController {


    private MemberRepository memberRepository = MemberRepository.getInstance();


    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    public String memberForm() {

        return "new-form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String memberSave(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String memberList(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";
    }


}
