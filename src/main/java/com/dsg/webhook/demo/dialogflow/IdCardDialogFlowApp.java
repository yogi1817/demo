package com.dsg.webhook.demo.dialogflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dsg.webhook.demo.pojo.Order;
import com.dsg.webhook.demo.pojo.Orders;
import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IdCardDialogFlowApp extends DialogflowApp{

	@Autowired
	private RestTemplate restTemplate;
	
	@ForIntent("order_details")
	public ActionResponse requestOrderHistory(ActionRequest request) {
		log.info("idCardRequest intent start.");
		
		ResponseBuilder responseBuilder = getResponseBuilder(request);
		responseBuilder.add(getResponseString());
		log.info("idCardRequest intent end.");
		
		return responseBuilder.build();
	}

	private String getResponseString() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("secret_key", "ESETXC1V1Zim2jwUL1lw");
		headers.set("partner_key", "myaccount_ui");
		headers.set("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik5qSkJSalkxTWpCRU1FUkRRekl5T1RZNU1FWkVSakExTWpBMFFVTkNRelJHTURNNE9FRTJSQSJ9.eyJodHRwOi8vd3d3LmRpY2tzc3BvcnRpbmdnb29kcy5jb20vc2NvcmVjYXJkbnVtYmVyIjoiNjA4OTc1NzMwMDE2IiwiaHR0cDovL3d3dy5kaWNrc3Nwb3J0aW5nZ29vZHMuY29tL2lkZW50aXR5SWQiOiIzYTk4Yjk4Yy02ZGE0LTRmMGMtOGU0Ni0xZGVlNzA1Y2E0MGMiLCJpc3MiOiJodHRwczovL3Nzby5kaWNrc3Nwb3J0aW5nZ29vZHMuY29tLyIsInN1YiI6ImF1dGgwfDVmMzM1NmQ2NWQ2NGFmNGEwZjg4OGYxNyIsImF1ZCI6WyJkc2ctand0cyIsImh0dHBzOi8vZGNzZy1hdGhsZXRlLmRpY2tzc3BvcnRpbmdnb29kcy5hdXRoMC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNTk5MDc5MzEwLCJleHAiOjE1OTkxNjU3MTAsImF6cCI6IlE3c2g4alFReXNqOWhtSWQ0QjdrOU5QWmM0cGpIWExkIiwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCByZWFkOmlkZW50aXR5In0.U7CVlb3IN2aMRASzWJyi9sG7_Oc6v9OWe0ZMcBOTgaRtDZhuBcWTLuxPHJhwQV3jtvsXX835Okv6N1GTk2RFlqIzOhN9shKOW4LZuGxt8SBbq_FOBQs0Pxm1rVoLF1Q2_eHoCfqbwvGNSn36vfCLBTS4iI6RpP21kSn16srPdUup0qLdKYHZavwLQTTKfqDm9iF1wgZ3tTKnV2M9uk3fZqi8VyxwxNvXLite1UkNrunG5tBtLRgK-IMoMMuBirUuWvJN1E87znDnRZRvBsWbQ_COdFqF8Nn6TQj3LzaA65JqwzDw-8x37OVfk_4oD75YM26dPCyCVZfKCfcib8deBQ");

		HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);

		ResponseEntity<Orders> ordersEntity 
			= restTemplate.exchange("https://move.dksxchange.com/user/api/v2/orderHistory?storeId=&startIndex=0&endIndex=99", 
					HttpMethod.POST, request, Orders.class);
		
		Order order = ordersEntity.getBody().getOrders().get(0);
		return "You order no "+order.getOrdersId()+" and order total "+order.getOrderTotal()+" is "+ (order.getStatus().equals("D") ? "Complete": "In progress");
	}
}
