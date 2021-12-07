package intro.spring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudyApplicationTests {

	@BeforeEach
	void setup() {
	}

	@Test
	void contextLoads() {
		assertThat(1).isEqualTo(1);
	}

}
