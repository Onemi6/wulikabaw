package com.example.wulikabaw;

public class UserInformation {
	public int logo_id;  
	public String zhanghao;
	public String mima;
	public String phone;
	public String email;
	public int credit;
	
	public UserInformation(){
		super();
	}

	public UserInformation(int logo_id,String zhanghao,String mima,String phone,String email,int credit){
		super();
		this.logo_id=logo_id;
		this.zhanghao=zhanghao;
		this.mima=mima;
		this.phone=phone;
		this.email=email;
		this.credit=credit;
	}
	
	public int getlogo(){
		return logo_id;
	}
	
	public void setlogo(int logo_id){
		this.logo_id=logo_id;
	}
	
	public String getzhanghao(){
		return zhanghao;
	}
	
	public void setzhanghao(String zhanghao){
		this.zhanghao=zhanghao;
	}
	
	public String getmima(){
		return mima;
	}
	
	public void setmima(String mima){
		this.mima=mima;
	}
	
	public String getphone(){
		return phone;
	}
	
	public void setphone(String phone){
		this.phone=phone;
	}
	
	public String getemail(){
		return email;
	}
	
	public void setemail(String email){
		this.email=email;
	}
	
	public int getcredit(){
		return credit;
	}
	
	public void setcredit(int credit){
		this.credit=credit;
	}
}
