package by.krupenko.ws.emailnotification.handler;

import by.krupenko.ws.core.ProductCreatedEvent;
import by.krupenko.ws.emailnotification.exception.NotRetryableException;
import by.krupenko.ws.emailnotification.exception.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import static by.krupenko.ws.core.shared.Constants.PRODUCT_CREATED_EVENTS_TOPIC;

@Log4j2
@Component
@KafkaListener(topics = PRODUCT_CREATED_EVENTS_TOPIC)
public class ProductCreatedEventHandler {

    @KafkaHandler
    public void handle(ProductCreatedEvent productCreatedEvent) {
        log.info("Received event: {}", productCreatedEvent.getTitle());

        try {
            // e.g. send REST request
        } catch (ResourceAccessException e) {
            log.error(e);
            throw new RetryableException(e);
        } catch (HttpServerErrorException e) {
            // specific logic for this error
            log.error(e);
            throw new NotRetryableException(e);
        } catch (Exception e) {
            log.error(e);
            throw new NotRetryableException(e);
        }

    }

}
