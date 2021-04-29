package com.dvp6.grupo1.userdetails.broker;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserDetailsProducer {

    @Autowired
    private RabbitTemplate template;

    public String publishLikeOrDislike(String imdbid, String likeOrDislike) {
        String json = "{ \"imdbid\" : \"" + imdbid + "\", \"" + likeOrDislike + "\" : 1 }";
        template.convertAndSend("product-details-exchange", "product-details-routing", json);
        return "Message Published";
    }

    public String publishOpenTicketSupport(String subject, String description) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();;
        String json = "{ \"username\" : \"" + username + "\", \"subject\" : \"" + subject + "\", \"description\" : \""
                + description + "\", \"status\" : \"open\"}";
        template.convertAndSend("support-exchange", "support-routing", json);
        return "Open Ticket";
    }
}