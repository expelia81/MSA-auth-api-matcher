package msa.aa_matcher.domain;

import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class AuthContextSpec {
	private final String contextPath;

	private List<String> authCatalog = new ArrayList<>();

	private List<AuthController> controllers = new ArrayList<>();

	private List<AuthEndpoint> endpoints = new ArrayList<>();


	public AuthContextSpec(ApplicationContext applicationContext) {
		this.contextPath = applicationContext.getEnvironment().getProperty("server.servlet.context-path", applicationContext.getEnvironment().getProperty("spring.webflux.base-path", ""));

		String[] beanNames = applicationContext.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			Class<?> clazz = applicationContext.getBean(beanName).getClass();
			if (!AuthController.Utils.isControllerBean(clazz)) {
				continue;
			}
			AuthController controller = new AuthController(clazz);
		}
	}

	public void registerController(AuthController controller) {
		controllers.add(controller);
		controller.getEndpoints().forEach(endpoint -> {
			endpoints.add(endpoint);
			authCatalog.addAll(endpoint.getAuths());
		});
	}
}
