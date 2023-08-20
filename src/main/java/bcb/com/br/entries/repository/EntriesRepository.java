package bcb.com.br.entries.repository;

import bcb.com.br.entries.domain.entity.Entries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntriesRepository extends JpaRepository<Entries, UUID> {
}
