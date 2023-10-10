package br.com.ctm.kafkaangular.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ctm.kafkaangular.Constants;
import br.com.ctm.kafkaangular.domain.Message;

@RestController
@RequestMapping(value = "/api/kafka")
public class MessageController {

    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private KafkaTemplate<String, Message> template;

    @PostMapping(path = "/send")
	public void send(@RequestBody String text) {
		String uuid = UUID.randomUUID().toString();
        this.template.send(Constants.KAFKA_TOPIC, uuid, 
            new Message(text, LocalDateTime.now())
        ).thenRun(() -> LOG.info("Message {} was sent.", uuid));
	}

}
