package com.fullhouse.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullhouse.DTOs.BusinessGetListByCityCategoryResponse;
import com.fullhouse.server.services.HomePageService;

@RestController
public class HomePageController {
    private HomePageService homePageService;
    public HomePageController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }

    @GetMapping("home/getBusinessList")
    public BusinessGetListByCityCategoryResponse getBusinessList() {
        return homePageService.getBusinessList();
    }
}
