package com.fiveletters.brush.service.auction;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.fiveletters.brush.config.AwsDynamoDBMapper;
import com.fiveletters.brush.domain.auction.AuctionRepository;
import com.fiveletters.brush.domain.lot.LotChristies;
import com.fiveletters.brush.domain.lot.LotChristiesRepository;
import com.fiveletters.brush.domain.lot.LotSothebys;
import com.fiveletters.brush.domain.lot.LotSothebysRepository;
import com.fiveletters.brush.domain.lotDetail.LotDetail;
import com.fiveletters.brush.web.dto.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RequiredArgsConstructor
@Service
public class AuctionServiceImpl implements AuctionService {

    @NonNull
    private final AuctionRepository auctionRepository;

    @NonNull
    private final LotChristiesRepository lotChristiesRepository;

    @NonNull
    private final LotSothebysRepository lotSothebysRepository;

    @Value("${amazon.dynamodb.christies.tableName}")
    private String christiesTableName;

    @Value("${amazon.dynamodb.sothebys.tableName}")
    private String sothebysTableName;

    private AwsDynamoDBMapper awsDynamoDBMapper;

    private AmazonDynamoDB dynamoDB;

    static final String CHRISTIES = "christies";
    static final String SOTHEBYS = "sothebys";
    static final String LOT_TITLE  = "Lot Title";


