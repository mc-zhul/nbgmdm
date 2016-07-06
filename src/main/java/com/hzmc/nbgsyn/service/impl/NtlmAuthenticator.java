package com.hzmc.nbgsyn.service.impl;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class NtlmAuthenticator extends Authenticator {
	private final String username;
	private final char[] password;

	public NtlmAuthenticator(final String username, final String password) {
		super();
		this.username = new String(username);
		this.password = password.toCharArray();
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return (new PasswordAuthentication(username, password));
	}
}
