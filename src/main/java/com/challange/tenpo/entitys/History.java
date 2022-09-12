package com.challange.tenpo.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "History")
public class History {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "statuscode")
    private int statusCode;

    @Column(name = "time")
    private String timeStamp;

    @Column(name = "method")
    private String method;


	public History(Long id, String endpoint, int statusCode, String timeStamp, String method) {
		super();
		this.id = id;
		this.endpoint = endpoint;
		this.statusCode = statusCode;
		this.timeStamp = timeStamp;
		this.method = method;
	}

	public History() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEndpoint() {
		return endpoint;
	}


	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public String getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}
}
