package com.exercise.listingservice.dto;


import java.util.List;

public class GetListingsResponseDto {

    private boolean result;
    private List<ListingDto> listings;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<ListingDto> getListings() {
        return listings;
    }

    public void setListings(List<ListingDto> listings) {
        this.listings = listings;
    }
}
