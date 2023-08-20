package bcb.com.br.entries.service;

import bcb.com.br.entries.clients.dto.ClientResponse;
import bcb.com.br.entries.domain.entity.Entries;
import bcb.com.br.entries.domain.enums.EntryType;
import bcb.com.br.entries.repository.EntriesRepository;
import bcb.com.br.entries.service.dto.ReceiveEntries;
import bcb.com.br.entries.service.dto.StatusMessage;
import bcb.com.br.entries.service.enums.Channel;
import bcb.com.br.entries.service.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final ClientService clientService;
    private final EntriesRepository entriesRepository;
    private final SendStatusService sendStatusService;

    public void processMessageDebit(ReceiveEntries receiveEntries) {
        try {
            ClientResponse clientResponse = clientService.chargeMessage(receiveEntries);
            Entries entries = new Entries(null, clientResponse.getCnpj(), clientResponse.getBalance().getCurrentRate(),
                    clientResponse.getBalance().getCurrentType(), EntryType.CREDIT, receiveEntries.getValue(),
                    Channel.FINANCIAL.toString(), receiveEntries.getId(), Timestamp.from(Instant.now())
            );
            //TODO api para disparar a mensagem.
            entriesRepository.save(entries);
            sendStatusService.send(StatusMessage.builder().status(Status.SUCCESS).id(receiveEntries.getId()).build());
        } catch (Exception e) {
            //TODO criar endpoint de rollback caso o disparo da mensagem falhe.
            sendStatusService.send(StatusMessage.builder().status(Status.FAIL).id(receiveEntries.getId()).build());
        }
    }
}