    @Transactional
    public List<AuctionDto> getAuctionResultsList(PageDto pageDto, String search) throws Exception {
        return auctionRepository.getAuctionResultsList(pageDto.getRecordCountPerPage(), pageDto.getFirstRecordIndex(), search)
                .stream()
                .map(AuctionDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public int getTotalRecordCount(String search) throws Exception {

        int totalRecordCount = auctionRepository.getTotalRecordCount(search);
        return totalRecordCount;
    }

    @Transactional
    public void setCrawlStatus(AuctionDto auctionDto) throws Exception {

        if(auctionDto.getAuctionName().equals(CHRISTIES)) {
            auctionRepository.setCrawlStatusChrisites(auctionDto.getId(), auctionDto.getCrawlStatus(), auctionDto.getTransStatus());
        }
        else if(auctionDto.getAuctionName().equals(SOTHEBYS)) {
            auctionRepository.setCrawlStatusSothebys(auctionDto.getId(), auctionDto.getCrawlStatus(), auctionDto.getTransStatus());
        }
        else {
            log.info("해당 경매가 없습니다.");
        }
    }

    @Transactional
    public void setTransStatus(AuctionDto auctionDto) throws Exception {
        if(auctionDto.getAuctionName().equals(CHRISTIES)) {
            auctionRepository.setTransStatusChrisites(auctionDto.getId(), auctionDto.getTransStatus());
        }
        else if(auctionDto.getAuctionName().equals(SOTHEBYS)) {
            auctionRepository.setTransStatusSothebys(auctionDto.getId(), auctionDto.getTransStatus());
        }
        else {
            log.info("해당 경매가 없습니다.");
        }
    }

    @Transactional
    public void setAuctionTitle(AuctionDto auctionDto) throws Exception {
        if(auctionDto.getAuctionName().equals(CHRISTIES)) {
            auctionRepository.setAuctionTitleChrisites(auctionDto.getId(), auctionDto.getTitle());
        }
        else if(auctionDto.getAuctionName().equals(SOTHEBYS)) {
            auctionRepository.setAuctionTitleSothebys(auctionDto.getId(), auctionDto.getTitle());
        }
        else {
            log.info("해당 경매가 없습니다.");
        }
    }

    @Transactional
    public List<LotDto> getAuctionRotList(LotDto lotDto, PageDto pageDto) throws Exception {
        Pageable paging = PageRequest.of(pageDto.getCurrentPageNo()-1, pageDto.getRecordCountPerPage());
        List<LotDto> lotList;
        if(lotDto.getAuctionName().equals(CHRISTIES)) {
            if(lotDto.getSearchKey().equals(LOT_TITLE)) {
                lotList = lotChristiesRepository
                        .findAllByAuctionIdAndLotTitleContaining(lotDto.getAuctionId(), lotDto.getSearchValue(), paging)
                        .stream()
                        .map(LotDto::new)
                        .collect(Collectors.toList());
            }
            else {
                lotList = lotChristiesRepository
                        .findAllByAuctionIdAndLotArtistContaining(lotDto.getAuctionId(), lotDto.getSearchValue(), paging)
                        .stream()
                        .map(LotDto::new)
                        .collect(Collectors.toList());
            }
        }
        else if(lotDto.getAuctionName().equals(SOTHEBYS)) {
            if(lotDto.getSearchKey().equals(LOT_TITLE)) {
                lotList = lotSothebysRepository
                        .findAllByAuctionIdAndLotTitleContaining(lotDto.getAuctionId(), lotDto.getSearchValue(), paging)
                        .stream()
                        .map(LotDto::new)
                        .collect(Collectors.toList());
            }
            else {
                lotList = lotSothebysRepository
                        .findAllByAuctionIdAndLotArtistContaining(lotDto.getAuctionId(), lotDto.getSearchValue(), paging)
                        .stream()
                        .map(LotDto::new)
                        .collect(Collectors.toList());
            }
        }
        else {
            log.info("해당 경매가 없습니다.");
            lotList = null;
        }
        return lotList;
    }

    @Transactional
    public int getLotTotalRecordCount(LotDto lotDto) throws Exception {
        int totalRecordCount;
        if(lotDto.getAuctionName().equals(CHRISTIES)){
            if(lotDto.getSearchKey().equals(LOT_TITLE)){
                totalRecordCount = lotChristiesRepository.countByAuctionIdAndLotTitleContaining(lotDto.getAuctionId(), lotDto.getSearchValue());
            }
            else {
                totalRecordCount = lotChristiesRepository.countByAuctionIdAndLotArtistContaining(lotDto.getAuctionId(), lotDto.getSearchValue());
            }
        }
        else if(lotDto.getAuctionName().equals(SOTHEBYS)){
            if(lotDto.getSearchKey().equals(LOT_TITLE)){
                totalRecordCount = lotSothebysRepository.countByAuctionIdAndLotTitleContaining(lotDto.getAuctionId(), lotDto.getSearchValue());
            }
            else {
                totalRecordCount = lotSothebysRepository.countByAuctionIdAndLotArtistContaining(lotDto.getAuctionId(), lotDto.getSearchValue());
            }
        }
        else {
            log.info("해당 경매가 없습니다.");
            totalRecordCount = 0;
        }
        return totalRecordCount;
    }

    @Transactional
    public void setLotIntegStatus(LotDto lotDto) throws Exception {

        if(lotDto.getAuctionName().equals(CHRISTIES)) {
            LotChristies lotChristies = lotChristiesRepository.findById(lotDto.getLotId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 Lot이 없습니다. id="+lotDto.getLotId()));
            lotChristies.updateIntegStatus(lotDto.getIntegStatus());
        }
        else if(lotDto.getAuctionName().equals(SOTHEBYS)) {
            LotSothebys lotSothebys = lotSothebysRepository.findById(lotDto.getLotId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 Lot이 없습니다. id="+lotDto.getLotId()));

            lotSothebys.updateIntegStatus(lotDto.getIntegStatus());
        }
        else {
            log.info("해당 경매가 없습니다.");
        }
    }

    public LotDetail getLotDetail(String auctionName, String lotUUID) throws Exception {

        AwsDynamoDBMapper mapper = new AwsDynamoDBMapper();
        LotDetail lotDetail = new LotDetail();
        lotUUID= lotUUID.trim();

        if(auctionName.equals(CHRISTIES)) {
            if(mapper.findByUUID(christiesTableName, lotUUID) == true) { // id 체크
                lotDetail = mapper.getDynamoDBItemByLotUUID(christiesTableName, lotUUID);
            }
        }
        else if(auctionName.equals(SOTHEBYS)) {
            if(mapper.findByUUID(sothebysTableName, lotUUID) == true) { // id 체크
                lotDetail = mapper.getDynamoDBItemByLotUUID(sothebysTableName, lotUUID);
            }
        }
        else {
            log.info("해당 경매가 없습니다.");
        }

        return lotDetail;
    }

    public void setLotDetail(LotDetailDto lotDetailDto) throws Exception {

        HashMap<String, String> getItemMap = new HashMap<>();
        for(Field field : lotDetailDto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            getItemMap.put(field.getName(), (String)field.get(lotDetailDto));
        }

        awsDynamoDBMapper = new AwsDynamoDBMapper();
        dynamoDB = awsDynamoDBMapper.getDynamoDB();

        Map<String, AttributeValue> key = new HashMap<>();
        key.put("LotUUID", new AttributeValue().withS(lotDetailDto.getLotUUID()));

        // 수정 가능한 컬럼, 추가하면 수정 가능
        String[] column = {"LotTitle", "LotEstimatedLowPrice", "LotEstimatedHighPrice", "LotPriceRealised", "LotHammerPrice"
                , "LotProvenance", "LotLiterature", "LotExhibited", "LotDescription", "LotEssay"};

        if(lotDetailDto.getAuctionName().equals(CHRISTIES)){
            GetItemResult getItemResult = getItemDynamoDB(christiesTableName, key);

            for (int i=0; i<column.length; i++){
                String columnNme = column[i].substring(0,1).toLowerCase()+column[i].substring(1, column[i].length()); // 가져올 컬럼 이름
                Optional<Object> getItem = Optional.ofNullable(getItemResult.getItem().get(column[i])); // 수정 전 데이터
                String setItem = getItemMap.get(columnNme); // 수정 데이터

                if(!setItem.equals("")){ // DB 컬럼 Y & N / 수정 값 Y => 수정 기능만 수행
                    awsDynamoDBMapper.setLotDetailItemNotNull(dynamoDB, christiesTableName, key, column[i], setItem);
                }
                if(getItem.isPresent()){ // DB 컬럼 Y / 수정 값 Null => 컬럼 삭제 기능 수행
                    if(setItem.equals("")){
                        awsDynamoDBMapper.setLotDetailColumnNotNullItemNull(dynamoDB, christiesTableName, key, column[i]);
                    }
                }
            }
        }
        else if(lotDetailDto.getAuctionName().equals(SOTHEBYS)) {
            GetItemResult getItemResult = getItemDynamoDB(sothebysTableName, key);

            for (int i=0; i<column.length; i++){
                String columnNme = column[i].substring(0,1).toLowerCase()+column[i].substring(1, column[i].length()); // 가져올 컬럼 이름
                Optional<Object> getItem = Optional.ofNullable(getItemResult.getItem().get(column[i])); // 수정 전 데이터
                String setItem = getItemMap.get(columnNme); // 수정 데이터

                if(!setItem.equals("")){ // DB 컬럼 Y & N / 수정 값 Y => 수정 기능만 수행
                    awsDynamoDBMapper.setLotDetailItemNotNull(dynamoDB, sothebysTableName, key, column[i], setItem);
                }
                if(getItem.isPresent()){ // DB 컬럼 Y / 수정 값 Null => 컬럼 삭제 기능 수행
                    if(setItem.equals("")){
                        awsDynamoDBMapper.setLotDetailColumnNotNullItemNull(dynamoDB, sothebysTableName, key, column[i]);
                    }
                }
            }
        }
        else {
            log.info("해당 경매가 없습니다.");
        }
    }

    private GetItemResult getItemDynamoDB(String tableName, Map<String, AttributeValue> key) {

        // DynamoDB 데이터 조회
        GetItemRequest getItemRequest = (new GetItemRequest())
                .withTableName(tableName)
                .withKey(key);
        GetItemResult getItemResult = dynamoDB.getItem(getItemRequest);

        return getItemResult;
    }
}
