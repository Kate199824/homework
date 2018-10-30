package com.database.hw.repository.records;

import com.database.hw.entity.records.CallRecord;
import com.database.hw.entity.records.MessageRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRecordRepository extends JpaRepository<MessageRecord, Long> {
    @Query(value = "select t from MessageRecord t  where t.startTime>=:beginTime and t.startTime<:endTime and t.uid=:uid")
    public List<MessageRecord> getMessageRecord(@Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("uid")long uid);

    @Query(value = "select t.cost from MessageRecord t where t.startTime>=:beginTime and t.startTime<:endTime and t.uid=:uid")
    public List<Double> getTotalCost(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("uid")long uid);
}
