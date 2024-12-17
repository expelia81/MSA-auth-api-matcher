package msa.aa_matcher.automator.conditioner;

import msa.aa_matcher.domain.types.ServerType;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class Conditioners {
	private static final String webBasePath = "server.servlet.context-path";
	private static final String webfluxBasePath = "spring.webflux.base-path";
	private static final String getServerType = "auth-matcher.type";
	public static class ApiServerMatcher implements Condition {
		/** 보통, 아래처럼 야믈 값 기준으로 판단하거나, 적용된 어노테이션을 기반하여 사용할 수 있다.
		 */
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			// 해당 서버의 설정값을 가져올 수 있다.

			String[] testers = context.getEnvironment().getProperty(getServerType, String[].class,  new String[0]);
			for (String tester : testers) {
				if (ServerType.API.getType().equals(tester)) {
					return true;
				}
			}
			return false;
		}
	}
	public static class AuthServerMatcher implements Condition {
		/** 보통, 아래처럼 야믈 값 기준으로 판단하거나, 적용된 어노테이션을 기반하여 사용할 수 있다.
		 */
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			// 해당 서버의 설정값을 가져올 수 있다.

			String[] testers = context.getEnvironment().getProperty(getServerType, String[].class,  new String[0]);
			for (String tester : testers) {
				if (ServerType.API.getType().equals(tester)) {
					return true;
				}
			}
			return false;
		}
	}
	public static class AuthMatchPullingConditioner implements Condition {
		/** 보통, 아래처럼 야믈 값 기준으로 판단하거나, 적용된 어노테이션을 기반하여 사용할 수 있다.
		 */
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			// 해당 서버의 설정값을 가져올 수 있다.

			String[] testers = context.getEnvironment().getProperty(getServerType, String[].class,  new String[0]);
			for (String tester : testers) {
				if (ServerType.API.getType().equals(tester)) {
					return true;
				}
			}
			return false;
		}
	}
	public static class AuthMatchPushingConditioner implements Condition {
		/** 보통, 아래처럼 야믈 값 기준으로 판단하거나, 적용된 어노테이션을 기반하여 사용할 수 있다.
		 */
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			// 해당 서버의 설정값을 가져올 수 있다.

			BeanFactory beanFactory = context.getBeanFactory();
			System.out.println("beanFactory: " + beanFactory);

			if (beanFactory.containsBean("authConfigs")) {
				System.out.println("authConfigs is found");
				return true;
			}

			String[] testers = context.getEnvironment().getProperty(getServerType, String[].class,  new String[0]);
			for (String tester : testers) {
				if (ServerType.API.getType().equals(tester)) {
					return true;
				}
			}
			return false;
		}
	}

}
