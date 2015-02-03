package apachecon.demo.router.inendpoint

import org.apache.camel.spring.boot.FatJarRouter
import org.springframework.boot.autoconfigure.SpringBootApplication

import static java.util.UUID.randomUUID

@SpringBootApplication
class InEndpointRouterConfiguration extends FatJarRouter {

    void configure() {
        from("netty-http:http://0.0.0.0:18080").
                setBody().expression { randomUUID() }.
                inOnly("jms:invoices");
    }

}