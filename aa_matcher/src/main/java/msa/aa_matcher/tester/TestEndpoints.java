package msa.aa_matcher.tester;

import msa.aa_matcher.annotations.Auth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestEndpoints {

	@GetMapping("/test1")
	@Auth({"admin", "user"})
	public void test1() {
		System.out.println("Test1");
	}
	@GetMapping("/test2")
	@Auth({"admin"})
	public void test2() {
		System.out.println("Test1");
	}
	@GetMapping("test3")
	@Auth({"user","guest"})
	public void test3() {
		System.out.println("Test1");
	}

	@GetMapping("/test4")
	public void test4() {
		System.out.println("Test1");
	}
}
