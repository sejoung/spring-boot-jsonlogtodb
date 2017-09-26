package com.github.sejoung.api.dto;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sejoung.api.constant.CommonConstants;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kim se joung
 *
 */
@Getter
@Setter
@ToString
public class AdvertiserConversion {
    private String siteCode;
    private String adGubun;
    private String yyyymmdd;
    private String userId;
    private Float point;
    private String ip;
    private String kno;
    private String type;
    private String pcode;
    private String regdate;
    private String siteUrl;
    private Long viewcnt1;
    private Long viewcnt2;
    private Long viewcnt3;
    private Long agoViewcnt1;
    private Long agoViewcnt2;
    private String ordRFUrl;
    private String ordQty;
    private String pnm;
    private String price;
    private String ordCode;
    private String uname;
    private String usex;
    private String upno;
    private String direct;
    private String mediaCode;
    private Float mpoint;
    private String platform;
    private Long freqLog;
    private String gender;
    private String age;
    private String serviceHostId;
    private String abtests;
    private Long time;
    private String frameId;
    private Long frameCycleNum;
    private String frameSelector;
    private String auid;
    private String ergabtests;
    private String subadgubun;
    private List<Object> actionLogList = null;
    private String lastClickTime;
    private String mobonYn;
    private String inflowRoute;
    private Long limit;
    private Boolean isEmpty;
    private String shopId;
    private String pltfomTpCode;
    private String domain;
    private String frameCode;
    
}
