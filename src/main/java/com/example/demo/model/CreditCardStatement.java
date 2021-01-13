package com.example.demo.model;

//Samiylo /Story 37 /Statement Service Object
public class CreditCardStatement {
	
	private Long id;
	private Double amount;
	private String category;
	private String item;
	private Boolean userNote;
	
	public CreditCardStatement() {}
	public CreditCardStatement(Long id, Double amount, String category, String item, Boolean userNote) {
		super();
		this.id = id;
		this.amount = amount;
		this.category = category;
		this.item = item;
		this.userNote = userNote;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Boolean getUserNote() {
		return userNote;
	}
	public void setUserNote(Boolean userNote) {
		this.userNote = userNote;
	}
	
	
	
	

}
