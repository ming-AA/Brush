package com.fiveletters.brush.domain.integrationLot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IntegrationLotImageRepository extends JpaRepository<IntegrationLotImage, ImageId> {

    int countByLotImageId_LotUUID(String lotUUID); // Lot에 해당하는 레코드 count 조회

    List<IntegrationLotImage> findAllByLotImageId_LotUUID(String lotUUID); // Lot에 해당하는 레코드 조회

    @Modifying
    @Query(value = "UPDATE PALETTE_LOT_IMAGES " +
            "SET IMAGE_SEQ=?3" +
            ", REP_YN='N'" +
            ", REP_IMAGE_URL='' " +
            "WHERE LOT_UUID=?1 AND IMAGE_SEQ=?2"
            , nativeQuery = true)
    int updateCurrentRepImg(String lotUUID, int lotImageSeq, int exchangeImageSeq);

    @Modifying
    @Query(value = "UPDATE PALETTE_LOT_IMAGES " +
            "SET IMAGE_SEQ=?3" +
            ", REP_YN='Y'" +
            ", REP_IMAGE_URL=?4 " +
            "WHERE LOT_UUID=?1 AND IMAGE_SEQ=?2"
            , nativeQuery = true)
    int updateChooseRepImg(String lotUUID, int lotImageSeq, int exchangeImageSeq, String lotImageUrl);




}
