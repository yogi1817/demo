package com.dsg.webhook.demo.adapters;

import java.util.Map;

public interface IVoiceDemoService {
	public String requestOrderHistory(String payloadAsString, Map<String, String> headers);
}
