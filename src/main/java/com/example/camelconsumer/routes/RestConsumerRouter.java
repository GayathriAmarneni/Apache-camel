package com.example.camelconsumer.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import com.example.camelconsumer.bean.Car;

// "timer://test-rest-api?period=1000000"
@Component
public class RestConsumerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        try (CamelContext context = new DefaultCamelContext();) {
            ConsumerTemplate template = context.createConsumerTemplate();
            context.start();
            String car = template.receiveBody(
                    "http://localhost:9090/api/car", String.class);
            System.out.println(car);

            context.stop();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

        Car car = new Car();
        car.setCarName("Benz Maybach");
        car.setCarModel("s-class");
        car.setCompanyName("Mercedes");
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

        rest("/car")
                .get()
                .to("direct:car");
        from("direct:car").transform().constant(car);
    }
}
