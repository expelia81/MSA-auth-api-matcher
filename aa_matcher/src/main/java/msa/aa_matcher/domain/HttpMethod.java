package msa.aa_matcher.domain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public enum HttpMethod {
	GET("GetMapping"),
	POST("PostMapping"),
	PUT("PutMapping"),
	PATCH("PatchMapping"),
	DELETE("DeleteMapping")
	;

	private final String annotation;

	HttpMethod(String annotation) {
		this.annotation = annotation;
	}

	public static HttpMethod from(Method method) {
		Annotation[] annotations = method.getAnnotations();
		for (Annotation annotation : annotations) {
			String annotationName = annotation.annotationType().getSimpleName();
			for (HttpMethod httpMethod : HttpMethod.values()) {
				if (httpMethod.annotation.equals(annotationName)) {
					return httpMethod;
				}
			}
		}
		throw new RuntimeException("HttpMethod not found");
	}
}
