package msa.aa_matcher.domain;

import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Path {


	/* TODO
	 * 메소드에서 클래스 기반 경로, 메소드 기반 경로를 합쳐서 하나의 경로를 만들어 반환해야한다.
	 */
	public static String from(String classRequestPath, Method method) {
		String methodPath = "";

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
			}
		}

		return methodPath;

	}


	/* TODO
	 * 클래스 기반 경로와 메소드 기반 경로를 합쳐서 하나의 경로를 만들어 반환해야한다.
	 * 단, classRequestPath, methodPath에 /가 중복되지 않도록 잘 처리해야한다.
	 */
	public static String mergePath(String classRequestPath, String methodPath) {
		return classRequestPath + methodPath;
	}
}
