package bcb.com.br.entries.service;

import bcb.com.br.entries.clients.ClientsClient;
import bcb.com.br.entries.clients.dto.ClientResponse;
import bcb.com.br.entries.service.dto.ReceiveEntries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientsClient client;

    public ClientResponse getClientInfo(String cnpj) {
        //todo realizar tratamento apropriado para quando o cliente não for encontrado. atualmente 500.
        return client.getClient(cnpj).getBody();
    }

    public ClientResponse addValue(ReceiveEntries receiveEntries) {
        return client.addValue(receiveEntries).getBody();
    }

    public ClientResponse chargeMessage(ReceiveEntries receiveEntries) {
        return client.chargeValue(receiveEntries).getBody();
    }
}
