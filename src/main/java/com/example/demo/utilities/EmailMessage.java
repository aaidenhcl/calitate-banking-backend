package com.example.demo.utilities;

public class EmailMessage {

	private String toAddr;
	private String fromAddr;
	
	private String subject;
	private String msgBody;
	
	public EmailMessage() {}
	
	public EmailMessage(String toAddr, String fromAddr, String subject, String msgBody) {
		this.toAddr = toAddr;
		this.fromAddr = fromAddr;
		this.subject = subject;
		this.msgBody = msgBody;
	}

	public String getToAddr() {
		return toAddr;
	}

	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}

	public String getFromAddr() {
		return fromAddr;
	}

	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	@Override
	public String toString() {
		return "EmailMessage [toAddr=" + toAddr + ", fromAddr=" + fromAddr + ", subject=" + subject + ", msgBody="
				+ msgBody + "]";
	}
}
