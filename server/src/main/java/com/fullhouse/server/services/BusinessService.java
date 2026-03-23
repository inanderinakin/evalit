package com.fullhouse.server.services;

import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByCityCategoryRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByCityCategoryResponse;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByNameRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByNameResponse;

public interface BusinessService {
    BusinessGetListByNameResponse getBusinessesByName(BusinessGetListByNameRequest request);
    BusinessGetListByCityCategoryResponse getBusinessesByCategoryAndCity(BusinessGetListByCityCategoryRequest request);
}