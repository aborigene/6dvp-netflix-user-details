package com.dvp6.grupo1.userdetails.broker;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class UserDetailsProducer {

    public static ConnectionFactory connectionFactory = new CachingConnectionFactory();
    public static AmqpTemplate template = new RabbitTemplate(connectionFactory);

    public static void publishLikeOrDislike(String imdbid, String likeOrDislike) {
        String json = "{\"imdbid\": \"" + imdbid + "\", \"" + likeOrDislike + "\": 1}";
        template.convertAndSend("product-details-exchange", "product-details-routing", json);
    }

    public static void publishOpenTicketSupport(String username, String subject, String description) {
        String json = "{ \"username\" : \"" + username + "\", \"subject\" : \"" + subject + "\", \"description\" : \""
                + description + "\", \"status\" : \"open\"}";
        template.convertAndSend("support-exchange", "support-routing", json);
    }

    public static void addUser(String username) {
        String json = "{ \"username\" : \"" + username + "\"}";
        template.convertAndSend("auth-exchange", "auth-routing", json);
    }
}