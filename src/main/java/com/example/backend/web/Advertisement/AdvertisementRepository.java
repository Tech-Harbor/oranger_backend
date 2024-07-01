package com.example.backend.web.Advertisement;

import com.example.backend.web.Advertisement.store.AdvertisementEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, Long> {
    AdvertisementEntity getByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE AdvertisementEntity ad SET ad.active = false WHERE ad.createDate <= :dateTime AND ad.active = true")
    void updateActiveAdvertisements(@Param("dateTime") LocalDateTime dateTime);

    @Transactional
    @Modifying
    @Query("DELETE FROM AdvertisementEntity ad WHERE ad.createDate <= :dateTime AND ad.active = false")
    void deleteActiveAdvertisements(@Param("dateTime") LocalDateTime dateTime);
}