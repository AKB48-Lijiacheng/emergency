package com.westcatr.emergency.business.utils;

/**
 * @author lijiacheng
 * @Date 2021/3/29
 */
public class FileUtil {
    //获取文件后缀
    public static String getFileSuffix(String type) {
        if (type==null){
            return ".xlsx";
        }
        String fileSuffix;
        switch (type) {
            case "excel":
                fileSuffix = ".xlsx";
                break;
            case "pdf":
                fileSuffix = ".pdf";
                break;
            case "word":
                fileSuffix = ".docx";
                break;
            default:
                fileSuffix = ".xlsx";
        }
        ;
        return fileSuffix;
    }
}
