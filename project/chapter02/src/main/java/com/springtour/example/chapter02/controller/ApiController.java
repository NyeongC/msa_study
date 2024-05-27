package com.springtour.example.chapter02.controller;


import com.springtour.example.chapter02.domian.Hotel;
import com.springtour.example.chapter02.domian.HotelSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class ApiController {

    private HotelSearchService hotelSearchService;

    public ApiController(HotelSearchService hotelSearchService) {
        this.hotelSearchService = hotelSearchService;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = "/hotels/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("hotelId") Long hotelId) {
        Hotel hotel = hotelSearchService.getHotelById(hotelId);
        return ResponseEntity.ok(hotel);
    }
}
