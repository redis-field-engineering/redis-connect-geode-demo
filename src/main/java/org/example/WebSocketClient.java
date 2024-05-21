package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.*;

@ClientEndpoint
public class WebSocketClient {
    ObjectMapper mapper = new ObjectMapper();
    @OnOpen
    public void onOpen(Session session){
        System.out.println("Connected to server");
        try {
            session.getBasicRemote().sendText("{\"type\":\"subscribe\",\"symbol\":\"AAPL\"}");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message){
        try{
            Message msg = mapper.readValue(message, Message.class);
            if(msg != null && msg.data != null){
                for(Trade trade : msg.data){
                    String serializedJson = mapper.writeValueAsString(trade);
                    System.out.println("Trade message: " +  serializedJson);
                    TradePipeline.sendMessage(trade);
                }
            }


        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose() {
        System.out.println("Disconnected from server");
    }

    @OnError
    public void onError(Throwable throwable) {
        System.err.println("Error: " + throwable.getMessage());
    }
}
