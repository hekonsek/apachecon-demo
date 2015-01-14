package apachecon.demo.router.inendpoint;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import static apachecon.demo.router.inendpoint.RandomUUIDExpression.randomUUID;

@SpringBootApplication
@Component
public class InEndpointRouterConfiguration extends RouteBuilder {

    public static void main(String[] args) {
        new SpringApplication("apachecon.demo.router.inendpoint").run(args);
    }

    @Override
    public void configure() throws Exception {
        from("netty-http:http://0.0.0.0:18080").
                setBody(randomUUID()).
                inOnly("jms:invoices");
    }

}
