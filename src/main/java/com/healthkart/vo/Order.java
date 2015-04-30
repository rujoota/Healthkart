/**
 * 
 */
package com.healthkart.vo;

import java.time.LocalDateTime;

/**
 * @author Rajan
 *
 */
public class Order {

	private String orderID;
	private String userID;
	private String orderStatus;
	private String orderDate;
	private String shippingDate;
	private Address shippngAddress;
	private String deliveredOrCancelledDate;
	private String prescription;
	private String discount;
	private LocalDateTime createDate;
	private LocalDateTime modifiedDate;
	/**
	 * @return the orderID
	 */
	public String getOrderID() {
		return orderID;
	}
	/**
	 * @param orderID the orderID to set
	 */
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the shippingDate
	 */
	public String getShippingDate() {
		return shippingDate;
	}
	/**
	 * @param shippingDate the shippingDate to set
	 */
	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}
	/**
	 * @return the shippngAddress
	 */
	public Address getShippngAddress() {
		return shippngAddress;
	}
	/**
	 * @param shippngAddress the shippngAddress to set
	 */
	public void setShippngAddress(Address shippngAddress) {
		this.shippngAddress = shippngAddress;
	}
	/**
	 * @return the deliveredOrCancelledDate
	 */
	public String getDeliveredOrCancelledDate() {
		return deliveredOrCancelledDate;
	}
	/**
	 * @param deliveredOrCancelledDate the deliveredOrCancelledDate to set
	 */
	public void setDeliveredOrCancelledDate(String deliveredOrCancelledDate) {
		this.deliveredOrCancelledDate = deliveredOrCancelledDate;
	}
	/**
	 * @return the prescription
	 */
	public String getPrescription() {
		return prescription;
	}
	/**
	 * @param prescription the prescription to set
	 */
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	/**
	 * @return the discount
	 */
	public String getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	/**
	 * @return the createDate
	 */
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the modifiedDate
	 */
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
