package soapuserservice.endpoint;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;

import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soapuserservice.service.UserWebService;

@Configuration
public class USConfig {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint userServiceEndpoint(UserWebService userWebService) {
        EndpointImpl endpoint = new EndpointImpl(bus, userWebService);
        endpoint.publish("/UserService");

        return endpoint;
    }
}
