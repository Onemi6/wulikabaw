package com.example.wulikabaw;

public class Credit_change {

	private String credit_need;
	private int imageid;
	private int value;
	
	public Credit_change(String credit_need, int imageid,int value){
		this.credit_need = credit_need;
		this.imageid = imageid;
		this.value = value;
	}
	
	public String getCredit_Need(){
		return credit_need;
	}
	public int getImageid(){
		return imageid;
	}
	public int getValue(){
		return value;
	}
}
