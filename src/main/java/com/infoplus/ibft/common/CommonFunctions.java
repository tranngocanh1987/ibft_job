package com.infoplus.ibft.common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import com.infoplus.ibft.model.NapasDataFormat;

public class CommonFunctions {

	/**
	 * Set field name
	 * 
	 * @param fieldInd
	 * @param fieldName
	 * @return
	 */
	public static String setFieldName(String fieldInd, String fieldName) {
		StringBuffer sb = new StringBuffer();
		sb.append("[" + fieldInd + "]");
		sb.append(fieldName);

		return sb.toString();
	}

	/**
	 * Set field name
	 * 
	 * @param fieldInd   : identification
	 * @param fieldName: name
	 * @param fieldType: type
	 * @param length
	 * @return
	 */
	public static String setFieldName(String fieldInd, String fieldType, int length, String fieldName) {
		StringBuffer sb = new StringBuffer();
		sb.append("[" + fieldInd + "]");

		switch (fieldType) {
		case AppConst.FIELD_CHAR:
			sb.append(StringUtils.leftPad(fieldName, length, " "));
			break;
		case AppConst.FIELD_NUM:
			sb.append(StringUtils.leftPad(fieldName, length, "0"));
			break;
		default:
			break;
		}

		return sb.toString();
	}

	/**
	 * anhtn5 Parse transaction from string to object
	 * 
	 * @param info
	 * @return
	 */
	public static List<NapasDataFormat> parseTransactionsInfo(String info) {

		NapasDataFormat dataFormat = new NapasDataFormat();
		List<NapasDataFormat> lstDataFormat = new ArrayList<NapasDataFormat>();

		// remove the DR character
		info = info.replaceFirst(AppConst.DETAIL, StringUtils.EMPTY);
		StringTokenizer multiTokenizer = new StringTokenizer(info, AppConst.TOKEN);

		// get data info
		if (multiTokenizer != null && multiTokenizer.countTokens() > 0) {
			int index = 0;
			String value = StringUtils.EMPTY;

			while (multiTokenizer.hasMoreTokens()) {
				value = multiTokenizer.nextToken();

				if (index % 2 == 1) {
					dataFormat.setValue(value.trim());
					lstDataFormat.add(dataFormat);
					dataFormat = new NapasDataFormat();
				} else {
					dataFormat.setFieldName(value);
				}

				index++;
			}
			return (lstDataFormat);
		}
		return null;
	}

	/**
	 * anhtn5 parsing data to MD5 value
	 * 
	 * @param info: type [IND] value
	 * @return
	 */
	public static String getMd5Value(String info) {
		String value = StringUtils.EMPTY;
		String[] temp = info.split(AppConst.CSR_REGEX);

		if (temp != null && temp.length > 0) {
			value = temp[0];
		}
		String md5Value = CommonFunctions.setFieldName(AppConst.NAPAS_CSR, new CheckMD5().getCS(value));
		value += md5Value;
		return value;
	}

	/**
	 * anhtn5
	 * get data with none MD5 Value
	 * @param info
	 * @return
	 */
	public static String getNoneMd5Value(String info) {
		String value = StringUtils.EMPTY;
		String[] temp = info.split(AppConst.CSR_REGEX);

		if (temp != null && temp.length > 0) {
			value = temp[0];
		}
		return value;
	}
	
	/**
	 * anhtn5 
	 * get only MD5 value
	 * @param info
	 * @return
	 */
	public static String getOnlyMd5Value(String info) {
		String value = StringUtils.EMPTY;
		String[] temp = info.split(AppConst.CSR_REGEX);

		if (temp != null && temp.length >= 1) {
			value = temp[1];
		}
		return value;
	}
	
	
	/**
	 * anhtn5 
	 * get last characters by length
	 * @param input
	 * @param length
	 * @return
	 */
	public static String getLastChars(String input, int length) {
		String lastDigits;
		if (input.length() > length)
			lastDigits = input.substring(input.length() - length);
		else
			lastDigits = input;
		return lastDigits;
	}
	
	/**
	 * anhtn5 
	 * get first characters by length
	 * @param input
	 * @param length
	 * @return
	 */
	public static String getFirstChars(String input, int length) {
		String lastDigits;
		if (input.length() > length)
			lastDigits = input.substring(0, length);
		else
			lastDigits = input;
		return lastDigits;
	}
	
	/**
	 * anhtn5
	 * compare 2 settlement date string
	 * @param arg1 MMdd
	 * @param arg2 MMdd
	 * @return 0 if arg1 == arg2 or exception, more than 0 if arg1 > arg2, less than 0 if arg1 < arg2
	 */
	public static int compare2SettlementDate(String arg1, String arg2) {
		String std1 = arg1;
		String std2 = arg2;
		
		if(StringUtils.isEmpty(std1))
			std1 = arg2;
		
		if(StringUtils.isEmpty(std1) && StringUtils.isEmpty(std2))
			return 0;
		
		try {
			return Integer.compare(Integer.parseInt(std1), Integer.parseInt(std2));
			
		}catch(Exception ex) {
			return 0;
		}
	}
}
