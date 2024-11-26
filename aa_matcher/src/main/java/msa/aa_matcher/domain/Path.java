package msa.aa_matcher.domain;

import msa.aa_matcher.exception.UnExpectedException;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.rmi.UnexpectedException;
import java.util.logging.Logger;

public class Path {
	private static final Logger logger = Logger.getLogger(Path.class.getName());


	/* TODO
	 * 메소드에서 클래스 기반 경로, 메소드 기반 경로를 합쳐서 하나의 경로를 만들어 반환해야한다.
	 */
	public static String from(String classPath, Method method) {
		String methodPath="";

		for (Annotation annotation : method.getAnnotations()) {
			Class<? extends Annotation> aClass = annotation.annotationType();
			if (aClass.equals(GetMapping.class)) {
				methodPath = method.getAnnotation(GetMapping.class).value()[0];
			} else if (aClass.equals(PostMapping.class)) {
				methodPath = method.getAnnotation(PostMapping.class).value()[0];
			} else if (aClass.equals(PutMapping.class)) {
				methodPath = method.getAnnotation(PutMapping.class).value()[0];
			} else if (aClass.equals(PatchMapping.class)) {
				methodPath = method.getAnnotation(PatchMapping.class).value()[0];
			} else if (aClass.equals(DeleteMapping.class)) {
				methodPath = method.getAnnotation(DeleteMapping.class).value()[0];
			} else {
				// 해당 warning 로그는 찍히는 것 자체가 버그이므로, 찍힐 경우 예외를 발생시킨다.
				logger.warning("HttpMethod not found while creating path : " + method.getName());
				throw new UnExpectedException("HttpMethod not found");
			}
		}

		return mergePath(classPath,methodPath);

	}


	private static String mergePath(String classPath, String methodPath) {
		if (methodPath.isBlank()) {
			return classPath;
		}
		if (methodPath.startsWith("/")) {
			methodPath = methodPath.substring(1);
		} else {
			methodPath = "/" + methodPath;
		}
		String result = classPath + methodPath;
		return result;
	}
}
