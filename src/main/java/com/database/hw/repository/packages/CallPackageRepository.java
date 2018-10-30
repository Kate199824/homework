package com.database.hw.repository.packages;

import com.database.hw.entity.packages.CallPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface CallPackageRepository extends JpaRepository<CallPackage, Long> {
    @Query(value = "select t.maxTime from CallPackage t where t.pkgId=:pkgId")
    public double getPackageMaxTimeByPkgId(@Param("pkgId")String pkgId);

    public CallPackage getCallPackageByPkgId(@Param("pkgId")String pkgId);
}
