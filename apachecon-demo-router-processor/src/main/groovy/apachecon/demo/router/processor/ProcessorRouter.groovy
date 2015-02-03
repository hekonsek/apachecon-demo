package apachecon.demo.router.processor

import org.apache.camel.spring.boot.FatJarRouter
import org.springframework.boot.autoconfigure.SpringBootApplication

import static java.lang.System.currentTimeMillis

@SpringBootApplication
class ProcessorRouter extends FatJarRouter {
    void configure() {
        from("jms:invoices").
                setBody().expression { new Invoice(it.in.body.toString(), currentTimeMillis()) }.
                to("mongodb:mongo?database=test&collection=invoices&operation=insert");
    }
}
