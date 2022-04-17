package com.abc.gcsmsys.domain;

import lombok.Data;

/**
 * @Description
 */
@Data
public class ItemData {

//    private String project;
//
//    private String annualBudget;
//
//    private String cumulativExecution;
//
//    private String progress;
//
//    private String progressDeviation;
//
//    public ItemData(String project, String annualBudget, String cumulativExecution, String progress, String progressDeviation) {
//        this.project = project;
//        this.annualBudget = annualBudget;
//        this.cumulativExecution = cumulativExecution;
//        this.progress = progress;
//        this.progressDeviation = progressDeviation;
//    }


    private Integer personId;

    private String residentName;

    private String residentIdCard;

    /**民族*/
    private String ethnic;

    /**户籍*/
    private String houseAddr;

    /**数组转String*/
    public static String houseAddrToString(String[] houseAddr){

        String houArrToString = "";
        for (int i=0;i<houseAddr.length;i++){
             houArrToString += houseAddr[i];
        }

        return houArrToString;
    }

    public ItemData(Integer personId, String residentName, String residentIdCard, String ethnic, String[] houseAddr) {
        this.personId = personId;
        this.residentName = residentName;
        this.residentIdCard = residentIdCard;
        this.ethnic = ethnic;
        this.houseAddr = ItemData.houseAddrToString(houseAddr);
    }
}
