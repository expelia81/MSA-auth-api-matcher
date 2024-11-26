package msa.aa_matcher.domain;

import msa.aa_matcher.annotations.Auth;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class AuthEndpoint {
	private static final Logger logger = Logger.getLogger(AuthEndpoint.class.getName());
	private HttpMethod method;
	private String path;
	private final List<String> auths = new ArrayList<>();

	/**
	 * Endpoint의 기초가 되는 생성자
	 */
	public AuthEndpoint(String classRequestPath, Method method) {
		this.path = Path.from(classRequestPath, method);
		this.method = HttpMethod.from(method);
	}
	public static void from(AuthController controller) {
		for (Method method : controller.methods) {
			if (!Utils.isEndpoint(method)) {
				continue;
			}
			String path = Path.from(controller.path, method);
			if (Utils.isAuthEndpoint(method)) {
				AuthEndpoint endpoint = new AuthEndpoint(controller.path, method);
				List<String> auths = Utils.getAuths(method);
				endpoint.auths.addAll(auths);
				controller.registerEndpoint(endpoint);
			} else {
				controller.registerUnAuthorizedEndpoint(path);
			}
		}
	}
	private static class Utils {
		private final static Set<Class<? extends Annotation>> ENDPOINT_ANNOTATIONS = Set.of(
						GetMapping.class,
						PostMapping.class,
						PutMapping.class,
						DeleteMapping.class,
						PatchMapping.class
		);

		public static boolean isEndpoint(Method method) {
			return ENDPOINT_ANNOTATIONS.stream()
							.anyMatch(method::isAnnotationPresent);
		}
		public static boolean isAuthEndpoint(Method method) {
			return method.isAnnotationPresent(Auth.class);
		}

		public static List<String> getAuths(Method method) {
			Auth auth = AnnotationUtils.getAnnotation(method, Auth.class);
			if (auth == null) {
				return List.of();
			}
			return Arrays.asList(auth.value());
		}
	}
}
