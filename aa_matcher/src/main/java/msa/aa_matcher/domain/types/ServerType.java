package msa.aa_matcher.domain.types;

import java.util.Arrays;

public enum ServerType {
	API("api"),
	AUTH("auth")
	;
	private final String type;
	ServerType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public static ServerType from(String type) {
		for (ServerType serverType : values()) {
			if (serverType.type.equals(type)) {
				return serverType;
			}
		}
		throw new IllegalArgumentException("ServerType is unknown : " + type + "  , available types are : " + Arrays.toString(ServerType.values()));
	}
}
