package com.lalit.customer;

import com.lalit.amqp.RabbitMQMessageProducer;


import com.lalit.clients.fraud.FraudCheckResponse;
import com.lalit.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.lalit.clients.notification.NotificationClient;
import com.lalit.clients.notification.NotificationRequest;
@AllArgsConstructor
@Service
public class CustomerService {
    private final  CustomerRepository customerRepository ;
    private final RestTemplate restTemplate ;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder().fname(customerRequest.fname()).lname(customerRequest.lname()).email(customerRequest.email()).build();
        customerRepository.saveAndFlush(customer);
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject
//               // ("http://localhost:8081/api/v1/fraud-check/{customerId}",FraudCheckResponse.class, customer.getId());
//        //After adding Eureka server .
//        ("http://FRAUD/api/v1/fraud-check/{customerId}",FraudCheckResponse.class, customer.getId());

        FraudCheckResponse fraudCheckResponse =   fraudClient.isFraudster(customer.getId());
        if (fraudCheckResponse.isFraudster())
        {
            throw new IllegalStateException("fraudster") ;
        }

//        notificationClient.sendNotification(
//                new NotificationRequest(
//                        customer.getId(),
//                        customer.getEmail(),
//                        String.format("Hi %s, welcome to...",
//                                customer.getFname())
//                )
//        );
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome",
                        customer.getFname())
        );
       rabbitMQMessageProducer.publish("internal.exchange",
               "internal.notification.routing-key",notificationRequest
               );
    }
}
