package msa.aa_matcher.automator;


import msa.aa_matcher.annotations.Auth;
import msa.aa_matcher.domain.AuthContextSpec;
import msa.aa_matcher.domain.AuthController;
import msa.aa_matcher.domain.AuthEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AuthAutomator {

	public static AuthContextSpec findAuthEndpoints(ApplicationContext applicationContext) {
		return new AuthContextSpec(applicationContext);
	}

	private static void processAuthEndpoint(Method method, List<AuthEndpoint> authMethods, String classRequestMapping) {
//		if (isEndpointMethod(method) && method.isAnnotationPresent(Auth.class)) {
		if (method.isAnnotationPresent(Auth.class)) {
			System.out.println("Found Auth endpoint: " + method.getDeclaringClass().getSimpleName() + "." + method.getName());
			AuthEndpoint endpoint = new AuthEndpoint(classRequestMapping, method);
			authMethods.add(endpoint);
		}
	}
	private static boolean isEndpointMethod(Method method) {
		return method.isAnnotationPresent(RequestMapping.class) ||
						method.isAnnotationPresent(GetMapping.class) ||
						method.isAnnotationPresent(PostMapping.class) ||
						method.isAnnotationPresent(DeleteMapping.class) ||
						method.isAnnotationPresent(PutMapping.class) ||
						method.isAnnotationPresent(PatchMapping.class);
	}
}
