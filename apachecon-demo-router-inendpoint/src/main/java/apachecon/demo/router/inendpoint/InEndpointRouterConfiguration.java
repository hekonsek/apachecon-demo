package apachecon.demo.router.inendpoint;

import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static apachecon.demo.router.inendpoint.RandomUUIDExpression.randomUUID;

@SpringBootApplication
public class InEndpointRouterConfiguration extends FatJarRouter {

    @Override
    public void configure() throws Exception {
        from("netty-http:http://0.0.0.0:18080").
                setBody(randomUUID()).
                inOnly("jms:invoices");
    }

}
