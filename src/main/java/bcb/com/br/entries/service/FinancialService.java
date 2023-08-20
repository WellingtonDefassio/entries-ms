package bcb.com.br.entries.service;

import bcb.com.br.entries.clients.dto.ClientResponse;
import bcb.com.br.entries.domain.entity.Entries;
import bcb.com.br.entries.domain.enums.CurrentType;
import bcb.com.br.entries.domain.enums.EntryType;
import bcb.com.br.entries.repository.EntriesRepository;
import bcb.com.br.entries.service.dto.ReceiveEntries;
import bcb.com.br.entries.service.enums.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class FinancialService {
    private final ClientService clientService;
    private final EntriesRepository entriesRepository;

    public void processFinancialCredit(ReceiveEntries receiveEntries) {
        try {
            ClientResponse clientResponse = clientService.addValue(receiveEntries);
            Entries entries = new Entries(null, clientResponse.getCnpj(), clientResponse.getBalance().getCurrentRate(),
                    clientResponse.getBalance().getCurrentType(), EntryType.CREDIT, receiveEntries.getValue(),
                    Channel.FINANCIAL.toString(), receiveEntries.getId(), Timestamp.from(Instant.now())
            );
            entriesRepository.save(entries);
        } catch (Exception e) {
            log.info("error processing financial value");
        }
    }

}
