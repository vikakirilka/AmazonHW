package org.ITstep.model;

public class InterimTable {

	private String name;
	private String asin;
	private String addToCart;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAsin() {
		return asin;
	}
	public void setAsin(String asin) {
		this.asin = asin;
	}
	public String getAddToCart() {
		return addToCart;
	}
	public void setAddToCart(String addToCart) {
		this.addToCart = addToCart;
	}
	public InterimTable(String name, String asin, String addToCart) {
		super();
		this.name = name;
		this.asin = asin;
		this.addToCart = addToCart;
	}
	public InterimTable() {
	}
}
