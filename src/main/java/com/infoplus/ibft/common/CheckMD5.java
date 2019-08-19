package com.infoplus.ibft.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CheckMD5 {
	//public static final String MaBM = "970457";
	final Logger logger = LogManager.getLogger(this.getClass());

    public String getMD5Hash(String value)
		    throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
	    StringBuilder sbMd5Hash = new StringBuilder();
	    MessageDigest m = MessageDigest.getInstance("MD5");
	    m.update(value.getBytes("UTF-8"));
	    byte[] data = m.digest();
	    for (byte element : data)
	    {
	    	sbMd5Hash.append(Character.forDigit(element >> 4 & 0xF, 16));
	    	sbMd5Hash.append(Character.forDigit(element & 0xF, 16));
	    }
		logger.info(" getMD5Hash : [" + sbMd5Hash.toString() + "]");

	    return sbMd5Hash.toString();
    }
  
	public boolean verifyMd5Hash(String input, String hash)
			throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
	    return getMD5Hash(input).toUpperCase().equals(hash.toUpperCase());
	}
	  
	public String getCS(String input)
	{
	    try
	    {
	        String string1 = "";
		    String string2 = getMD5Hash(input);
		    //String string3 = string2;
		    String sMaBM = "5" + AppConst.MD5_SECURITY_CODE + "5";
		    if (isInteger(sMaBM))
		    {
		    	char[] charArray1 = sMaBM.toCharArray();
		    	for (int i1 = 0; i1 < charArray1.length - 1; i1++)
		    	{
		    		Integer iHead = Integer.valueOf(String.valueOf(charArray1[i1]));
		    		Integer iLast = Integer.valueOf(20 - Integer.valueOf(String.valueOf(charArray1[(i1 + 1)])).intValue() + iHead.intValue());
		      
		    		string1 = string1 + string2.substring(iHead.intValue(), iLast.intValue());
		    	}
		    }
		    return getMD5Hash(string1);
		}
		catch (Exception localException) {
			logger.error(" getCS Error : [" + localException + "]");
		}
		
	    return null;
	}
	  
	public boolean isInteger(String str)
	{
	    try
	    {
	    	//int i = Integer.parseInt(str);
	    	Integer.parseInt(str);
	    }
	    catch (NumberFormatException nfe)
	    {
	    	return false;
	    }
	    return true;
	}
	  
	public boolean verifyCS(String input, String hash)
	{
	    String s = getCS(input);
	    
	    return s.compareTo(hash) == 0;
	}

    /*
     * MD5 
     */
	public boolean checkCheckSum(String fileData, String chkSum) {
    	
    	// Check Check sum
		CheckMD5 chMD5 = null;
		chMD5 = new CheckMD5();
   		try {
   			if (chMD5.verifyCS(fileData, chkSum)) {
   				logger.info(" * "+ ". Compare checksum : [" + chkSum + "]");
   			}
   			else {
   				logger.info(" * "+ ". Compare checksum Error ");
   				logger.info(" * "+ ". input   :[" + chkSum + "]");
   				logger.info(" * "+ ". In Data :[" + fileData + "]");
   	   	    	return false;
   			}
   		}
   		catch (Exception ee) {
   			logger.info(" * "+ ". getCS Error :[" + ee + "]");
   	    	throw ee;
   			
   		}
    	
    	return true;
    }

    /*
     * MD5 
     */
	public boolean checkHash(String fileData, String chkSum) throws Exception {
    	
    	// Check Check sum
		CheckMD5 chMD5 = null;
		chMD5 = new CheckMD5();
   		try {
   			if (chMD5.verifyMd5Hash(fileData, chkSum)) {
   				logger.info(" * "+". Compare checksum : [" + chkSum + "]");
   			}
   			else {
   				logger.info(" * "+". Compare checksum Error ");
   				logger.info(" * "+". input   :[" + chkSum + "]");
   				logger.info(" * "+". In Data :[" + fileData + "]");
   	   	    	return false;
   			}
   		}
   		catch (Exception ee) {
   			logger.info(" * "+". getCS Error :[" + ee + "]");
   	    	throw ee;
   		}
    	
    	return true;
    }
}
