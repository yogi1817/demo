# demo
-->Go to dialog flow for Google - https://dialogflow.cloud.google.com/
    1. There should be a default welcome intent, if you want to change it you can customize according to your need.
        a. You can change the default Intent request
        b. You can also change Response, in our example we are only changing the response to say "Hello Athlete, you can as say where is my last order?"
    2. You will now create another intent that will listen to 1b. you can have as many request phrases as you want to train the model.
        The response to this intent will come from a API and there where we have a fulfillment "Enable Webhook for intent".
    3. Last step we will go to Fulfilment and create a webhook. You can provide you api that will be called when user says 1b.
        That's it now we can work on the spring boot code to read the intent and response accordingly.

-->Spring boot code details:
    1. First thing to notice is we have pulled in a dependency - com.google.actions:actions-on-google:1.+'
    2. Include the Java class IdCardDialogFlowApp idCardDialogFlowApp and call handleMessage to process the incoming call
    3. In IdCardDialogFlowApp.java we created a @ForIntent("order_details"), this will take care of incoming message, you will notice the incoming request has that intent.

https://demovoice123.herokuapp.com/voicedemo/orderHistory
Request Body -

{
    "responseId": "8d9335e9-f772-43b8-bf75-5008f925fc82-7f7fc3f3",
    "queryResult": {
    "queryText": "order?",
    "parameters": {},
    "allRequiredParamsPresent": true,
    "fulfillmentMessages": [
    {
    "text": {
    "text": [
    ""
    ]
    }
    }
    ],
    "outputContexts": [
    {
    "name": "projects/dsg-order-details-veje/locations/global/agent/sessions/842456e5-841f-7d09-a362-e9d0eca8966f/contexts/__system_counters__",
    "parameters": {
    "no-input": 0,
    "no-match": 0
    }
    }
    ],
    "intent": {
    "name": "projects/dsg-order-details-veje/locations/global/agent/intents/c621b263-6a66-4ac5-9d1d-4e7ad4067382",
    "displayName": "order_details"
    },
    "intentDetectionConfidence": 1,
    "languageCode": "en",
    "sentimentAnalysisResult": {
    "queryTextSentiment": {
    "score": -0.1,
    "magnitude": 0.1
    }
    }
    },
    "originalDetectIntentRequest": {
    "source": "DIALOGFLOW_CONSOLE",
    "payload": {}
    },
    "session": "projects/dsg-order-details-veje/locations/global/agent/sessions/842456e5-841f-7d09-a362-e9d0eca8966f"
}
