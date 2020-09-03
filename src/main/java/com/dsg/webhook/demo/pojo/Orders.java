package com.dsg.webhook.demo.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Orders implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Order>orders;
}
