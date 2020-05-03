package com.ms.movie.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

import com.ms.movie.entity.Rating;


@Component
public class MessageProducer {
	
	@Autowired
	JmsTemplate template;
	
	@Value("${jms.MovieTopic}")
	private String movieTopic;
	
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
	
	public void sendUpdate(String movieId, Rating rating) {
		
		UpdateRatingMessage msg = new UpdateRatingMessage(movieId, rating);
		template.convertAndSend(movieTopic, msg);
	}
}