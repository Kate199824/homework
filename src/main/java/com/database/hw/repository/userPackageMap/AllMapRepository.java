package com.database.hw.repository.userPackageMap;

import com.database.hw.entity.userPackageMap.AllMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import javax.transaction.Transactional;
import java.util.List;

@Component
public interface AllMapRepository extends JpaRepository<AllMap, Long> {
    //筛选现在有效的某种套餐
    @Query(value = "select t from AllMap t where t.uid=:uid and t.pkgType=:pkgType and t.startDate<=:currentDate and t.endDate>:currentDate")
    public List<AllMap> findUserCurrentPackage(@Param("uid") long uid,
                                               @Param("currentDate") String currentDate,
                                               @Param("pkgType")int pkgType);

    //用户退订一个套餐
    @Transactional
    @Modifying
    @Query("update AllMap set endDate=:endDate where uid=:uid and pkgId=:pkgId and pkgType=:pkgType")
    public void removeUserPackage(@Param("uid")long uid,
                                  @Param("endDate")String endDate,
                                  @Param("pkgId")String pkgId,
                                  @Param("pkgType")int pkgType);

    @Query(value = "select t from AllMap t where t.uid=:uid and t.pkgId=:pkgId")
    public List<AllMap> findAllByUidAndAndPkgId(@Param("uid")long uid, @Param("pkgId")String pkgId);
}
