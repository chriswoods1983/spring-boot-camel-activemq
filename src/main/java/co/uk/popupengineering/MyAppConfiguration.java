package co.uk.popupengineering;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;


@Configuration
public class MyAppConfiguration {

   // @Autowired
    //ActiveMQComponent activeMQComponent;

    @Bean
    CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {

            @Override
            public void beforeApplicationStart(CamelContext context) {
                // your custom configuration goes here
                //not needed 
                //context.addComponent("jms", activeMQComponent);
            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {

            }
        };
    }

}
