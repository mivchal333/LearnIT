package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pb.edu.pl.krysiukm.learnit.entity.HistoryEntry;

@Repository
public interface HistoryEntryRepository extends CrudRepository<HistoryEntry, Long> {
}
