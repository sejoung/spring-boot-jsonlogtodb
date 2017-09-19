package com.github.sejoung.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.sejoung.api.service.LoggerFileService;

import io.swagger.annotations.ApiOperation;

@RestController
public class LoggerFileController {
    
    @Autowired
    private LoggerFileService loggerFileService;

    @ApiOperation(value = "광고클릭등록", notes = "광고 클릭정보 등록을 위한 API 입니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/click", method = RequestMethod.PUT)
    public void click() throws IOException {
        loggerFileService.click();
    }

    @ApiOperation(value = "광고정보등록", notes = "광고 뷰정보 등록을 위한 API 입니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/view", method = RequestMethod.PUT)
    public void view() throws IOException {
        loggerFileService.view();
    }

    @ApiOperation(value = "구매정보등록", notes = "구매 정보 등록을 위한 API 입니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/conversion", method = RequestMethod.PUT)
    public void conversion() throws IOException {
        loggerFileService.conversion();
    }

}
