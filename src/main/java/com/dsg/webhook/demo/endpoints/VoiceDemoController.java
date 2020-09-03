package com.dsg.webhook.demo.endpoints;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dsg.webhook.demo.adapters.IVoiceDemoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class VoiceDemoController {

	@Autowired
	private IVoiceDemoService voiceDemoService;

	@PostMapping(value = "orderHistory", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> requestOrderHistory(@RequestBody Object payload,
			@RequestHeader Map<String, String> headers){
		ObjectMapper objMapper = new ObjectMapper();
		log.info("payload "+payload);
		String payloadAsString = null;
		try { 
			payloadAsString = objMapper.writeValueAsString(payload); 
        }  catch (IOException e) { 
        	return new ResponseEntity<>("Unable to parse the request", HttpStatus.BAD_REQUEST);
        } 
		return new ResponseEntity<>(voiceDemoService.requestOrderHistory(payloadAsString, headers), HttpStatus.OK);
	}
}