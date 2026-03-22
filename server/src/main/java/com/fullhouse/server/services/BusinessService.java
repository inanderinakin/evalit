package com.fullhouse.server.services;

import com.fullhouse.DTOs.BusinessGetListByNameRequest;
import com.fullhouse.DTOs.BusinessGetListByNameResponse;

public interface BusinessService {
    BusinessGetListByNameResponse getBusinessesByName(BusinessGetListByNameRequest request);
}