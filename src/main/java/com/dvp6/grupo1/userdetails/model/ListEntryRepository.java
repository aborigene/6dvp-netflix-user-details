package com.dvp6.grupo1.userdetails.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ListEntryRepository extends CrudRepository<ListEntry, String>{
    List<ListEntry> findTopByUserIdAndEntryTypeOrderByDateAdded(String userId, String EntryType);
    List<ListEntry> findTop5ByUserIdAndEntryTypeOrderByDateAdded(String userId, String EntryType);
    List<ListEntry> findTop10ByUserIdAndEntryTypeOrderByDateAdded(String userId, String EntryType);
    List<ListEntry> findTop20ByUserIdAndEntryTypeOrderByDateAdded(String userId, String EntryType);

    Optional<ListEntry> findByImdbIddAndUseridAndEntrytype(String imdbId, String userId, String entryType);
}
