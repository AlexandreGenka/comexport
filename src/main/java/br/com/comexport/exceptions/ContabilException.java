package br.com.comexport.exceptions;

public class ContabilException extends Exception {

	private static final long serialVersionUID = 204339375576872391L;

	private Integer statusCode;

	public ContabilException(Exception e) {
		super(e);
	}

	public ContabilException(String msg) {
		super(msg);
	}
	
	public ContabilException(Exception e, Integer statusCode) {
		super(e);
		this.statusCode = statusCode;
	}

	public ContabilException(String msg, Integer statusCode) {
		super(msg);
		this.statusCode = statusCode;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

}
