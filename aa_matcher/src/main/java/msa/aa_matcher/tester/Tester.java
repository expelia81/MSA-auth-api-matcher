package msa.aa_matcher.tester;

import jakarta.annotation.PostConstruct;
import msa.aa_matcher.automator.AuthAutomator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class Tester {
	@Autowired
	private ApplicationContext applicationContext;

	@GetMapping("/test99")
	public void test99() {
		AuthAutomator.findAuthEndpoints(applicationContext);
	}
}
