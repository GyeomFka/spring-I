package intro.spring.study.repository;

import intro.spring.study.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

   MemoryMemberRepository repository = new MemoryMemberRepository();

   @AfterEach
   void afterEach() {
       repository.clearStore();
   }

   @Test
   public void saveTest() {
       Member member = new Member();
       member.setName("spring");

       repository.save(member);

       Member result = repository.findById(member.getId()).get();

       //Assertions.assertEquals(result, member);
       assertThat(member).isEqualTo(result);
   }

   @Test
   void findByNameTest() {
       Member member1 = new Member();
       member1.setName("spring1");
       repository.save(member1);

       Member member2 = new Member();
       member2.setName("spring2");
       repository.save(member2);

       Member result = repository.findByName("spring1").get();

       assertThat(result).isEqualTo(member1);
   }
   @Test
   void findAllTest() {
       Member member1 = new Member();
       member1.setName("spring1");
       repository.save(member1);

       Member member2 = new Member();
       member2.setName("spring2");
       repository.save(member2);

       List<Member> result = repository.findAll();

       assertThat(result.size()).isEqualTo(2);
   }
}