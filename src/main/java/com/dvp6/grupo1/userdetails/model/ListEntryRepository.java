package com.dvp6.grupo1.userdetails.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ListEntryRepository extends CrudRepository<ListEntry, String>{
    List<ListEntry> findTopByUseridAndEntrytypeOrderByDateadded(String userId, String EntryType);
    List<ListEntry> findTop5ByUseridAndEntrytypeOrderByDateadded(String userId, String EntryType);
    List<ListEntry> findTop10ByUseridAndEntrytypeOrderByDateadded(String userId, String EntryType);
    List<ListEntry> findTop20ByUseridAndEntrytypeOrderByDateadded(String userId, String EntryType);

    Optional<ListEntry> findByImdbidAndUseridAndEntrytype(String imdbId, String userId, String entryType);
}
