package com.fiveletters.brush.service.feedback;

import com.fiveletters.brush.domain.feedback.Feedback;
import com.fiveletters.brush.domain.feedback.FeedbackRepository;
import com.fiveletters.brush.web.dto.FeedbackDto;
import com.fiveletters.brush.web.dto.PageDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @NonNull
    private final FeedbackRepository feedbackRepository;

    @Transactional(readOnly = true)
    public int getFeedbackTotalRecordCount(String searchKey, String searchValue) throws Exception {
        int totalRecordCount;
        if(searchKey.equals("Name")){
            totalRecordCount = feedbackRepository.countByNameContainingIgnoreCase(searchValue);
        }
        else {
            totalRecordCount = feedbackRepository.countByEmailContainingIgnoreCase(searchValue);
        }

        return totalRecordCount;
    }

    @Transactional(readOnly = true)
    public List<FeedbackDto> getFeedbackList(PageDto pageDto, String searchKey, String searchValue) throws Exception {
        Pageable paging = PageRequest.of(pageDto.getCurrentPageNo()-1, pageDto.getRecordCountPerPage());

        List<FeedbackDto> feedbackList;
        if(searchKey.equals("Name")){
            feedbackList = feedbackRepository
                    .findAllByNameContainingIgnoreCase(searchValue, paging)
                    .stream()
                    .map(FeedbackDto::new)
                    .collect(Collectors.toList());
        }
        else {
            feedbackList = feedbackRepository
                    .findAllByEmailContainingIgnoreCase(searchValue, paging)
                    .stream()
                    .map(FeedbackDto::new)
                    .collect(Collectors.toList());
        }

        return feedbackList;

    }

    @Transactional(readOnly = true)
    public FeedbackDto getFeedbackDetail(Long feedbackId) throws Exception {

        Feedback entity = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new IllegalArgumentException("해당 피드백이 없습니다. id="+feedbackId));

        return new FeedbackDto(entity);

    }

    @Transactional
    public void updateFeedback(Long feedbackId, FeedbackDto feedbackDto) throws Exception {

        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new IllegalArgumentException("해당 피드백이 없습니다. id="+feedbackId));

        feedback.update(feedbackDto.getName()
                , feedbackDto.getContent()
                , feedbackDto.getEmail());
    }

    @Transactional
    public void deleteFeedback(Long feedbackId) throws Exception {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new IllegalArgumentException("해당 피드백이 없습니다. id="+feedbackId));

        feedbackRepository.delete(feedback);

    }

}
