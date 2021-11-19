package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service // (Controller에 있는)Autowired를 통해 연결하기위해 (내부의 @Component가 있어 spring bean으로 등록됨)
@Transactional // jpa를 쓰기위해서는 반드시 필요함
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired // Repository와 연결을 위해 Autowired는 객체가 bean에 등록되어야 작동
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        // 같은 이름은 안됌
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(a -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /* 전체 회원 조회 */
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
