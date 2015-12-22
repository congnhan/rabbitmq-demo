package org.mq.demo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.StringRpcServer;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by BinBo on 12/18/2015.
 */
public class receiver implements queues {
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException{
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            //maping data
            connectionFactory.setUsername("nhantc");
            connectionFactory.setPassword("nhantc");
            connectionFactory.setHost("192.168.100.14");
            connectionFactory.setPort(5672);

            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_HELLO, false, false, false, null);
            System.out.println("Waiting for message from " + QUEUE_HELLO);
            StringRpcServer server = new StringRpcServer(channel, QUEUE_HELLO) {
                @Override
                public String handleStringCall(String request) {
                    Gson gson = new Gson();
                    JsonObject objRequest = gson.fromJson(request, JsonObject.class);
                    String name = objRequest.get("name").getAsString();
                    int age = objRequest.get("age").getAsInt();
                    System.out.println("Got request " + request);
                    return "Hello, " + name + " age" + age + "!";
                }
            };

            server.mainloop();
        } catch (Exception ex) {
            System.out.println("Main thread caught exception: " + ex);
            ex.printStackTrace();
        }
    }
}
