package com.github.sejoung.api.dto;

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
public class AdvertiserClick {

    private Long no;
    private String targetgubun;
    private String siteCode;
    private String ip;
    private String userId;
    private String pcode;
    private String gb;
    private String mcgb;
    private String cwgb;
    private String sctxt;
    private String pnm;
    private String purl;
    private String rdate;
    private String rtime;
    private String imgpath;
    private String url;
    private String rf;
    private String price;
    private String cate1;
    private String mallnm;
    private String pcMobileGubun;
    private String status;
    private Long width;
    private Long height;
    private String kakaoStatus;
    private String auid;
    private String targetDate;
    private Long limit;
    private Boolean isEmpty;
    private String regdate;

    public String getShopId() {
        String shopId = this.getUserId();
        if (CommonConstants.MOBILE.equals(this.getPcMobileGubun())) {
            shopId = "m_" + this.getUserId();
        }
        return shopId;
    }
}
