package com.fiveletters.brush.web;

import com.fiveletters.brush.service.artist.ArtistService;
import com.fiveletters.brush.web.dto.ArtistDto;
import com.fiveletters.brush.web.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ArtistController {

    private final ArtistService artistService;

    @RequestMapping(value = "/artist", method = {RequestMethod.GET, RequestMethod.POST} )
    public String artistList(Model model, PageDto pageDto, ArtistDto artistDto) throws Exception {

        String searchValue = artistDto.getSearchValue();
        int totalRecordCount = artistService.getArtistTotalRecordCount(searchValue);
        pageDto.setTotalRecordCount(totalRecordCount);

        if(totalRecordCount !=0){
            List<ArtistDto> artistList = artistService.getArtistList(pageDto, searchValue);
            model.addAttribute("artistList", artistList);
        }

        model.addAttribute("searchValue", searchValue);
        model.addAttribute("pagging", pageDto);
        model.addAttribute("totalRecordCount", totalRecordCount);

        return "artist";
    }

    @RequestMapping(value = "/artistDetail/{artistId}", method = {RequestMethod.GET, RequestMethod.POST} )
    public String artistDetail(Model model, @PathVariable Long artistId) throws Exception {

        ArtistDto artistDetail = artistService.getArtistDetail(artistId);
        model.addAttribute("artistDetail", artistDetail);

        return "artistDetail";
    }

    @RequestMapping(value = "/artistCreate", method = {RequestMethod.GET} )
    public String artistCreate() throws Exception {

        return "artistCreate";
    }

    @ResponseBody
    @RequestMapping(value = "/saveArtist", method = {RequestMethod.GET, RequestMethod.POST} )
    public void saveArtist(ArtistDto artistDto) throws Exception {

        artistService.saveArtist(artistDto);
    }

    @ResponseBody
    @RequestMapping(value = "/updateArtist/{artistId}", method = {RequestMethod.GET, RequestMethod.PUT} )
    public void updateArtist(@PathVariable Long artistId, @RequestBody ArtistDto artistDto) throws Exception {

        artistService.updateArtist(artistId, artistDto);
    }

    @ResponseBody
    @RequestMapping(value = "/updateArtistUseYN", method = {RequestMethod.GET, RequestMethod.PUT} )
    public void updateArtistUseYN(@RequestBody ArtistDto artistDto) throws Exception {

        artistService.updateArtistUseYN(artistDto);
    }

    @ResponseBody
    @DeleteMapping("/deleteArtist/{artistId}")
    public void deleteArtist(@PathVariable Long artistId) throws Exception {

        artistService.deleteArtist(artistId);
    }

}
