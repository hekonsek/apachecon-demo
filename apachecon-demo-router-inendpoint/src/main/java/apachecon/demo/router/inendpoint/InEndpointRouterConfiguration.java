package apachecon.demo.router.inendpoint;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.jms.ConnectionFactory;

import static apachecon.demo.router.inendpoint.RandomUUIDExpression.randomUUID;

@EnableAutoConfiguration
public class InEndpointRouterConfiguration {

    public static void main(String[] args) throws InterruptedException {
        new SpringApplication(InEndpointRouterConfiguration.class).run(args);
    }

    @Bean
    RoutesBuilder nettyEndpoint() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("netty-http:http://0.0.0.0:18080").
                        setBody(randomUUID()).
                        inOnly("jms:invoices");
            }
        };
    }

    @Bean
    ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(new ActiveMQConnectionFactory("tcp://amqbroker:6162"));
    }

}

