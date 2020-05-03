package com.ms.movie.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

import com.ms.movie.service.MovieService;


@Component
public class MessageListener {
	
	@Autowired
	MovieService service;
	
	@JmsListener(destination = "${jms.MovieTopic}", subscription = "MovieDataListener")
	public void receiveMessage(UpdateRatingMessage message) {
		
		service.updateRating(message.getMovieId(), message.getRating());
	}
	
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.BYTES);
		converter.setTypeIdPropertyName("_type");
		
		return converter;
	}
}
