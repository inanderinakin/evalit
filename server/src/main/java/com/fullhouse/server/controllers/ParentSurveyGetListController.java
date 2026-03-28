package com.fullhouse.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller receives a User's id
 * and returns the list of ParentSurveys
 * that are created by that User.
 */

@RestController
@RequestMapping("parent-survey/get-list")
public class ParentSurveyGetListController {

}
