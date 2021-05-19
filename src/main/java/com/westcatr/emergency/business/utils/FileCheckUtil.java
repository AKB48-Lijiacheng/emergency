package com.westcatr.emergency.business.utils;

import lombok.Data;

@Data
public class FileCheckUtil {

    /**
     * 将文件头转换成16进制字符串
     *
     * @return 16进制字符串
     */
    public static String bytesToHexString(byte[] src) {

        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


}

