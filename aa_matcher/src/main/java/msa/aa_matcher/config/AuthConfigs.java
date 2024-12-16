package msa.aa_matcher.config;

import msa.aa_matcher.automator.conditioner.Conditioners;
import msa.aa_matcher.domain.types.ServerType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "auth-matcher")
@Configuration
@Conditional({Conditioners.ApiServerMatcher.class, Conditioners.AuthServerMatcher.class})
public class AuthConfigs {
	private ServerType type;
	private String[] servers;
	private String syncOption;

	public String getSyncOption() {
		return syncOption;
	}

	public void setSyncOption(String syncOption) {
		this.syncOption = syncOption;
	}

	public ServerType getType() {
		return type;
	}
	public void setType(ServerType type) {
		this.type = type;
	}

	public String[] getServers() {
		return servers;
	}

	public void setServers(String[] servers) {
		this.servers = servers;
	}
}
