package intro.spring.study;

import intro.spring.study.repository.JdbcMemberRepository;
import intro.spring.study.repository.MemberRepository;
import intro.spring.study.repository.MemoryMemberRepository;
import intro.spring.study.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration //→ 스프링이 동작할때 읽어들인다.
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //다만 controller는 예외적으로 우선적으로 필수적으로 넣어줘야한다.
    @Bean
    public MemberService memberService() {
//        return new MemberService();
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() { //상황에 따라 memberRepository 구현체가 바뀔수있다.
/*12.08*/
        //return new MemoryMemberRepository(); //구현체 → interface new가 안된다.
        //추후 '구현체' 만 교체해주면 된다. → 자바설정파일로 빈등록할때의 장점.

/*12.11*/
        return new JdbcMemberRepository(dataSource); //★다른것도 손 댄것이 없다. memory 구현체 로직 구현 + springConfiguration 수정만 했다.
        //"스프링이 왜 좋으냐?"에 대한 대답 -> 객체지향적인 설계 -> 컨테이너 와 DI덕분에  굉장히 편하다
        // OCP - Open-Closed Principle 개방-폐쇄 원칙
        //확장에는 열려있고, 수정에는 닫혀있다. -> 다형성을 활용하여 -> 조립코드는 바뀌지만, app구성하는 로직 코드는 수정 할 필요가 없다.
    }

}
