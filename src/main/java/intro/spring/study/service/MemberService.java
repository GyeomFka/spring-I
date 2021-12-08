package intro.spring.study.service;

import intro.spring.study.domain.Member;
import intro.spring.study.repository.MemberRepository;
import intro.spring.study.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //스프링이 관리하게 해줘야한다.
public class MemberService {

    //- 회원 Repo와 Domain을 활용해서 Business 로직을 작성한다.
    //- 회원 Repo가 필요하다.
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) { //repository 도 스프링에 등록을 해줘야한다
        //memberservice도 repository 가 필요하다. -> autowired 로 인해 service생성자 호출시 repo를 자동 연결 해준다.
        // 현재 구현체로 정의되어있는 memoryMemberRepository 가 주입이 된다.
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) { //회원가입

        //가입할때 같은 이름 중복 안된다.
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */ //↓ 코드 사용 권장
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    * 전체회원 조회
    * */
    public List<Member> findMembers() { //여기서 잠시, naming을 repository와 비교하면 findAll : 단순 DB I/O 느낌이 강력  <-> 서비스 객체 findMembers : 비즈니스 네이밍에 더 가깝다.
        //서비스 클래스는 비즈니스 용어의 냄새가 많이 나야한다.
        //서비스는 비즈니스 냄새가 많이 나야한다. -> 누구든 알아 볼 수 있게,
        //서비스는 비즈니스 의존적인 설계
        // repo는 low level에 가깝게
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
