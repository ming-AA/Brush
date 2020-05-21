package com.fiveletters.brush.service.feedback;

import com.fiveletters.brush.web.dto.FeedbackDto;
import com.fiveletters.brush.web.dto.PageDto;

import java.util.List;

public interface FeedbackService {

    public int getFeedbackTotalRecordCount(String searchKey, String searchValue) throws Exception;

    public List<FeedbackDto> getFeedbackList(PageDto pageDto, String searchKey, String searchValue) throws Exception;

    public FeedbackDto getFeedbackDetail(Long feedbackId) throws Exception;

    public void updateFeedback(Long feedbackId, FeedbackDto feedbackDto) throws Exception;

    public void deleteFeedback(Long feedbackId) throws Exception;
}
