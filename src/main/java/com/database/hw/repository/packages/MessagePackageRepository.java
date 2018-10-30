package com.database.hw.repository.packages;

import com.database.hw.entity.packages.CallPackage;
import com.database.hw.entity.packages.MessagePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface MessagePackageRepository extends JpaRepository<MessagePackage, Long> {
    @Query(value = "select t.maxNumber from MessagePackage t where t.pkgId=:pkgId")
    public double getPackageMaxNumberByPkgId(@Param("pkgId")String pkgId);

    public MessagePackage getMessagePackageByPkgId(@Param("pkgId")String pkgId);
}
