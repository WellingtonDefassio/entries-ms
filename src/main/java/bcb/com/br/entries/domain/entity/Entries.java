package bcb.com.br.entries.domain.entity;


import bcb.com.br.entries.domain.enums.CurrentType;
import bcb.com.br.entries.domain.enums.EntryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "entries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entries {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String clientId;
    private Double balanceRate;
    @Enumerated(EnumType.STRING)
    private CurrentType currentType;
    @Enumerated(EnumType.STRING)
    private EntryType entryType;
    private Double amount;
    private String channel;
    private String channelId;
    private Timestamp createdAt;

}
