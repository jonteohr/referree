package net.jonteohr.referee.core;

public class Code {

	private String code;
	private String guildId;
	private String userId;
	private int uses;

	public Code(String code, String guildId, String userId, int uses) {
		this.code = code;
		this.guildId = guildId;
		this.userId = userId;
		this.uses = uses;
	}

	public String getCode() {
		return this.code;
	}
	public String getGuildId() {
		return this.guildId;
	}
	public String getUserId() {
		return this.userId;
	}
	public int getUses() {
		return this.uses;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUses(int uses) {
		this.uses = uses;
	}

}
