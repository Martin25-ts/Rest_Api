package com.exercise.listingservice.dto;


public class CreateListingResponseDto {

    private boolean result;
    private ListingDto listing;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ListingDto getListing() {
        return listing;
    }

    public void setListing(ListingDto listing) {
        this.listing = listing;
    }
}
