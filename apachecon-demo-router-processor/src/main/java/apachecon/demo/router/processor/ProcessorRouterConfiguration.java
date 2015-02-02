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
package apachecon.demo.router.processor;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProcessorRouterConfiguration extends FatJarRouter {

    @Bean
    public RoutesBuilder nettyEndpoint() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("jms:invoices").
                        setBody().groovy("new apachecon.demo.router.processor.Invoice(request.body, System.currentTimeMillis())").
                        to("mongodb:mongo?database=test&collection=invoices&operation=insert");
            }
        };
    }

}
