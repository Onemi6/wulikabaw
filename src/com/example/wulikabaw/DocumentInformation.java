package com.example.wulikabaw;

public class DocumentInformation {

	public String type;
	public String card_name;
	public String name;
	public String number;
	public String balance;
	public String zhanghao;

	public DocumentInformation() {
		super();
	}

	public DocumentInformation(String type,String card_name,String name,String number,String balance,String zhanghao) {
		super();
		this.type = type;
		this.card_name = card_name;
		this.name = name;
		this.number = number;
        this.balance = balance;
        this.zhanghao = zhanghao;
	}

	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}

	public String getcard_name() {
		return card_name;
	}

	public void setcard_name(String card_name) {
		this.card_name = card_name;
	}
	
	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getnumber() {
		return number;
	}

	public void setnumber(String number) {
		this.number = number;
	}
	
	public String getbalance() {
		return balance;
	}

	public void setbalance(String balance) {
		this.balance = balance;
	}

	public String getzhanghao() {
		return zhanghao;
	}

	public void setzhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
	}
	
}
