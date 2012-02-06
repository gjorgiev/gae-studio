package com.arcbees.gaestudio.server.recorder;

import com.arcbees.gaestudio.server.dto.mapper.QueryMapper;
import com.arcbees.gaestudio.server.dto.mapper.QueryResultMapper;
import com.arcbees.gaestudio.shared.dto.DbOperationRecord;
import com.arcbees.gaestudio.shared.dto.DeleteRecord;
import com.arcbees.gaestudio.shared.dto.GetRecord;
import com.arcbees.gaestudio.shared.dto.PutRecord;
import com.arcbees.gaestudio.shared.dto.query.QueryRecord;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.apphosting.api.DatastorePb;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

// TODO externalize magic literals
public class MemcacheDbOperationRecorder implements DbOperationRecorder {
    
    private final Provider<MemcacheService> memcacheServiceProvider;
    
    private final Provider<Long> requestIdProvider;

    @Inject
    public MemcacheDbOperationRecorder(final Provider<MemcacheService> memcacheServiceProvider,
                                       final @Named("requestId") Provider<Long> requestIdProvider) {
        this.memcacheServiceProvider = memcacheServiceProvider;
        this.requestIdProvider = requestIdProvider;
    }

    @Override
    public void recordDbOperation(DatastorePb.DeleteRequest request, DatastorePb.DeleteResponse response,
                                  int executionTimeMs) {
        recordOperation(new DeleteRecord(
                //request, response,
                //Thread.currentThread().getStackTrace(),
                requestIdProvider.get(), generateId(), executionTimeMs));
    }

    @Override
    public void recordDbOperation(DatastorePb.GetRequest request, DatastorePb.GetResponse response,
                                  int executionTimeMs) {
        recordOperation(new GetRecord(
                //request, response,
                //Thread.currentThread().getStackTrace(),
                requestIdProvider.get(), generateId(), executionTimeMs));
    }

    @Override
    public void recordDbOperation(DatastorePb.PutRequest request, DatastorePb.PutResponse response,
                                  int executionTimeMs) {
        recordOperation(new PutRecord(
                //request, response,
                //Thread.currentThread().getStackTrace(),
                requestIdProvider.get(), generateId(), executionTimeMs));
    }

    @Override
    public void recordDbOperation(DatastorePb.Query query, DatastorePb.QueryResult queryResult, int executionTimeMs) {
        recordOperation(new QueryRecord(
                QueryMapper.mapDTO(query), QueryResultMapper.mapDTO(queryResult),
                //Thread.currentThread().getStackTrace(),
                requestIdProvider.get(), generateId(), executionTimeMs));
    }
    
    private void recordOperation(DbOperationRecord record) {
        memcacheServiceProvider.get().put("db.operation.record." + record.getStatementId(), record,
                Expiration.byDeltaSeconds(60));
    }

    private long generateId() {
        return memcacheServiceProvider.get().increment("db.operation.counter", 1L, 0L);
    }

}

