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
		headers.set("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik5qSkJSalkxTWpCRU1FUkRRekl5T1RZNU1FWkVSakExTWpBMFFVTkNRelJHTURNNE9FRTJSQSJ9.eyJodHRwOi8vd3d3LmRpY2tzc3BvcnRpbmdnb29kcy5jb20vc2NvcmVjYXJkbnVtYmVyIjoiNjA4OTc1NzMwMDE2IiwiaHR0cDovL3d3dy5kaWNrc3Nwb3J0aW5nZ29vZHMuY29tL2lkZW50aXR5SWQiOiIzYTk4Yjk4Yy02ZGE0LTRmMGMtOGU0Ni0xZGVlNzA1Y2E0MGMiLCJpc3MiOiJodHRwczovL3Nzby5kaWNrc3Nwb3J0aW5nZ29vZHMuY29tLyIsInN1YiI6ImF1dGgwfDVmMzM1NmQ2NWQ2NGFmNGEwZjg4OGYxNyIsImF1ZCI6WyJkc2ctand0cyIsImh0dHBzOi8vZGNzZy1hdGhsZXRlLmRpY2tzc3BvcnRpbmdnb29kcy5hdXRoMC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNjAwMDkzNTgzLCJleHAiOjE2MDAxNzk5ODMsImF6cCI6IlE3c2g4alFReXNqOWhtSWQ0QjdrOU5QWmM0cGpIWExkIiwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCByZWFkOmlkZW50aXR5In0.urvwdrYD5eoJGszZnQQoHk6v4We45QHDISQn1rnv5NuuC-oOw0nfdgC_NXGWa2zZ816o99FYGqcdJRtgRPqxhj_OLgLRTUogEGZ61gGS8Et6iZF7MG_EL6wi_aQnFMg_v4lU5-LKNqZgfBnj4LhkhxCFc1mRK7YTMAq-etFAD43A6ej_TRZ_Idx5-jAlnekDWI2w4X0tJ4OQtrBOvOhEorIRV17hWe9mywDv6TImrjDUinrEp6B5zrKZ2K6-_tiZI38PcF0BcH4qoOwG0qlV3ovXgWgUrQDo1_3RPgTv7yCvgxz3-plzOBZ-asEUQ6aQyTJHmyqn_Ztu_9c1r5hY5A");

		HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);

		ResponseEntity<Orders> ordersEntity 
			= restTemplate.exchange("https://move.dksxchange.com/user/api/v2/orderHistory?storeId=&startIndex=0&endIndex=99", 
					HttpMethod.GET, request, Orders.class);
		
		Order order = ordersEntity.getBody().getOrders().get(0);
		return "You order no "+order.getOrdersId()+" and order total "+order.getOrderTotal()+" is "+ (order.getStatus().equals("D") ? "Complete": "In progress");
	}
}
