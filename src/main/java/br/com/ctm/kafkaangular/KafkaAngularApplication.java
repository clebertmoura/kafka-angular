package br.com.ctm.kafkaangular;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaAngularApplication.class, args);
	}

	@Bean
	public NewTopic messages() {
		return new NewTopic("messages", 1, (short) 1);
	}

}
