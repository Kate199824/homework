package com.database.hw.repository.packages;

import com.database.hw.entity.packages.LocalSurfPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface LocalSurfPackageRepository extends JpaRepository<LocalSurfPackage, Long> {
    @Query(value = "select t.maxAmount from LocalSurfPackage t where t.pkgId=:pkgId")
    public double getPackageMaxAmountByPkgId(@Param("pkgId")String pkgId);

    public LocalSurfPackage getLocalSurfPackageByPkgId(@Param("pkgId")String pkgId);
}
