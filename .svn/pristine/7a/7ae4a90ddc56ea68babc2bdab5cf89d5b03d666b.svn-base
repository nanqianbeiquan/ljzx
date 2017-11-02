/**  
* 文件名: MD5Util.java
* 包    名: com.srd.ljzd.util
* 描    述: TODO(用一句话描述该文件做什么)
* 作    者： 谭显伦 
* 日    期： 2016年4月12日
* 版    本： V1.0  
*/

package com.srd.ljzd.util;

/**
 * 类名: MD5Util
 * 描述: TODO(这里用一句话描述这个类的作用)
 * 作者： 谭显伦
 * 日期： 2016年4月12日
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
   
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public MD5Util() {
    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        
        if (iRet < 0) {
            iRet += 256;
        }
        
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        
        if (iRet < 0) {
            iRet += 256;
        }
        
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj==null?"":strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(resultString.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    public static void main(String[] args) {
    	MD5Util getMD5 = new MD5Util();
        System.out.println(getMD5.GetMD5Code("1234"));
    }
}