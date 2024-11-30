package msa.aa_matcher.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class AuthController {
	@JsonIgnore
	private static Logger logger = Logger.getLogger(AuthController.class.getName());

	private String name;
	private String path;
	private List<AuthEndpoint> endpoints = new ArrayList<>();

	private Set<String> authCatalog = new HashSet<>();

	private List<String> unAuthorizedEndpoints = new ArrayList<>();

	@JsonIgnore
	Method[] methods;


	public AuthController(Class<?> clazz) {
		methods = clazz.getMethods();
		this.name = clazz.getSimpleName();
		logger.info("AuthController : " + name);
		this.path = Utils.getRequestMappingPath(clazz);
		AuthEndpoint.from(this);
	}

	public void registerUnAuthorizedEndpoint(String path) {
		unAuthorizedEndpoints.add(path);
	}
	public void registerEndpoint(AuthEndpoint endpoint) {
		endpoints.add(endpoint);
	}


	protected static class Utils {
		private final static Set<Class<? extends Annotation>> CONTROLLER_ANNOTATIONS = Set.of(
						org.springframework.stereotype.Controller.class,
						org.springframework.web.bind.annotation.RestController.class
		);

		public static boolean isControllerBean(Class<?> clazz) {
			return CONTROLLER_ANNOTATIONS.stream()
							.anyMatch(clazz::isAnnotationPresent);
		}

		public static String getRequestMappingPath(Class<?> clazz) {
			if (!clazz.isAnnotationPresent(RequestMapping.class)){
				return "";
			}
			return clazz.getAnnotation(RequestMapping.class).value()[0];
		}
	}




	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public List<AuthEndpoint> getEndpoints() {
		return endpoints;
	}

	public List<String> getUnAuthorizedEndpoints() {
		return unAuthorizedEndpoints;
	}

	public Method[] getMethods() {
		return methods;
	}

	public Set<String> getAuthCatalog() {
		return authCatalog;
	}
}
