package com.github.sejoung.api.dto;

import com.google.gson.annotations.SerializedName;

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
public class ImpressionView {
    
    @SerializedName("img")
    private String img;
    
    @SerializedName("pcode")
    private String pcode;
    
    @SerializedName("ergabt")
    private String ergabt;
    
    @SerializedName("site_code")
    private String siteCode;
    @SerializedName("advrts_rcmd_tp_code")
    private String advrtsRcmdTpCode;
    @SerializedName("frameCode")
    private String frameCode;
    @SerializedName("userId")
    private String userId;
    @SerializedName("advrts_tp_code_detail")
    private String advrtsTpCodeDetail;
    @SerializedName("pltfom_tp_code")
    private String pltfomTpCode;
    @SerializedName("advrts_tp_code")
    private String advrtsTpCode;
    @SerializedName("pnm")
    private String pnm;
    @SerializedName("target")
    private String target;
    @SerializedName("site_url")
    private String siteUrl;
    @SerializedName("price")
    private String price;
    @SerializedName("regdate")
    private String regdate;
    @SerializedName("media_script_no")
    private String mediaScriptNo;
    
}
