package com.fiveletters.brush.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiveletters.brush.domain.lotDetail.LotDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class AwsDynamoDBMapper {
    private AmazonDynamoDB amazonDynamoDB;
    private DynamoDBMapper dynamoDBMapper;

    @Value("${amazon.aws.accesskey}")
    private String accesskey;

    @Value("${amazon.aws.secretkey}")
    private String secretkey;

    @Value("${amazon.aws.region}")
    private String region;

    private static AWSCredentials awsCredentials;

    private static Regions regions;

    private static final String DYNAMO_DB_KEY_NAME = "LotUUID";

    @PostConstruct
    void init(){ //DB Client 객체 생성
        awsCredentials = new BasicAWSCredentials(accesskey, secretkey);
        regions = Regions.fromName(region);
    }

    public DynamoDBMapper setup() {

        return new DynamoDBMapper(getDynamoDB());
    }

    public AmazonDynamoDB getDynamoDB() {

        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(regions)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    public DynamoDBMapperConfig getDynamoDBMapperConfig(String tableName) {

        return new DynamoDBMapperConfig(new DynamoDBMapperConfig.TableNameOverride(tableName));
    }

    public boolean findByUUID(String tableName, String lotUUID) throws Exception {

        Map<String, AttributeValue> key = new HashMap<>();
        key.put(DYNAMO_DB_KEY_NAME, (new AttributeValue()).withS(lotUUID));

        GetItemRequest getItemRequest = (new GetItemRequest())
                .withTableName(tableName)
                .withKey(key);

        Map<String,AttributeValue> returnedItem = getDynamoDB().getItem(getItemRequest).getItem();

        if (returnedItem != null) {
            return true;
        }
        return false;
    }

    public LotDetail getDynamoDBItemByLotUUID(String tableName, String lotUUID) throws Exception {

        // dataType List 제외 DynamoDB 데이터 가져오기
        DynamoDBMapper dynamoDBMapper = setup();
        LotDetail lotDetail = dynamoDBMapper.load(LotDetail.class, lotUUID, getDynamoDBMapperConfig(tableName)); // DynamoDBTable 테이블 이름 지정

        // dataType List 형태 DynamoDB 데이터 가져오기
        DynamoDB dynamoDB = new DynamoDB(getDynamoDB());

        Table table = dynamoDB.getTable(tableName);
        String json = table.getItem(DYNAMO_DB_KEY_NAME, lotUUID).toJSON(); // json -> String

        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, List> map = mapper.readValue(json, Map.class); // String -> Map<String, List> 변환

            Optional<List> lotSizeListOp = Optional.ofNullable(map.get("LotSizeList")); // null 체크
            Optional<List> lotImageListOp = Optional.ofNullable(map.get("LotImageList"));
            Optional<List> lotImageS3ListOp = Optional.ofNullable(map.get("LotImageS3List"));


            if(lotImageListOp.isPresent()) { // url "*" 제외
                lotDetail.setLotImageList(getSplitImageUrl(map.get("LotImageList")));
            }
            if(lotImageS3ListOp.isPresent()) { // S3 Url 추가
                lotDetail.setLotImageS3List(getReSizeImage(map.get("LotImageS3List")));
            }
            lotDetail.setLotSizeList(lotSizeListOp.orElse(new ArrayList()));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lotDetail;
    }

    private List getReSizeImage(List imageList) throws Exception {

        for(int i=0; i<imageList.size(); i++){
            String imgUrl = imageList.get(i).toString();
            ArrayList reSizeImgUrlList = new ArrayList(); // Image 3Size Url
            reSizeImgUrlList.add(imgUrl); // 원본 추가 : index 0

            String[] splitImgUrl = imgUrl.split(".com/"); // url 조정
            String smallSizeUrl = splitImgUrl[0]+".com/representative/r_"+splitImgUrl[1];
            String mediumSizeUrl = splitImgUrl[0]+".com/detail_view/d_"+splitImgUrl[1];

            reSizeImgUrlList.add(smallSizeUrl); // representative 추가 : index 1
            reSizeImgUrlList.add(mediumSizeUrl); // detail_view 추가 : index 2

            imageList.set(i, reSizeImgUrlList);

        }
        return  imageList;
    }

    private List getSplitImageUrl(List imageList) throws Exception { // Url 앞 * 문자 제거

        for(int i=0; i<imageList.size(); i++){
            String imgUrl = imageList.get(i).toString();
            if(imgUrl.substring(0,1).equals("*"))
                imageList.set(i, imgUrl.substring(1));
            else
                imageList.set(i, imgUrl);
        }
        return  imageList;
    }


    public void setLotDetailItemNotNull(AmazonDynamoDB dynamoDB, String tableName, Map<String, AttributeValue> key, String columnName, String setItem) throws Exception {

        Map<String, AttributeValueUpdate> updatedValues = new HashMap<>();

        String item = (String) setItem;
        updatedValues.put(columnName, new AttributeValueUpdate()
                .withValue(new AttributeValue().withS(item))
                .withAction(AttributeAction.PUT));

        UpdateItemRequest updateItemRequest = new UpdateItemRequest()
                .withTableName(tableName)
                .withKey(key)
                .withAttributeUpdates(updatedValues);

        dynamoDB.updateItem(updateItemRequest);

    }

    public void setLotDetailColumnNotNullItemNull(AmazonDynamoDB dynamoDB, String tableName, Map<String, AttributeValue> key, String columnName) throws Exception {

        Map<String, AttributeValueUpdate> updatedValues = new HashMap<String, AttributeValueUpdate>();

        updatedValues.put(columnName, new AttributeValueUpdate().withAction(AttributeAction.DELETE));

        UpdateItemRequest updateItemRequest = new UpdateItemRequest()
                .withTableName(tableName)
                .withKey(key)
                .withAttributeUpdates(updatedValues);

        UpdateItemResult updateItemResult = dynamoDB.updateItem(updateItemRequest);
    }

}
