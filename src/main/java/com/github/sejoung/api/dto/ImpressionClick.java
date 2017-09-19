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
public class ImpressionClick {
    private String keyIp;
    private String u;
    private String gb;
    private String sc;
    private String s;
    private String mc;
    private String no;
    private String kno;
    private String mcgb;
    private String product;
    private String pCode;
    private String frameid;
    private String cycleNum;
    private String frameSelector;
    private String pbGubun;
    private String freqLog;
    private String gender;
    private String age;
    private String serviceHostId;
    private String platform;
    private long campaignPoint;
    private long point;
    private long mpoint;
    private String type;
    private String regdate;
    private String subadgubun;
    private String ergabtests;
    private String mobonlinkcate;
    
    public String getPltfomTpCode() {
        String tpCode = CommonConstants.WEB;
        if ("M".equals(this.getPlatform())) {
            tpCode = CommonConstants.MOBILE;
        }
        return tpCode;
    }
}
