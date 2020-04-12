package com.tongji.f4.stt.model.testresult;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: stt
 * @description: The test result class for excel
 * @author: saturn
 * @create: 2020/04/11
 **/
public class ExcelTestResult {
    private float percentage;

    public float getPercentage() {
        return percentage;
    }

    public int getPassNum() {
        return passNum;
    }

    public List<String> getFailLog() {
        return failLog;
    }

    private int passNum;
    private List<String> failLog = new ArrayList<>();

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    public void addFailLog(String log){
        failLog.add(log);
    }
}
