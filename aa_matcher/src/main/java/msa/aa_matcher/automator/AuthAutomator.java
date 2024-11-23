package msa.aa_matcher.automator;


import msa.aa_matcher.annotations.Auth;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AuthAutomator {

	public static void findAuthEndpoints(ApplicationContext applicationContext) {
		List<Method> authMethods = new ArrayList<>();
		String[] beanNames = applicationContext.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			Object bean = applicationContext.getBean(beanName);
			Method[] methods = bean.getClass().getMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(Auth.class)) {
					authMethods.add(method);
					// 원하는 방식으로 처리 (예: 로깅)
					System.out.println("Found @Auth method: " + method.getName() + " in bean: " + beanName);
				}
			}
		}
	}

	private void processAuthEndpoint(Method method, List<Method> authMethods) {
		if (isEndpointMethod(method) && method.isAnnotationPresent(Auth.class)) {
			authMethods.add(method);
		}
	}
	private boolean isEndpointMethod(Method method) {
		return method.isAnnotationPresent(RequestMapping.class) ||
						method.isAnnotationPresent(GetMapping.class) ||
						method.isAnnotationPresent(PostMapping.class) ||
						method.isAnnotationPresent(DeleteMapping.class) ||
						method.isAnnotationPresent(PutMapping.class) ||
						method.isAnnotationPresent(PatchMapping.class);
	}
}
