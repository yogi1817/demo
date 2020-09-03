package com.dsg.webhook.demo.adapters;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dsg.webhook.demo.dialogflow.IdCardDialogFlowApp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class VoiceDemoService implements IVoiceDemoService {

	@Autowired
	private IdCardDialogFlowApp idCardDialogFlowApp;
	
	@Override
	public String requestOrderHistory(String payloadAsString, Map<String, String> headers) {
		String jsonResponse = "No Data Found";
		try {
			jsonResponse = idCardDialogFlowApp.handleRequest(payloadAsString, headers).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		log.info(jsonResponse);
		return jsonResponse;
	}

}
