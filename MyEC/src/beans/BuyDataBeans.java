package beans;

import java.io.Serializable;
import java.util.Date;

public class BuyDataBeans implements Serializable{
	private int id;
	private int userId;
	private int totalPrice;
	private int deliveryMethod;
	private Date buyDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(int deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
}
