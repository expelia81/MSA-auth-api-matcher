package msa.aa_matcher.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthController {

	String name;
	String path;
	List<AuthEndpoint> endpoints = new ArrayList<>();

	List<String> unAuthorizedEndpoints = new ArrayList<>();

	@JsonIgnore
	Method[] methods;

	public AuthController(Class<?> clazz) {
		methods = clazz.getMethods();
		this.name = clazz.getSimpleName();
		this.path = Utils.getRequestMappingPath(clazz);
		AuthEndpoint.from(this);
	}

	public void registerUnAuthorizedEndpoint(String path) {
		unAuthorizedEndpoints.add(path);
	}
	public void registerEndpoint(AuthEndpoint endpoint) {
		endpoints.add(endpoint);
	}


	private static class Utils {
		private final static Set<Class<? extends Annotation>> CONTROLLER_ANNOTATIONS = Set.of(
						org.springframework.stereotype.Controller.class,
						org.springframework.web.bind.annotation.RestController.class
		);

		public static boolean isControllerBean(Class<?> clazz) {
			return CONTROLLER_ANNOTATIONS.stream()
							.anyMatch(clazz::isAnnotationPresent);
		}

		public static String getRequestMappingPath(Class<?> clazz) {
			if (clazz.isAnnotationPresent(RequestMapping.class)){
				return "";
			}
			return clazz.getAnnotation(RequestMapping.class).value()[0];
		}
	}
}
