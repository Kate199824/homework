package com.database.hw.repository.packages;

import com.database.hw.entity.packages.CallPackage;
import com.database.hw.entity.packages.DomesticSurfPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface DomesticSurfPackageRepository extends JpaRepository<DomesticSurfPackage, Long> {
    @Query(value = "select t.maxAmount from DomesticSurfPackage t where t.pkgId=:pkgId")
    public double getPackageMaxAmountByPkgId(@Param("pkgId")String pkgId);

    public DomesticSurfPackage getDomesticSurfPackageByPkgId(@Param("pkgId")String pkgId);
}
