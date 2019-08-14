package com.infoplus.ibft.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${ibft.rabbitmq.exchange}")
	private String exchange;

	@Value("${ibft.rabbitmq.routingkey}")
	private String routingKey;

	public void produceMsg(String msg) {
		rabbitTemplate.convertAndSend(exchange, routingKey, msg);
		System.out.println("Send msg = " + msg);
	}
}
