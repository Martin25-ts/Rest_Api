package com.exercise.listingservice.service;

import com.exercise.listingservice.dto.CreateListingRequestDto;
import com.exercise.listingservice.dto.CreateListingResponseDto;
import com.exercise.listingservice.dto.GetListingsRequestDto;
import com.exercise.listingservice.dto.GetListingsResponseDto;

public interface ListingService {

    GetListingsResponseDto getListings(GetListingsRequestDto requestDto);

    CreateListingResponseDto createListing(CreateListingRequestDto requestDto);
}
