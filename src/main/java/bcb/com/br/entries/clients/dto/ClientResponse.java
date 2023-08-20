package bcb.com.br.entries.clients.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ClientResponse {
    private UUID id;

    private String name;

    private String email;

    private String telephone;

    private String cpf;

    private String cnpj;

    private String companyName;

    private BalanceResponse balance;

}
