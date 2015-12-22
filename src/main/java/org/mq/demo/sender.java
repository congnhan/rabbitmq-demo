package org.mq.demo;


import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.RpcClient;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BinBo on 12/18/2015.
 */
public class sender implements queues, Runnable {
    public static void main(String[] args) {
        sender sender = new sender();
        Thread thread01 = new Thread(sender);
        thread01.start();
    }

    public void run() {
        while (true) {
            try {
                ConnectionFactory connectionFactory = new ConnectionFactory();
                //maping data
                connectionFactory.setUsername("nhantc");
                connectionFactory.setPassword("nhantc");
                connectionFactory.setHost("192.168.100.14");
                connectionFactory.setPort(5672);

                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel();
                RpcClient service = new RpcClient(channel, "", QUEUE_HELLO);

                Dog dog = new Dog();
                Gson gson = new Gson();
                String reqMessage = gson.toJson(dog);
                System.out.println(service.stringCall(reqMessage));
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
