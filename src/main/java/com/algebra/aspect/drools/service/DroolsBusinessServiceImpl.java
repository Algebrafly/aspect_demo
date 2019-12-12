package com.algebra.aspect.drools.service;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.model.KieModuleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author al
 * @date 2019/12/10 16:40
 * @description
 */
@Slf4j
public class DroolsBusinessServiceImpl implements DroolsBusinessService {

    @Value("${drl.path}")
    private String drlPath;

    /**
     * 加载规则目录
     */
    private final String BUILD_PATH = "src/main/resources/";

    /**
     * Kiebase
     */
    private final String KEI_BASE = "kieBase";

    /**
     * Kiebase
     */
    private final String SESSION = "session_";

    @Autowired
    private KieServices kieServices;

    @Autowired
    private KieFileSystem kieFileSystem;

    @Autowired
    private KieModuleModel kieModuleModel;

    @Autowired
    private KieRepository kieRepository;

    @Override
    public void buildAllRules() {
        log.info("Build rules start ...");
        File filePath = new File(drlPath);
        if (!filePath.exists()) {
            filePath.mkdir();
        }

        // 读取指定文件夹下的drl文件列表


        // 将规则文件存入kieFileSystem


    }

    @Override
    public void buildRules(String fileName, String code) {

    }

    @Override
    public void deleteRules(String fileName) {

    }

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = sdf.parse("2019-11-30");
        Date startDate = sdf.parse("2019-4-15");

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        int endDays = endCal.get(Calendar.DAY_OF_YEAR);
        int startDays = startCal.get(Calendar.DAY_OF_YEAR);

        int endYears = endCal.get(Calendar.YEAR);
        int startYears = startCal.get(Calendar.YEAR);

        if (startYears != endYears) {
            int timeDistance = 0;
            for (int i = startYears; i < endYears; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    timeDistance += 366;
                } else {
                    timeDistance += 365;
                }
            }
            System.out.println("相隔天数：" + (timeDistance + (endDays - startDays)));
        } else {
            System.out.println("相隔天数：" + (endDays - startDays));
        }

    }

    public static int differentDayMillisecond(Date date1, Date date2) {
        int day = (int) ((date2.getTime() - date1.getTime()) / (3600 * 1000 * 24));
        return day;
    }

}
