package msa.aa_matcher.tester;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import msa.aa_matcher.automator.AuthAutomator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Tester {
	@Autowired
	private ApplicationContext applicationContext;


	@RequestMapping("/test99")
	public List<String> test99() {

		System.out.println("Tester initialized");
		Environment env = applicationContext.getEnvironment();
		String basePath = env.getProperty("server.servlet.context-path", env.getProperty("spring.webflux.base-path",""));

		System.out.println("basePathWeb: " + basePath);
		return AuthAutomator.findAuthEndpoints(applicationContext);
	}
}
