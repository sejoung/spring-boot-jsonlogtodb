package com.github.sejoung.api.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sejoung.api.constant.CommonConstants;
import com.github.sejoung.api.dao.LoggerFileDao;
import com.github.sejoung.api.dto.AdvertiserConversion;
import com.github.sejoung.api.dto.ImpressionClick;
import com.github.sejoung.api.dto.ImpressionView;
import com.github.sejoung.api.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoggerFileService {

    @Autowired
    private LoggerFileDao loggerFileDao;

    @Autowired
    private JsonUtil jsonUtil;

    public void click() throws IOException {
        this.filelist("E:\\lowdata\\drc", CommonConstants.CLICK);
    }

    public void view() throws IOException {

        this.filelist("E:\\lowdata\\hearina", CommonConstants.VIEW);

    }

    public void conversion() throws IOException {
        this.filelist("E:\\lowdata\\conversion", CommonConstants.CONVERSION);
    }

    private void filelist(String filePath, String code) throws IOException {

        File f1 = new File(filePath);
        String[] list = f1.list();

        for (int i = 0; i < list.length; i++) {
            File f2 = new File(filePath, list[i]);

            if (f2.isDirectory()) {
                this.filelist(f2.getCanonicalPath(), code);
            } else {
                this.writerfile(f2.getCanonicalPath(), f2.getName(), code);
            }
        }
    }

    private void writerfile(String filePath, String fileName, String code) throws IOException {

        BufferedReader in = null;
        try {
            log.debug("filePath ===> " + filePath);
            in = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = in.readLine()) != null) {
                if (CommonConstants.CLICK.equals(code)) {
                    ImpressionClick click = (ImpressionClick) jsonUtil.parseRequestJson(line, ImpressionClick.class);
                    Map<String, Object> data = new HashMap<String, Object>();
                    DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                    DateTime jodatime = dtf.parseDateTime(click.getRegdate());
                    DateTimeFormatter dtfOut = DateTimeFormat.forPattern("yyyyMMdd");
                    data.put("yyyymmdd", dtfOut.print(jodatime));
                    data.put("pltfom_tp_code", click.getPltfomTpCode());
                    data.put("advrts_prdt_code", CommonConstants.BANNER);
                    data.put("site_code", click.getSc());
                    data.put("media_script_no", click.getS());
                    data.put("advrts_rcmd_tp_code", click.getErgabtests());
                    data.put("advrts_amt", click.getPoint());
                    data.put("advrts_tp_code", click.getGb());
                    data.put("advrts_tp_code_detail", click.getSubadgubun());

                    // log.debug("click ={}", data);
                    loggerFileDao.insertAdlinkRcdStatusClick(data);

                } else if (CommonConstants.VIEW.equals(code)) {
                    ImpressionView view = (ImpressionView) jsonUtil.parseRequestJson(line, ImpressionView.class);

                    Map<String, Object> data = new HashMap<String, Object>();
                    DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                    DateTime jodatime = dtf.parseDateTime(view.getRegdate());
                    DateTimeFormatter dtfOut = DateTimeFormat.forPattern("yyyyMMdd");

                    data.put("yyyymmdd", dtfOut.print(jodatime));
                    data.put("pltfom_tp_code", view.getPltfomTpCode());
                    data.put("advrts_prdt_code", CommonConstants.BANNER);
                    data.put("site_code", view.getSiteCode());
                    data.put("media_script_no", view.getMediaScriptNo());
                    data.put("advrts_rcmd_tp_code", view.getAdvrtsRcmdTpCode());
                    data.put("tot_eprs_cnt", 1);
                    data.put("click_cnt", 0);
                    data.put("advrts_amt", 0);
                    data.put("userId", view.getUserId());
                    // data.put("frameCode", view.getFrameCode());
                    // data.put("target", view.getTarget());
                    data.put("advrts_tp_code", view.getAdvrtsTpCode());
                    data.put("advrts_tp_code_detail", view.getAdvrtsTpCodeDetail());
                    // data.put("ergabt", view.getErgabt());
                    // data.put("pcode", view.getPcode());
                    // data.put("pnm",view.getPnm());
                    // data.put("price", view.getPrice());
                    // data.put("img", view.getImg());
                    // data.put("site_url", view.getSiteUrl());

                    loggerFileDao.insertAdlinkRcdStatusView(data);

                } else if (CommonConstants.CONVERSION.equals(code)) {
                    AdvertiserConversion conversion = (AdvertiserConversion) jsonUtil.parseRequestJson(line, AdvertiserConversion.class);
                    Map<String, Object> data = new HashMap<String, Object>();
                    DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                    DateTime jodatime = dtf.parseDateTime(conversion.getRegdate());
                    DateTimeFormatter dtfOut = DateTimeFormat.forPattern("yyyyMMdd");
                    data.put("yyyymmdd", dtfOut.print(jodatime));
                    data.put("pltfom_tp_code", conversion.getPltfomTpCode());
                    data.put("advrts_prdt_code", CommonConstants.BANNER);
                    data.put("site_code", conversion.getSiteCode());
                    data.put("media_script_no", conversion.getMediaCode());
                    data.put("advrts_rcmd_tp_code", conversion.getErgabtests());
                    data.put("userId", conversion.getUserId());
                    data.put("frameCode", conversion.getFrameCode());
                    data.put("advrts_tp_code", conversion.getAdGubun());
                    data.put("advrts_tp_code_detail", conversion.getSubadgubun());
                    data.put("order_amt", conversion.getPrice());
                    
                    data.put("order_no", conversion.getOrdCode());
                    
                    data.put("click_tp", conversion.getPrice());
                    data.put("sesion_selng_yn", conversion.getDirect());
                    
                    data.put("direct_yn", conversion.getPrice());
                    data.put("mob_order_yn", conversion.getMobonYn());
                    data.put("cnvrs_tp_code", conversion.getPrice());
                    data.put("order_cnt", conversion.getPrice());
                    data.put("order_qy", conversion.getPrice());
                    
                    loggerFileDao.insertMobCnvrsStats(data);
                    //log.debug("conversion ={}", conversion);

                } else {
                    log.debug("--------------------------------->>>>>>>>>>>>>>>>>>>>>ERROR");
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
