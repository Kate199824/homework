package com.database.hw.repository.records;

import com.database.hw.entity.records.DomesticSurfRecord;
import com.database.hw.entity.records.LocalSurfRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DomesticSurfRecordRepository extends JpaRepository<DomesticSurfRecord, Long> {
    @Query(value = "select t from DomesticSurfRecord t  where t.startTime>=:beginTime and t.startTime<:endTime and t.uid=:uid")
    public List<DomesticSurfRecord> getDomesticSurfRecord(@Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("uid")long uid);

    @Query(value = "select t.amount from DomesticSurfRecord t where t.startTime>=:beginTime and t.startTime<:endTime and t.uid=:uid")
    public List<Double> getDomesticSurfAmount(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("uid")long uid);

    @Query(value = "select t.cost from DomesticSurfRecord t where t.startTime>=:beginTime and t.startTime<:endTime and t.uid=:uid")
    public List<Double> getTotalCost(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("uid")long uid);
}
