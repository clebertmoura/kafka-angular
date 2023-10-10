package br.com.ctm.kafkaangular.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import br.com.ctm.kafkaangular.Constants;
import br.com.ctm.kafkaangular.domain.Message;

@Component
@KafkaListener(topics = Constants.KAFKA_TOPIC)
public class MessageConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired
	private SimpMessagingTemplate webSocket;

    @KafkaHandler
	public void processMessage(Message message) {
        LOG.info("Message received at {}, with content: {}", 
            message.sentAt(), message.text());
            
        this.webSocket.convertAndSend(Constants.WEBSOCKET_DESTINATION, message);
	}
    
}
