package com.hzmc.nbgsyn.domain.persistence;

import java.io.Serializable;

public class ResultBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8605566028940170996L;
	private String msgId;
	private String msgDesc;
	private String result;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgDesc() {
		return msgDesc;
	}

	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void appendResult(String result) {
		this.result = this.result == null ? "" : this.result;
		this.result += result;
	}

}
