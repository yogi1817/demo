package com.dsg.webhook.demo.dialogflow;

import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IdCardDialogFlowApp extends DialogflowApp{

	@ForIntent("order_details")
	public ActionResponse requestOrderHistory(ActionRequest request) {
		log.info("idCardRequest intent start.");

		ResponseBuilder responseBuilder = getResponseBuilder(request);
		responseBuilder.add(getResponseStringFromApi());
		log.info("idCardRequest intent end.");

		return responseBuilder.build();
	}

	private String getResponseStringFromApi() {
		return "You order no "+ 123456 +" and order total "+ 50+"$ is In progress";
	}
}
