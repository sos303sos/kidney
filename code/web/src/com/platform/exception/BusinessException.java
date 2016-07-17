package com.platform.exception;

public class BusinessException extends RuntimeException{
	private String err_code;
	private Object err_obj;
	
	public String getErr_code() {
		return err_code;
	}
	
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	
	public BusinessException(String err_code){
	    this(err_code,null);
	}
	
	public BusinessException(String err_code,Object obj){
		this.setErr_code(err_code);
		this.setErr_obj(obj);
	}
	
    public Object getErr_obj() {
        return err_obj;
    }
    
    public void setErr_obj(Object err_obj) {
        this.err_obj = err_obj;
    }
	
	
}
