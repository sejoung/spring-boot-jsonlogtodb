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
    
    

    @ApiOperation(value = "rfshop", notes = "rfshop을 위한 API 입니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/rfshop", method = RequestMethod.PUT)
    public void rfshop() throws IOException {
        loggerFileService.rfshop();
    }
    
    
    @ApiOperation(value = "auidText", notes = "auidText 을 위한 API 입니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/auidText", method = RequestMethod.PUT)
    public void auidText() throws Exception {
        loggerFileService.auidText();
    }
    
    
    @ApiOperation(value = "auidDuplication", notes = "auidDuplication 을 위한 API 입니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/auidDuplication", method = RequestMethod.PUT)
    public void auidDuplication() throws Exception {
        loggerFileService.auidDuplication();
    }
    
    @ApiOperation(value = "auidDuplication2", notes = "auidDuplication2 을 위한 API 입니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/auidDuplication2", method = RequestMethod.PUT)
    public void auidDuplication2() throws Exception {
        loggerFileService.auidDuplication2();
    }
    
    @ApiOperation(value = "auidExcel", notes = "auidExcel 을 위한 API 입니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/auidExcel", method = RequestMethod.PUT)
    public void auidExcel() throws Exception {
        loggerFileService.auidExcel();
    }
    
    @ApiOperation(value = "auidText2", notes = "auidExcel 을 위한 API 입니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/auidText2", method = RequestMethod.PUT)
    public void auidText2() throws Exception {
        loggerFileService.auidText2();
    }
    
    

    @ApiOperation(value = "업데이트테스트", notes = "테스트용")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/test", method = RequestMethod.PUT)
    public void test() {
        loggerFileService.test();
    }

    
    
}
