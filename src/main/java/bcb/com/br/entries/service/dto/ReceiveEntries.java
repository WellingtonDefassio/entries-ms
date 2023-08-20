package bcb.com.br.entries.service.dto;

import lombok.Data;

@Data
public class ReceiveEntries {
    private String cnpj;
    private Double value;
    private String channel;
    private String id;
}
