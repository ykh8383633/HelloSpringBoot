package hello.hello.spring.controller;

import hello.hello.spring.domain.Member;
import hello.hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //내부의 @Component가 있어 spring bean으로 등록됨
public class MemberController {
    private final MemberService memberService;
    // @Autowired private  MemberService memberService ## 필드 주입(DI 방법중 하나) 뭔가 조작할 방법이 제한되어 비추

    //@Autowired ## setter주입(DI 방법중 하나) 한번 주입후 잘못 바꿔질 수 있음
    //public void setMemberService(MemberService memberService){
    //    this.memberService = memberService;
    //}

    @Autowired // Controller에서 Service, Repository를 연결 ## 생성자 주입(DI 방법중 하나)
    public MemberController(MemberService memberservice){
        this.memberService = memberservice;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm){
        Member member = new Member();
        member.setName(memberForm.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMember();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
