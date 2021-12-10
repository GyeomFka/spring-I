package intro.spring.study.service;

import intro.spring.study.domain.Member;
import intro.spring.study.repository.MemberRepository;
import intro.spring.study.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

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