package intro.spring.study.service;

import intro.spring.study.domain.Member;
import intro.spring.study.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    /*
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    */
    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        //memberservice 입장에서 보면 직접 new 하지 않고, 외부에서 dependency injection 이라고 한다.
    }

    /*
    * memberservice 클래스 에 repo 객체가 존재한다
    * 현재 내부 test클래스에도 repo객체가 존재한다... 흠... 굳이 같은걸 두번 new한다?
    * repo객체에 static한 변수를 활용해서 그렇지 , 근본적으로 다른 인스턴스이기때문에 오류가능성이 있다.
    *
    * ->  문제 ln 16안에 repo 인스턴스 !=  ln 17 repo 인스턴스
    * 테스트는 같은 repo로 구현이 되고있지만 엄연히 다른 repo이다.
    * -> 같은 인스턴스를 쓰게 바꾸려면  service객체에 선언한 repository를 지워주고 생성자로 받도록 한다.
    * */

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입_테스트() {
        // given : 상황이 주워지고
        Member member = new Member();
        member.setName("spring");

        // when : 실행했을 때
        Long saveId = memberService.join(member);

        // then : 결과값
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

        //test는 예외 케이스테스트를 검증하는게 중요하다.
    }

    @Test
    //test는 예외 케이스테스트를 검증하는게 중요하다.
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        /*try {
            memberService.join(member2); //예외가 터져야 한다.
            fail();
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //반환도 가능하다
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//반환도 가능하다
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}