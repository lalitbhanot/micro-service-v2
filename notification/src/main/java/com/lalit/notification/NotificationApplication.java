package com.lalit.notification;

import com.lalit.amqp.RabbitMQMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@SpringBootApplication
        (scanBasePackages =
                {"com.lalit.notification",
                        "com.lalit.amqp"}
        )
@Slf4j
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer rabbitMQMessageProducer,
//                                        NotificationConfig notificationConfig) {
//        return args -> {
//            rabbitMQMessageProducer.publish(notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKeys(),new Person("Ali", 18));
//        } ;
//    }
//    record Person(String name, int age){}
}