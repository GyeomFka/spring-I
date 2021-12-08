package intro.spring.study.controller;

import intro.spring.study.domain.Member;
import intro.spring.study.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //-> anno를 붙히면,스프링이 뜰때 스프링 컨테이너라는 통이 생긴다
// 스프링 컨테이너 생성이 될 때 -> controller anno 객체를 생성해서 spring이 관리릃 해준다. -> spring bean이 관리된다.
public class MemberController {

    //private final MemberService memberService = new MemberService(); -> new 연산자로 할당하기보다는
    //-> spring 컨테이너에 등록하고, 받아 쓰도록 설계를 해야한다
    //new 의 문제점 다양한 controller에서 사용해야한다. -> 기능이 공용 기능들이다 -> 여러개 생성할 필요성 X
    // -> 하나만 생성하고 공용으로 사용해도 된다. -> 이제 스프링 컨테이너에 등록을 하고 사용을 한다 -> 하나만 등록이 된다.
    //-> 기타 다른 부가적인 이득은 나중에 알아보도록
    private final MemberService memberService;

    @Autowired //생성자 + autowired -> 컨트롤러가 컨테이너에 뜰 때 -> 생성된다 -> 생성자 호출
    //-> autowired ? -> spring이 컨테이너의 memberservice를 주입시켜준다.
    public MemberController(MemberService memberService) { //memberservice에 annotaion을 달아줘야한다. -> 스프링이 알게 해줘야한다.
        //spring이 뜰때 con, ser, rep @annotaion을 읽고 쫙 가지고 올라와준다. 그리고 con <-> service 연결
        // con 생성자 호출 -> spring bean등록된 memberservice 주입 시켜준다. -> dependency injection -> 의존관계 주입 -> 주체가 스프링이다.
        this.memberService = memberService;
    }

    //

}
