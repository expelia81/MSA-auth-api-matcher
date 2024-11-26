package msa.aa_matcher.domain;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class AuthContextSpec {
	private final String contextPath;

	private List<String> authCatalog;

	private List<AuthController> controllers;

	private List<AuthEndpoint> endpoints;


	public AuthContextSpec(ApplicationContext applicationContext) {
		this.contextPath = applicationContext.getEnvironment().getProperty("server.servlet.context-path", applicationContext.getEnvironment().getProperty("spring.webflux.base-path", ""));
	}
}
