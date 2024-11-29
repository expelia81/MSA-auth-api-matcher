package msa.aa_matcher.automator;


import msa.aa_matcher.domain.AuthContextSpec;
import org.springframework.context.ApplicationContext;

public class AuthAutomator {

	public static AuthContextSpec findAuthEndpoints(ApplicationContext applicationContext) {
		return new AuthContextSpec(applicationContext);
	}

}
