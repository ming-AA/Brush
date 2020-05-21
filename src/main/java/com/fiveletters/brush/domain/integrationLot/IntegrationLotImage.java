package com.fiveletters.brush.domain.integrationLot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="PALETTE_LOT_IMAGES")
public class IntegrationLotImage {

    @EmbeddedId // 복합키(LOT_UUID, IMAGE_SEQ)
    private ImageId lotImageId;

    @Column(name = "IMAGE_CONVERTED_URL")
    private String lotImageUrl;

    @Column(name = "REP_IMAGE_URL")
    private String repImageUrl;

    @Column(name = "REP_YN")
    private String repYN; // 대표 이미지 설정 값

    public void updateCurrentRepImg(int lotImageId) {
        this.lotImageId.setLotImageId(lotImageId); // 순서 번호 1외 번호
        this.repYN = "N";
        this.repImageUrl ="";
    }

    public void updateChooseRepImg(String lotImageUrl) {
        this.lotImageId.setLotImageId(1); // 대표 이미지는 무조건 순서 번호 1
        this.repYN = "Y";
        this.repImageUrl = lotImageUrl;
    }
}
