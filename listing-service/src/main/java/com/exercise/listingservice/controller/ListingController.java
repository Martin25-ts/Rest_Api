package com.exercise.listingservice.controller;

import com.exercise.listingservice.dto.CreateListingRequestDto;
import com.exercise.listingservice.dto.CreateListingResponseDto;
import com.exercise.listingservice.dto.GetListingsRequestDto;
import com.exercise.listingservice.dto.GetListingsResponseDto;
import com.exercise.listingservice.service.ListingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class ListingController {

    @Autowired
    private ListingService listingService;

    @GetMapping("/listings")
    public GetListingsResponseDto getListings(
            @RequestParam(name = "pageNum", defaultValue = "1") @Min(1) Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") @Min(1) Integer pageSize,
            @RequestParam(name = "userId", required = false) @Min(1) Integer userId
    ) {
        GetListingsRequestDto requestDto = new GetListingsRequestDto();
        requestDto.setPageNum(pageNum - 1);
        requestDto.setPageSize(pageSize);
        requestDto.setUserId(userId);

        return listingService.getListings(requestDto);
    }

    @PostMapping("/listings")
    public CreateListingResponseDto createListing(@Valid CreateListingRequestDto requestDto) {
        return listingService.createListing(requestDto);
    }
    
    
    @GetMapping("/")
    public String dashboard() {
    	return "hello";
    }
}
