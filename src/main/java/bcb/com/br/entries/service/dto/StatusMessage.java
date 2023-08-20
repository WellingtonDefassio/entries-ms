package bcb.com.br.entries.service.dto;

import bcb.com.br.entries.service.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusMessage {

    private Status status;
    private String id;

}
