package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
//	MemberRepository repository = new MemoryMemberRepository();
	MemoryMemberRepository repository = new MemoryMemberRepository();

	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}

	@Test       // org.junit.jupiter.api.Test;
	public void save() {
		Member member = new Member();
		member.setName("spring");

		repository.save(member);
		Member result = repository.findById(member.getId()).get();  //optional반환인데 get은 사실 좋지않다.
//		System.out.println("result = " + (result == member));
//		Assertions.assertEquals(member, result);        // org.assertj.core.api.Assertions;
		assertThat(member).isEqualTo(result);    // org.assertj.core.api.Assertions;
		// Assertions 에 대고 Alt + Enter -> static import
	}

	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);


		Member member2 = new Member();     // shift + F6 -> 복사후 rename 간편
		member2.setName("spring2");
		repository.save(member2);

		Member result = repository.findByName("spring1").get(); // get을 쓰면 Optional을 깔 수 있다.

		assertThat(result).isEqualTo(member1);
	}

	@Test
	public void findAll() {
		Member member3 = new Member();
		member3.setName("spring3");
		repository.save(member3);


		Member member4 = new Member();     // shift + F6 -> 복사후 rename 간편
		member4.setName("spring4");
		repository.save(member4);

		List<Member> result = repository.findAll();

		assertThat(result.size()).isEqualTo(2);
	}

}
