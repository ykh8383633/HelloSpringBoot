package hello.hello.spring;

import hello.hello.spring.aop.TimeTraceAop;
import hello.hello.spring.repository.JdbcTemplateMemberRepository;
import hello.hello.spring.repository.JpaMemberRepository;
import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;
import hello.hello.spring.service.MemberService;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // spring bean에 등록하는 class (Component scanning(위에 @Service, @Repository 붙이는 방법) 말고 다른 방법임)
public class SpringConfig {

    private final MemberRepository memberRepository; // spring data jpa 구현시

    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //private EntityManager em; ############

    //public SpringConfig(EntityManager em){  ############ jpa사용시
    //    this.em = em; #############
    //}
    //private DataSource dataSource;            ###

    //@Autowired                               ###
    //public SpringConfig(DataSource dataSource) { ### jdbc사용시
    //    this.dataSource = dataSource; ###
    //}

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
        //return new MemberService(memberRepository());
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

//    @Bean
 //   public MemberRepository memberRepository(){
//
        //return new MemoryMemberRepository(); // 임시저장소
        //return new JdbcTemplateMemberRepository(dataSource); // jdbc template
        //return new JpaMemberRepository(em); // Jpa

  //  }
}
