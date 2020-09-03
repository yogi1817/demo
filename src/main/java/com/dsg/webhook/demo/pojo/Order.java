package com.dsg.webhook.demo.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ordersId;
	private int storeUniqueID;
	private String status;
	private Date lastUpdate;
	private Date timePlaced;
	private float orderTotal;
	private String type;
	private String origin;
	private float totalShippingCharge;
	private float totalSalesTax;
	private float totalShippingTax;
	private float totalAdjustment;
	private boolean ØreadyForPickup;
}
