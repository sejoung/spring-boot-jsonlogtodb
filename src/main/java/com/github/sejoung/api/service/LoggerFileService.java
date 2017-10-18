package com.github.sejoung.api.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sejoung.api.constant.CommonConstants;
import com.github.sejoung.api.dao.LoggerFileDao;
import com.github.sejoung.api.dao.RedisDao;
import com.github.sejoung.api.dto.AdvertiserClick;
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
    private RedisDao redisDao;

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

    public void rfshop() throws IOException {
        this.filelist("E:\\lowdata\\rfshop", CommonConstants.RFSHOP);
    }

    public void auidText() throws Exception {

        PrintWriter pw = new PrintWriter("E:\\\\2개같은상품본것제거.txt");
        Set<String> st = redisDao.selectAuidData();
        System.out.println("start");

        for (String key : st) {
            pw.println(key);
        }

        pw.close();
        System.out.println("end");

    }

    public void auidDuplication() throws Exception {

        Set<String> st = redisDao.selectAuidData();
        System.out.println("start");
        for (String key : st) {
            String result = redisDao.selectAuidPcodeDataOne("auid:" + key);
            int i = result.split(",").length;
            if (i == 1) {
                redisDao.deleteAuidPcodeDataOne(key);
            }
        }
        System.out.println("end");
    }

    public void auidDuplication2() throws Exception {

        Set<String> st = redisDao.selectAuidData();
        System.out.println("start");
        int rownum = 0;
        for (String key : st) {
            String result = redisDao.selectAuidPcodeDataOne("auid:" + key);
            String[] s = result.split(",");
            int cnt = s.length;
            if (cnt == 2 && s[0].equals(s[1])) {
                redisDao.deleteAuidPcodeDataOne(key);
                rownum++;
            }
        }

        System.out.println("end " + rownum);
    }

    public void auidExcel() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        FileOutputStream fos = new FileOutputStream(new File("E:\\CLICK_20171017.xls"));
        HSSFSheet sheet = wb.createSheet();

        Set<String> st = redisDao.selectAuidData();
        int rownum = 0;

        System.out.println("start");
        for (String key : st) {
            String result = redisDao.selectAuidPcodeDataOne("auid:" + key);
            String[] s = result.split(",");
            int cnt = s.length;

            HSSFRow row = sheet.createRow(rownum);
            HSSFCell keycell = row.createCell(1);
            keycell.setCellValue(key);
            HSSFCell cntcell = row.createCell(2);
            cntcell.setCellValue(cnt);
            HSSFCell pcodecell = row.createCell(3);
            pcodecell.setCellValue(result);
            System.out.println(rownum);
            rownum++;
        }
        wb.write(fos);
        fos.close();
        System.out.println("end");
    }

    public void auidText2() throws Exception {
        PrintWriter pw = new PrintWriter("E:\\\\2개같은상품본것제거.txt");
        Set<String> st = redisDao.selectAuidData();
        int rownum = 0;

        System.out.println("start");
        for (String key : st) {
            String result = redisDao.selectAuidPcodeDataOne("auid:" + key);
            String[] s = result.split(",");
            int cnt = s.length;
            pw.println(key + "|" + cnt + "|" + result);
            System.out.println(rownum);
            rownum++;
        }
        pw.close();
        System.out.println("end");
    }

    public void test() {
        Map<String, Object> data = new HashMap<String, Object>();

        loggerFileDao.updatetest(data);
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
                    // log.debug("conversion ={}", conversion);

                } else if (CommonConstants.RFSHOP.equals(code)) {
                    AdvertiserClick click = (AdvertiserClick) jsonUtil.parseRequestJson(line, AdvertiserClick.class);

                    try {
                        if (CommonConstants.MOBILE.equals(click.getPcMobileGubun())) {
                            redisDao.createAuidPcodeData("auid:" + click.getAuid(), click.getPcode());
                            redisDao.createAuidData(click.getAuid());
                            redisDao.createPcodeData(click.getPcode());
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
