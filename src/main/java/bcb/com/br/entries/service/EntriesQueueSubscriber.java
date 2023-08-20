package bcb.com.br.entries.service;

import bcb.com.br.entries.service.dto.ReceiveEntries;
import bcb.com.br.entries.service.enums.Channel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EntriesQueueSubscriber {


    private final FinancialService financialService;
    private final MessageService messageService;

    //todo try catch de rollback
    @RabbitListener(id = "entries", queues = "${spring.rabbit.queue.entries}")
    public void processEntriesQueue(@Payload String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ReceiveEntries receiveEntries = mapper.readValue(payload, ReceiveEntries.class);
        if (receiveEntries.getChannel().equalsIgnoreCase(Channel.MESSAGE.toString())) {
           messageService.processMessageDebit(receiveEntries);
        }
        if (receiveEntries.getChannel().equalsIgnoreCase(Channel.FINANCIAL.toString())) {
            financialService.processFinancialCredit(receiveEntries);
        }
    }


}
