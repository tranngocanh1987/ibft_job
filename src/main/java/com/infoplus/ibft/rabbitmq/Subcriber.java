package com.infoplus.ibft.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Subcriber {
	@RabbitListener(queues="${ibft.rabbitmq.queue}")
    public void receivedMessage(String msg) {
        System.out.println("Received Message: " + msg);
    }
}
