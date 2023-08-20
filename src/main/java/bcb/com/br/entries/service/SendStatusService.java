package bcb.com.br.entries.service;

import bcb.com.br.entries.service.dto.StatusMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendStatusService {

    private final RabbitTemplate rabbitTemplate;
    @Value("${spring.rabbit.queue.status}")
    private String statusQueue;

    public void send(StatusMessage request) {
        String json = convertToJson(request);
        rabbitTemplate.convertAndSend(statusQueue, json);
    }
    @SneakyThrows
    private String convertToJson(StatusMessage request) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(request);
    }

}
