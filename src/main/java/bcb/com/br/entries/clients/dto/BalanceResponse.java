package bcb.com.br.entries.clients.dto;

import bcb.com.br.entries.domain.enums.CurrentType;
import lombok.Data;

@Data
public class BalanceResponse {

    private Double value;
    private Double currentExpense;
    private Double currentRate;
    private CurrentType currentType;

}
