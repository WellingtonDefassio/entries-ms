package bcb.com.br.entries.clients;


import bcb.com.br.entries.clients.dto.ClientResponse;
import bcb.com.br.entries.service.dto.ReceiveEntries;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ClientsClient", url = "${clients.client-ms}")
public interface ClientsClient {

    @GetMapping("/v1/clients")
    ResponseEntity<ClientResponse> getClient(@RequestParam String cnpj);

    @PostMapping("/v1/clients/value")
    ResponseEntity<ClientResponse> addValue(@RequestBody ReceiveEntries receiveEntries);
    @PostMapping("/v1/clients/charge")
    ResponseEntity<ClientResponse> chargeValue(@RequestBody ReceiveEntries receiveEntries);

}
