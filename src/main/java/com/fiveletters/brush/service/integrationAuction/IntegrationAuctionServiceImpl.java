package com.fiveletters.brush.service.integrationAuction;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.fiveletters.brush.config.AwsDynamoDBMapper;
import com.fiveletters.brush.domain.integrationAuction.IntegrationAuction;
import com.fiveletters.brush.domain.integrationAuction.IntegrationAuctionRepository;
import com.fiveletters.brush.domain.integrationLot.*;
import com.fiveletters.brush.domain.lotDetail.LotDetail;
import com.fiveletters.brush.web.dto.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class IntegrationAuctionServiceImpl implements IntegrationAuctionService {

    @NonNull
    private final IntegrationAuctionRepository integrationAuctionRepository;

    @NonNull
    private final IntegrationLotRepository integrationLotRepository;

    @NonNull
    private final IntegrationLotImageRepository integrationLotImageRepository;

    @Value("${amazon.dynamodb.palette.tableName}")
    private String paletteTableName;

    private AwsDynamoDBMapper awsDynamoDBMapper;

    private AmazonDynamoDB dynamoDB;

    static final String LOT_TITLE  = "Lot Title";


    @Transactional
    public List<IntegrationAuctionDto> getAuctionResultsList(PageDto pageDto, String search) throws Exception {
        Pageable paging = PageRequest.of(pageDto.getCurrentPageNo()-1, pageDto.getRecordCountPerPage());

        List<IntegrationAuctionDto> auctionResultsList = integrationAuctionRepository
                .findAllByAuctionTitleContainingIgnoreCase(search, paging)
                .stream()
                .map(IntegrationAuctionDto::new)
                .collect(Collectors.toList());

        return auctionResultsList;
    }

    @Transactional
    public int getTotalRecordCount(String search) throws Exception {

        int totalRecordCount = integrationAuctionRepository.countByAuctionTitleContainingIgnoreCase(search);
        return totalRecordCount;
    }

    @Transactional
    public Long setAuctionTitle(IntegrationAuctionDto integrationAuctionDto) throws Exception {

        IntegrationAuction integrationAuction = integrationAuctionRepository.findById(integrationAuctionDto.getId())
                .orElseThrow(()-> new IllegalArgumentException("해당 경매가 없습니다. id="+ integrationAuctionDto.getId()));
        integrationAuction.update(integrationAuctionDto.getAuctionTitle());

        return integrationAuctionDto.getId();
    }

    @Transactional
    public List<IntegrationLotDto> getAuctionRotList(IntegrationLotDto integrationLotDto, PageDto pageDto) throws Exception {
        Pageable paging = PageRequest.of(pageDto.getCurrentPageNo()-1, pageDto.getRecordCountPerPage());
        List<IntegrationLotDto>  lotList;
        if(integrationLotDto.getSearchKey().equals(LOT_TITLE)) {
            lotList = integrationLotRepository
                    .findAllByAuctionIdAndLotTitleContainingIgnoreCase(integrationLotDto.getAuctionId(), integrationLotDto.getSearchValue(), paging)
                    .stream()
                    .map(IntegrationLotDto::new)
                    .collect(Collectors.toList());

        }
        else {
           lotList = integrationLotRepository
                    .findAllByAuctionIdAndLotArtistNameContainingIgnoreCase(integrationLotDto.getAuctionId(), integrationLotDto.getSearchValue(), paging)
                    .stream()
                    .map(IntegrationLotDto::new)
                    .collect(Collectors.toList());
        }

        return lotList;
    }

    @Transactional
    public int getLotTotalRecordCount(IntegrationLotDto integrationLotDto) throws Exception {
        int totalRecordCount = 0;
        if(integrationLotDto.getSearchKey().equals(LOT_TITLE)) {
            totalRecordCount = integrationLotRepository
                    .countByAuctionIdAndLotTitleContainingIgnoreCase(integrationLotDto.getAuctionId(), integrationLotDto.getSearchValue());
        }
        else {
            totalRecordCount = integrationLotRepository
                    .countByAuctionIdAndLotArtistNameContainingIgnoreCase(integrationLotDto.getAuctionId(), integrationLotDto.getSearchValue());
        }

        return totalRecordCount;
    }

    @Transactional
    public void setLotUseYN(Long lotId, String useYN) throws Exception {
        IntegrationLot integrationLot = integrationLotRepository.findById(lotId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Lot이 없습니다. id="+lotId));

        integrationLot.updateUseYN(useYN);
    }

    @Transactional
    public LotDetail getLotDetail(String lotUUID) throws Exception {

        LotDetail lotDetail = new LotDetail();
        AwsDynamoDBMapper mapper = new AwsDynamoDBMapper();

        if(mapper.findByUUID(paletteTableName, lotUUID) == true) { // id 체크
            lotDetail = mapper.getDynamoDBItemByLotUUID(paletteTableName, lotUUID);
        }

        return lotDetail;
    }

    @Transactional
    public void setLotDetail(IntegrationLotDetailDto integrationLotDetailDto) throws Exception {
        awsDynamoDBMapper = new AwsDynamoDBMapper();
        dynamoDB = awsDynamoDBMapper.getDynamoDB();
        Map<String, AttributeValue> key = new HashMap<>();

        // DynamoDB 데이터 조회
        key.put("LotUUID", new AttributeValue().withS(integrationLotDetailDto.getLotUUID()));

        GetItemRequest getItemRequest = (new GetItemRequest())
                .withTableName(paletteTableName)
                .withKey(key);
        GetItemResult getItemResult = dynamoDB.getItem(getItemRequest);

        HashMap<String, String> getItemMap = new HashMap<>();
        for(Field field : integrationLotDetailDto.getClass().getDeclaredFields()){
            field.setAccessible(true);
            getItemMap.put(field.getName(), (String)field.get(integrationLotDetailDto));
        }

        // 수정 가능한 컬럼, 추가하면 수정 가능
        String[] column = {"LotTitle", "LotEstimatedLowPrice", "LotEstimatedHighPrice", "LotPriceRealised", "LotHammerPrice"
                , "LotProvenance", "LotLiterature", "LotExhibited", "LotDescription", "LotEssay"};

        for (int i=0; i<column.length; i++){
            String columnNme = column[i].substring(0,1).toLowerCase()+column[i].substring(1, column[i].length()); // 가져올 컬럼 이름
            Optional<Object> getItem = Optional.ofNullable(getItemResult.getItem().get(column[i])); // 수정 전 데이터
            String setItem = getItemMap.get(columnNme); // 수정 데이터

            if(!setItem.equals("")){ // DB 컬럼 Y & N / 수정 값 Y => 수정 기능만 수행
                awsDynamoDBMapper.setLotDetailItemNotNull(dynamoDB, paletteTableName, key, column[i], setItem);
            }
            if(getItem.isPresent()){ // DB 컬럼 Y / 수정 값 Null => 컬럼 삭제 기능 수행
                if(setItem.equals("")){
                    awsDynamoDBMapper.setLotDetailColumnNotNullItemNull(dynamoDB, paletteTableName, key, column[i]);
                }
            }
        }
    }

    @Transactional
    public int getLotImageTotalRecordCount(String lotUUID) throws Exception {

        int totalRecordCount = integrationLotImageRepository.countByLotImageId_LotUUID(lotUUID);

        return totalRecordCount;
    }

    @Transactional
    public List<IntegrationLotImageDto> getAuctionLotImage(String lotUUID) throws Exception {

        List<IntegrationLotImageDto>  lotImageList = integrationLotImageRepository
                .findAllByLotImageId_LotUUID(lotUUID)
                .stream()
                .map(IntegrationLotImageDto::new)
                .collect(Collectors.toList());

        return lotImageList;
    }

    @Transactional
    public void setLotRepImg(IntegrationLotImageDto integrationLotImageDto) throws Exception {

        // 현재 대표이미지 <-> 선택된 대표이미지 IMAGE_SEQ(순서번호), REP_YN, REP_URL 수정 작업
        String LotUUID = integrationLotImageDto.getLotUUID(); // Lot 고유 Id
        int chooseRepImgId = integrationLotImageDto.getChooseRepImgId(); // 선택된 대표 이미지의 순서 번호
        String chooseImgUrl = integrationLotImageDto.getChooseImgUrl(); // 선택된 대표 이미지 Url

        // 1) 현재 대표 이미지 데이터 수정 전 작업, IMAGE_SEQ="100" pk 충돌 방지 임시 숫자(100) 업데이트
        integrationLotImageRepository.updateCurrentRepImg(LotUUID, 1, 100);

        // 2) 선택된 이미지 대표 이미지로 수정. IMAGE_SEQ="1", REP_YN="Y", REP_URL="..."
        String[] splitImgUrl = chooseImgUrl.split(".com/");  // IMAGE_CONVERTED_URL -> REP_IMAGE_URL로 변환(S3 representative/r_)
        String repImgUrl = splitImgUrl[0]+".com/representative/r_"+splitImgUrl[1];
        integrationLotImageRepository.updateChooseRepImg(LotUUID, chooseRepImgId, 1, repImgUrl);

        // 3) 현재 대표 이미지 데이터 수정 IMAGE_SEQ="1외 번호", REP_YN="N", REP_URL=""
        integrationLotImageRepository.updateCurrentRepImg(LotUUID, 100, chooseRepImgId);
    }

}
