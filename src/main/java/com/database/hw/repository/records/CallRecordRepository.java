package com.database.hw.repository.records;

import com.database.hw.entity.records.CallRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CallRecordRepository extends JpaRepository<CallRecord, Long> {
    @Query(value = "select t from CallRecord t  where t.startTime>=:beginTime and t.startTime<:endTime and t.uid=:uid")
    public List<CallRecord> getCallRecord(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("uid")long uid);

    @Query(value = "select t.duration from CallRecord t where t.startTime>=:beginTime and t.startTime<:endTime and t.uid=:uid")
    public List<Double> getCallTime(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("uid")long uid);

    @Query(value = "select t.cost from CallRecord t where t.startTime>=:beginTime and t.startTime<:endTime and t.uid=:uid")
    public List<Double> getTotalCost(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("uid")long uid);
}
