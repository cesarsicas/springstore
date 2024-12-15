package br.com.cesarsicas.springstore.domain.order;

import br.com.cesarsicas.springstore.domain.order.dto.CustomerEmail;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendRabbitMQMessageService {


    @Autowired
    private RabbitTemplate rabbitTemplate;



    void sendCustomerEmail(CustomerEmail customerEmail) {
        String message = new Gson().toJson(customerEmail);
        rabbitTemplate.convertAndSend(
                "store-messages",
                "rk.orders",
                message);
    }
}
