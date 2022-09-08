package com.example.camelconsumer.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import com.example.camelconsumer.bean.Car;

@Component
public class RestConsumerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer://test-rest-api?period=1000000")
                .log("Rest API calling...")
                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .to("http://localhost:9090/api/car?outType= com.example.camelconsumer.bean.Car")
                .unmarshal(new JacksonDataFormat(Car.class))
                .process(new CamelProcessor());

        restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

        rest("/car")
                .get()
                .to("direct:car");
        Car car = new Car();
        car.setCarName("Benz Maybach");
        car.setCarModel("s-class");
        car.setCompanyName("Mercedes");
        from("direct:car").transform().constant(car);
    }
}
