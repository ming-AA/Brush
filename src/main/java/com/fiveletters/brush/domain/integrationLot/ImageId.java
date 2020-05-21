package com.fiveletters.brush.domain.integrationLot;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@NoArgsConstructor
@Data
@Embeddable
public class ImageId implements Serializable {

    @Column(name = "LOT_UUID")
    private String lotUUID;

    @Column(name = "IMAGE_SEQ")
    private int lotImageId;

    public ImageId(String lotUUID, int lotImageId) {
        this.lotUUID = lotUUID;
        this.lotImageId = lotImageId;
    }
}
