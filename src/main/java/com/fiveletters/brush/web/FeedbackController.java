package com.fiveletters.brush.web;

import com.fiveletters.brush.service.feedback.FeedbackService;
import com.fiveletters.brush.web.dto.FeedbackDto;
import com.fiveletters.brush.web.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class FeedbackController {

    private final FeedbackService feedbackService;

    @RequestMapping(value = "/feedback", method = {RequestMethod.GET, RequestMethod.POST} )
    public String feedbackList(Model model, PageDto pageDto, FeedbackDto feedbackDto) throws Exception {

        int totalRecordCount = feedbackService.getFeedbackTotalRecordCount(feedbackDto.getSearchKey(), feedbackDto.getSearchValue());
        pageDto.setTotalRecordCount(totalRecordCount);

        if(totalRecordCount !=0){
            List<FeedbackDto> feedbackList = feedbackService.getFeedbackList(pageDto, feedbackDto.getSearchKey(), feedbackDto.getSearchValue());
            model.addAttribute("feedbackList", feedbackList);
        }

        model.addAttribute("feedbackValues", feedbackDto);
        model.addAttribute("pagging", pageDto);
        model.addAttribute("totalRecordCount", totalRecordCount);

        return "feedback";
    }

    @RequestMapping(value = "/feedbackDetail/{feedbackId}", method = {RequestMethod.GET, RequestMethod.POST} )
    public String feedbackDetail(Model model, @PathVariable Long feedbackId) throws Exception {

        FeedbackDto feedbackDetail = feedbackService.getFeedbackDetail(feedbackId);
        model.addAttribute("feedbackDetail", feedbackDetail);

        return "feedbackDetail";
    }

    @ResponseBody
    @RequestMapping(value = "/updateFeedback/{feedbackId}", method = {RequestMethod.GET, RequestMethod.PUT} )
    public void updateFeedback(@PathVariable Long feedbackId, @RequestBody FeedbackDto feedBackDto) throws Exception {

        feedbackService.updateFeedback(feedbackId, feedBackDto);
    }

    @ResponseBody
    @DeleteMapping("/deleteFeedback/{feedbackId}")
    public void deleteFeedback(@PathVariable Long feedbackId) throws Exception {

        feedbackService.deleteFeedback(feedbackId);
    }

}
