package by.krupenko.ws.productmicroservice.service;

import by.krupenko.ws.core.ProductCreatedEvent;
import by.krupenko.ws.productmicroservice.service.dto.CreateProductDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static by.krupenko.ws.core.shared.Constants.PRODUCT_CREATED_EVENTS_TOPIC;

@Log4j2
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    @Override
    public String createProduct(CreateProductDto createProductDto) throws ExecutionException, InterruptedException {
        //todo save to db
        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, createProductDto.getTitle(),
                createProductDto.getPrice(), createProductDto.getQuantity());

//        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate
//                .send(PRODUCT_CREATED_EVENTS_TOPIC, productId, productCreatedEvent);
//        future.whenComplete((result, exception) -> {
//            if (exception != null) {
//                log.error("Failed to send message: {}", exception.getMessage());
//            } else {
//                log.info("Message sent successfully: {}", result.getRecordMetadata());
//            }
//        });
        SendResult<String, ProductCreatedEvent> result = kafkaTemplate
                .send(PRODUCT_CREATED_EVENTS_TOPIC, productId, productCreatedEvent).get();

        return productId;
    }

}
