package bcb.com.br.entries.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MQConfig {

    @Value("${spring.rabbit.queue.status}")
    private String queueName;

    @Bean
    public Queue sendStatusQueue() {
        return new Queue(queueName, true);
    }

}
