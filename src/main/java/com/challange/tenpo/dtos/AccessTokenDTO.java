package com.challange.tenpo.dtos;

import java.io.Serializable;

public class AccessTokenDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;
    
	public AccessTokenDTO(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}