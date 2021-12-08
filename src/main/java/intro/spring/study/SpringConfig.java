package intro.spring.study;

import intro.spring.study.repository.MemberRepository;
import intro.spring.study.repository.MemoryMemberRepository;
import intro.spring.study.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    //다만 controller는 예외적으로 우선적으로 필수적으로 넣어줘야한다.
    //당연히 콤포넌트 스캔이 필요하지만 장단점이 있다.
    //
    @Bean
    public MemberService memberService() { //스프링이 뜰때 configuration을 읽고 @bean을 찾아 등록을 시킨다.
//        return new MemberService();
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() { //상황에 따라 memberRepository 구현체가 바뀔수있다.
        return new MemoryMemberRepository();//구현체 -> interface new가 안된다.
        //추후 ln 22 만 교체해주면 된다.
        // 자바설정파일로 빈등록할때의 장점.
    }
}
