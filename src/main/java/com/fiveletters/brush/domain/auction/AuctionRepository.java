package com.fiveletters.brush.domain.auction;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Query(value = "SELECT AUCTION_RESULTS.SEQ AS SEQ" +
            ", AUCTION_RESULTS.AUCTION_NAME AS AUCTION_NAME" +
            ", AUCTION_RESULTS.TITLE AS TITLE" +
            ", AUCTION_RESULTS.LANDING_URL AS LANDING_URL" +
            ", AUCTION_RESULTS.SALE_YEAR AS SALE_YEAR" +
            ", AUCTION_RESULTS.SALE_PERIOD AS SALE_PERIOD" +
            ", AUCTION_RESULTS.SALE_LOCATION AS SALE_LOCATION" +
            ", AUCTION_RESULTS.ONLINE_YN AS ONLINE_YN" +
            ", AUCTION_RESULTS.CRAWL_YN AS CRAWL_YN" +
            ", AUCTION_RESULTS.TRANS_YN AS TRANS_YN" +
            ", AUCTION_RESULTS.INTEG_YN AS INTEG_YN" +
            ", AUCTION_RESULTS.INSERT_DT AS INSERT_DT " +
            "FROM (SELECT SEQ" +
            ", 'christies' AS AUCTION_NAME" +
            ", TITLE" +
            ", CONCAT('https://www.christies.com', LANDING_URI) AS LANDING_URL" +
            ", SALE_YEAR" +
            ", SALE_PERIOD" +
            ", SALE_LOCATION" +
            ", ONLINE_YN" +
            ", CRAWL_YN" +
            ", TRANS_YN" +
            ", INTEG_YN" +
            ", INSERT_DT " +
            "FROM CHRISTIES_AUCTION_RESULTS " +
            "UNION ALL " +
            "SELECT SEQ" +
            ", 'sothebys' AS AUCTION_NAME" +
            ", TITLE" +
            ", LANDING_URL AS LANDING_URL" +
            ", SALE_YEAR" +
            ", SUBSTRING_INDEX(SALE_PERIOD,'|',1) AS SALE_PERIOD" +
            ", SALE_LOCATION" +
            ", ONLINE_YN" +
            ", CRAWL_YN" +
            ", TRANS_YN" +
            ", INTEG_YN" +
            ", INSERT_DT " +
            "FROM SOTHEBYS_AUCTION_RESULTS) AS AUCTION_RESULTS " +
            "WHERE UPPER(TITLE) LIKE UPPER(CONCAT('%', ?3, '%')) " +
            "LIMIT ?1 OFFSET ?2"
            , nativeQuery = true)
    List<Auction> getAuctionResultsList(int recordCountPerPage, int firstRecordIndex, String searchValue);

    @Query(value = "SELECT COUNT(SEQ) AS TOTAL_RECORD " +
            "FROM ((SELECT SEQ" +
            ", TITLE " +
            "FROM CHRISTIES_AUCTION_RESULTS) " +
            "UNION ALL (SELECT SEQ" +
            ", TITLE " +
            "FROM SOTHEBYS_AUCTION_RESULTS)) AUCTION_RESULTS " +
            "WHERE UPPER(TITLE) LIKE UPPER(CONCAT('%', ?1, '%'))"
            , nativeQuery = true)
    Integer getTotalRecordCount(String searchValue);

    @Modifying
    @Query(value = "UPDATE CHRISTIES_AUCTION_RESULTS SET TITLE=?2 WHERE SEQ=?1"
            , nativeQuery = true)
    void setAuctionTitleChrisites(Long auctionId, String auctionTitle);

    @Modifying
    @Query(value = "UPDATE SOTHEBYS_AUCTION_RESULTS SET TITLE=?2 WHERE SEQ=?1"
            , nativeQuery = true)
    void setAuctionTitleSothebys(Long auctionId, String auctionTitle);

    @Modifying
    @Query(value = "UPDATE CHRISTIES_AUCTION_RESULTS SET CRAWL_YN=?2, TRANS_YN=?3 WHERE SEQ=?1"
            , nativeQuery = true)
    void setCrawlStatusChrisites(Long auctionId, String crawlStatus, String transStatus);

    @Modifying
    @Query(value = "UPDATE SOTHEBYS_AUCTION_RESULTS SET CRAWL_YN=?2, TRANS_YN=?3 WHERE SEQ=?1;"
            , nativeQuery = true)
    void setCrawlStatusSothebys(Long auctionId, String crawlStatus, String transStatus);
    
    @Modifying
    @Query(value = "UPDATE CHRISTIES_AUCTION_RESULTS SET TRANS_YN=?2 WHERE SEQ=?1"
            , nativeQuery = true)
    void setTransStatusChrisites(Long auctionId, String transStatus);

    @Modifying
    @Query(value = "UPDATE SOTHEBYS_AUCTION_RESULTS SET TRANS_YN=?2 WHERE SEQ=?1;"
            , nativeQuery = true)
    void setTransStatusSothebys(Long auctionId, String transStatus);

}