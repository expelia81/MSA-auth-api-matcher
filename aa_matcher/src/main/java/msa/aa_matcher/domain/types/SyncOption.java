package msa.aa_matcher.domain.types;

import java.util.Arrays;

public enum SyncOption {

	PULL("pull", "api -> auth   data pull"),
	PUSH("push", "api -> auth   data push")
	;
	private final String type;
	private final String description;
	SyncOption(String type, String description) {
		this.type = type;
		this.description = description;
	}
	public String getType() {
		return type;
	}

	/**
	 * @throws IllegalArgumentException if the type is unknown
	 */
	public static SyncOption from(String type) {
		for (SyncOption serverType : values()) {
			if (serverType.type.equals(type)) {
				return serverType;
			}
		}
		throw new IllegalArgumentException("SyncType is unknown : " + type    + " , available types are : " + Arrays.toString(SyncOption.values()));
	}

	public String getDescription() {
		return description;
	}
}
