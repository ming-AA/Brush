package com.fiveletters.brush.web.dto;

import com.fiveletters.brush.domain.integrationLot.IntegrationLotImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class IntegrationLotImageDto {

    private Long id; //SEQ
    private int lotImageId; // IMAGE_SEQ
    private String lotUUID; // Lot 고유값
    private String lotImageUrl; // DETAIL_VIEW_IMAGE_URL
    private String repYN; // 대표 이미지 설정 값
    private int chooseRepImgId; // 선택된 대표 이미지 IMAGE_SEQ
    private String chooseImgUrl; // 선택된 대표 이미지 Url


    public IntegrationLotImageDto(IntegrationLotImage entity) {
        this.lotImageId = entity.getLotImageId().getLotImageId();
        this.lotImageUrl = entity.getLotImageUrl();
        this.repYN = entity.getRepYN();
    }
}
