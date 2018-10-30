package com.database.hw.repository.records;

import com.database.hw.entity.records.CallRecord;
import com.database.hw.entity.records.LocalSurfRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocalSurfRecordRepository extends JpaRepository<LocalSurfRecord, Long> {
    @Query(value = "select t from LocalSurfRecord t  where t.startTime>=:beginTime and t.startTime<:endTime and t.uid=:uid")
    public List<LocalSurfRecord> getLocalSurfRecord(@Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("uid")long uid);

    @Query(value = "select t.amount from LocalSurfRecord t where t.startTime>=:beginTime and t.startTime<:endTime and t.uid=:uid")
    public List<Double> getLocalSurfAmount(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("uid")long uid);

    @Query(value = "select t.cost from LocalSurfRecord t where t.startTime>=:beginTime and t.startTime<:endTime and t.uid=:uid")
    public List<Double> getTotalCost(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("uid")long uid);
}
