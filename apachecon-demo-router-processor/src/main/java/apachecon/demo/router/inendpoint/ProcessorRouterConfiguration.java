/**
 *  Copyright 2005-2014 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package apachecon.demo.router.inendpoint;

import com.mongodb.MongoClient;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.jms.ConnectionFactory;
import java.net.UnknownHostException;

@EnableAutoConfiguration
public class ProcessorRouterConfiguration {

    public static void main(String[] args) throws InterruptedException {
        new SpringApplication(ProcessorRouterConfiguration.class).run(args);
    }

    @Bean
    public RoutesBuilder nettyEndpoint() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("jms:invoices").
                        setBody().groovy("new apachecon.demo.router.inendpoint.Invoice(request.body, System.currentTimeMillis())").
                        to("mongodb:mongo?database=test&collection=invoices&operation=insert");
            }
        };
    }

    @Bean
    ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(new ActiveMQConnectionFactory("tcp://amqbroker:6162"));
    }

    @Bean
    MongoClient mongo() throws UnknownHostException {
        return new MongoClient("mongodb");
    }

}

class Invoice {

    private String invoiceId;

    private long netValue;

    Invoice() {
    }

    Invoice(String invoiceId, long netValue) {
        this.invoiceId = invoiceId;
        this.netValue = netValue;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public long getNetValue() {
        return netValue;
    }

    public void setNetValue(long netValue) {
        this.netValue = netValue;
    }

}