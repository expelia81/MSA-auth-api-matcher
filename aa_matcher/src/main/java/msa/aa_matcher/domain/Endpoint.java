package msa.aa_matcher.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Endpoint {
	private HttpMethod method;
	private String path;
	private final List<String> auths = new ArrayList<>();

	public Endpoint(String classRequestPath, Method method) {
		System.out.println("Endpoint created from CLASS : " + method.getClass().getName());
		this.path = Path.from(classRequestPath, method);
		this.method = HttpMethod.from(method);
	}}
