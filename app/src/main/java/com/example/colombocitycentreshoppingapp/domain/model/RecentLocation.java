package com.example.colombocitycentreshoppingapp.domain.model;

public class RecentLocation {

    String locationName;
    String locationAddress;

    public RecentLocation(String locationName, String locationAddress) {
        this.locationName = locationName;
        this.locationAddress = locationAddress;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }
}
