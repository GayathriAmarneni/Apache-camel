package com.example.camelconsumer.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.camel.Exchange;
import com.example.camelconsumer.bean.Car;
import org.apache.camel.Processor;

public class CamelProcessor implements Processor {
    private static final Logger logger = LoggerFactory.getLogger(RestConsumerRouter.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Car car = exchange.getIn().getBody(Car.class);
        logger.info(car.toString());
        car.setCarModel("16series");
        exchange.getIn().setBody(car);
    }
}
