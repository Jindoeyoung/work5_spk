package com.spk.api.util;

public class ReturnException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	final Object value;
	
	public ReturnException(Object value, String message){
		super(message);
		this.value = value;
//		getValue();
	}
	
	public Object getValue(){
//		System.out.println("===getValue==="+value);
//		System.out.println("===getValue.toString==="+value.toString());
		return value.toString();
	}	
	
	
//	private static final long serialVersionUID = 1L;
//	
//	final Object value;
//	
//	public ReturnException(Object value, String message){
//		super(message);
//		this.value = value;
//	}
//	
//	public Object getValue(){
//		return value;
//	}
}
