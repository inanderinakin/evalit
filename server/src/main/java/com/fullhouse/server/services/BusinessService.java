package com.fullhouse.server.services;

import com.fullhouse.DTOs.BusinessListRequest;
import com.fullhouse.DTOs.BusinessListResponse;

public interface BusinessService {
    BusinessListResponse getBusinesses(BusinessListRequest request);
}