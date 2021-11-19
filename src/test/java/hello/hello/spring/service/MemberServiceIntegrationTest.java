package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;
import hello.hello.spring.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 테스트에 이걸 하면
public class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @Test
    //@Commit // 위에 transaction을 적어도 db에 반영됨
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("hello");
        member2.setName("hello");
        // when
        Long saveId1 = memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*
        try{
            Long saveId2 = memberService.join(member2);
            fail();
        } catch(IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
         */
        //then
    }


}
