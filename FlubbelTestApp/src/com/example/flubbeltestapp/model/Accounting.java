package com.example.flubbeltestapp.model;

import java.util.Date;

public class Accounting {
	private int id;
	private double value;
	private int category_id;
	private Date date_created;
	
	public Accounting(){
	}
	
	public Accounting(double value, int category_id, Date date_created) {
		super();
		this.value = value;
		this.category_id = category_id;
		this.date_created = date_created;
	}
	@Override
	public String toString() {
		return "Accounting [id=" + id + ", value=" + value + ", category_id="
				+ category_id + ", date=" + date_created + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public Date getDate() {
		return date_created;
	}
	public void setDate(Date date_created) {
		this.date_created = date_created;
	}
	
	
}
