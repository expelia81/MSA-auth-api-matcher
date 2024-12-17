package msa.aa_matcher.automator;


import msa.aa_matcher.automator.conditioner.Conditioners;
import msa.aa_matcher.domain.AuthContextSpec;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Conditional;

//@ConditionalOnWebApplication

@Conditional(Conditioners.AuthMatchPushingConditioner.class)
public class AuthAutomator {

	public static AuthContextSpec findAuthEndpoints(ApplicationContext applicationContext) {
		return new AuthContextSpec(applicationContext);
	}
	public AuthContextSpec find(ApplicationContext applicationContext) {
		return new AuthContextSpec(applicationContext);
	}

}
