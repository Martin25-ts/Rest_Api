package com.exercise.listingservice.service.impl;

import com.exercise.listingservice.dto.CreateListingRequestDto;
import com.exercise.listingservice.dto.CreateListingResponseDto;
import com.exercise.listingservice.dto.GetListingsRequestDto;
import com.exercise.listingservice.dto.GetListingsResponseDto;
import com.exercise.listingservice.dto.ListingDto;
import com.exercise.listingservice.entity.Listing;
import com.exercise.listingservice.repository.ListingRepository;
import com.exercise.listingservice.service.ListingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService {

    @Autowired
    private ListingRepository listingRepository;

    @Override
    public GetListingsResponseDto getListings(GetListingsRequestDto requestDto) {
        PageRequest pageRequest = PageRequest.of(requestDto.getPageNum(),
                requestDto.getPageSize(), Sort.by("createdAt").descending()
        );

        List<Listing> result = Optional.ofNullable(requestDto.getUserId())
                .map(id -> listingRepository.findByUserId(id, pageRequest))
                .orElseGet(() -> listingRepository.findAll(pageRequest).getContent());

        List<ListingDto> listingDtoList = result.stream()
                .map(this::convertListingToListingDto)
                .collect(Collectors.toList());

        GetListingsResponseDto responseDto = new GetListingsResponseDto();
        responseDto.setResult(true);
        responseDto.setListings(listingDtoList);

        return responseDto;
    }

    @Override
    public CreateListingResponseDto createListing(CreateListingRequestDto requestDto) {
        Listing listing = new Listing();
        listing.setPrice(requestDto.getPrice());
        listing.setUserId(requestDto.getUserId());
        listing.setListingType(requestDto.getListingType());

        final Long timestampInMicroSecond = nowInEpochMicroSecond();
        listing.setCreatedAt(timestampInMicroSecond);
        listing.setUpdatedAt(timestampInMicroSecond);

        listingRepository.save(listing);

        CreateListingResponseDto responseDto = new CreateListingResponseDto();
        responseDto.setResult(true);
        responseDto.setListing(convertListingToListingDto(listing));

        return responseDto;
    }

    private ListingDto convertListingToListingDto(Listing listing) {
        ListingDto listingDto = new ListingDto();
        BeanUtils.copyProperties(listing, listingDto);

        return listingDto;
    }

    private Long nowInEpochMicroSecond() {
        return ChronoUnit.MICROS.between(Instant.EPOCH, Instant.now());
    }
}
