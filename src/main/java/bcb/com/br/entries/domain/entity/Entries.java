package bcb.com.br.entries.domain.entity;


import bcb.com.br.entries.domain.enums.CurrentType;
import bcb.com.br.entries.domain.enums.EntryType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "entries")
@Data
public class Entries {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String clientId;
    private Double balanceRate;
    private CurrentType currentType;
    private EntryType entryType;
    private Double amount;
    private String channel;
    private String channelId;
    private Timestamp createdAt;

}
