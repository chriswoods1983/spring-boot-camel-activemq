package co.uk.popupengineering;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class SpringBootCamelActivemqApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootCamelActivemqApplication.class, args);
	}


	//not needed if using embedded
	public ActiveMQConnectionFactory ConnectionFactory(@Value("${broker.url}") String url) {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(url);
		return activeMQConnectionFactory;
	}

	//not needed yet
	public ActiveMQComponent activeMQComponent(JmsConfiguration jmsConfiguration){
		ActiveMQComponent activeMQComponent = new ActiveMQComponent();
		activeMQComponent.setConfiguration(jmsConfiguration);

		return activeMQComponent;
	}

	//not needed yet
	public JmsConfiguration jmsConfiguration(ConnectionFactory connectionFactory)
	{
		return new JmsConfiguration(connectionFactory);
	}


	@Component
	public class Route extends RouteBuilder {

		@Override
		public void configure() throws Exception {
			from("jms:test.input")
					.log("${body}")
					.to("jms:test.output");
		}
	}
}
