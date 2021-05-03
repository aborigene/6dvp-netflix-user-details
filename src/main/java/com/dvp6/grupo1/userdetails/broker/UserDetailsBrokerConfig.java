/*
  Autor: Guilherme Rubio
  e-mail: guilherme.rubio@outlook.com.br
  Data: 14/04/2021
*/

package com.dvp6.grupo1.userdetails.broker;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
  Classe responsável pela configuração automática do RabbitMQ.
*/
@Configuration
public class UserDetailsBrokerConfig {

    public static final String QUEUE = "user-queue";
    public static final String EXCHANGE_NAME = "user-exchange";
    public static final String ROUTING_KEY = "user-routing";

    /*
      Método para criar o Exchange.
    */
    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME).durable(true).build();
    }

    /*
      Método para criar o Queue.
    */
    @Bean
    public Queue declareQueue() {
        return QueueBuilder.durable(QUEUE).build();
    }

    /*
      Método para criar o Bind entre o exchange e o queue.
    */
    @Bean
    public Binding declareBinding(Exchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
    }

}
