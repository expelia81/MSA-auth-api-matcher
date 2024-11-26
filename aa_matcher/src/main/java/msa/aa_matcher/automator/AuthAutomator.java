package msa.aa_matcher.automator;


import msa.aa_matcher.annotations.Auth;
import msa.aa_matcher.domain.AuthEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AuthAutomator {

	public static List<String> findAuthEndpoints(ApplicationContext applicationContext) {
		List<AuthEndpoint> authMethods = new ArrayList<>();
		String[] beanNames = applicationContext.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			Object bean = applicationContext.getBean(beanName);
			if (!isControllerBean(bean)) {
				continue;
			}
			Method[] methods = bean.getClass().getMethods();
			String classRequestMapping = "/";
			if (bean.getClass().isAnnotationPresent(RequestMapping.class)) {
				classRequestMapping = bean.getClass().getAnnotation(RequestMapping.class).value()[0];
			}
			for (Method method : methods) {
				processAuthEndpoint(method, authMethods, classRequestMapping);
			}
		}
		return List.of("test1", "test2", "test3");
	}

	private static void processAuthEndpoint(Method method, List<AuthEndpoint> authMethods, String classRequestMapping) {
//		if (isEndpointMethod(method) && method.isAnnotationPresent(Auth.class)) {
		if (method.isAnnotationPresent(Auth.class)) {
			System.out.println("Found Auth endpoint: " + method.getDeclaringClass().getSimpleName() + "." + method.getName());
			AuthEndpoint endpoint = new AuthEndpoint(classRequestMapping, method);
			authMethods.add(endpoint);
		}
	}
	private static boolean isControllerBean(Object bean) {
			return bean.getClass().isAnnotationPresent(Controller.class) ||
							bean.getClass().isAnnotationPresent(RestController.class);
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
