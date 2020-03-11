package com.mvcweb.base;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The common operation utilities.
 * Including string operation, date operation, collection operation, array operation, and etc.
 * 
 * @author Xiufeng.Bao
 */
public class CommonUtils {

	/** The empty String */
	public static final String EMPTY = "";
	/** The comma String */
	public static final String COMMA = ",";
	
	/** The percent String */
	private static final String PERCENT = "%";
	/** The Get String */
	private static final String GET = "get";
	/** The Set String */
	private static final String SET = "set";
	/** The filling string */
	private static final String TRUNCATE_FILLING = "...";
	
	/** The default date format */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /** The default time format */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    /** The default datetime format */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** The default datetime formator */
    public static final SimpleDateFormat DEFAULT_DTF = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
    
    /**
     * Checks if a Object is null or empty.
     *
     * @param o The Object to check, may be null
     * @return true if the Object is null or empty
     */
    public static boolean isNull(Object o) {
        return (o == null || o.toString().length() == 0);
    }

    /**
     * Checks if a Object is not null and not empty.
     *
     * @param o The Object to check, may be null
     * @return true if the Object is not null and not empty
     */
    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }
    
    /**
     * Checks if a String is empty or null.
     *
     * @param str The String to check, may be null
     * @return true if the String is empty or null
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * Checks if a String is not empty and not null.
     *
     * @param str The String to check, may be null
     * @return true if the String is not empty and not null
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * Checks if a array is null or empty.
     * 
     * @param objects Object array
     * @return true if The array is null or empty
     */
	public static boolean isEmpty(Object[] objects) {
		return (objects == null || objects.length == 0);
	}
	
	/**
     * Checks if a array is not null and not empty.
     * 
     * @param objects Object array
     * @return true if The array is not null and not empty
     */
	public static boolean isNotEmpty(Object[] objects) {
		return !isEmpty(objects);
	}
    
    /**
     * Checks if a Collection is null or empty.
     * 
     * @param objects Object list
     * @return true if The Collection is null or empty
     */
	public static boolean isEmpty(Collection<? extends Object> objects) {
		return (objects == null || objects.isEmpty());
	}
    
    /**
     * Checks if a Collection is not null and not empty.
     * 
     * @param objects Object list
     * @return true if The Collection is not null and not empty
     */
	public static boolean isNotEmpty(Collection<? extends Object> objects) {
		return !isEmpty(objects);
	}

    /**
     * Removes control characters (char &lt;= 32) from both ends of this String.
     * Returning an empty String ("") if the String is empty ("") after the trim or if it is null.
     *
     * @param str The String to be trimmed, may be null
     * @return The trimmed String, or an empty String if null input
     */
    public static String trimToEmpty(String str) {
        return (str == null ? EMPTY : str.trim());
    }
    
    /**
     * Removes control characters (char &lt;= 32) from both ends of this String.
     * Returning null if the String is empty ("") after the trim or if it is null.
     * 
     * @param str The String to be trimmed, may be null
     * @return The trimmed String, null if only chars &lt;= 32, empty or null String input
     */
    public static String trimToNull(String str) {
        String trimStr = trim(str);
        return (trimStr == null || trimStr.length() == 0 ? null : trimStr);
    }
    
    /**
     * Gets the current date String by given optional date format.
     * 
     * @param format Optional date format
     * @return Formatted current date String
     */
    public static String getCurrentDate(String... format) {
    	if (format.length == 1) {
    		SimpleDateFormat df = new SimpleDateFormat(format[0]);
    		return df.format(new Date());
    	}
    	return DEFAULT_DTF.format(new Date());
    }
    
    /**
     * Converts the given String to date by given optional date format.
     * 
     * @param str The String to be converted
     * @param format Optional date format
     * @return Converted date
     */
    public static Date stringToDate(String str, String... format) {
		SimpleDateFormat df = (format.length == 1 ? new SimpleDateFormat(
				format[0]) : DEFAULT_DTF);
    	try {
    		return df.parse(str);
    	} catch (Exception e) {
    		return null;
    	}
    }
    
    /**
     * Converts the given Date to date String by given optional date format.
     * 
     * @param d The Date to be converted
     * @param format Optional date format
     * @return Converted date String
     */
    public static String dateToString(Date d, String... format) {
		SimpleDateFormat df = (format.length == 1 ? new SimpleDateFormat(
				format[0]) : DEFAULT_DTF);
    	try {
    		return df.format(d);
    	} catch (Exception e) {
    		return null;
    	}
    }
    
    /**
     * Adds percent character around the given String.
     * Insert position: Before and After the given String.
     * 
     * @param value The String to be inserted
     * @return Inserted String
     */
    public static String addBAPercent(String value) {
    	if (isEmpty(value)) return EMPTY;
		return new StringBuffer().append(PERCENT).append(value.trim()).append(PERCENT).toString();
    }
    
    /**
     * Adds percent character around the given String.
     * Insert position: After the given String.
     * 
     * @param value The String to be inserted
     * @return Inserted String
     */
    public static String addAPercent(String value) {
    	if (isEmpty(value)) return EMPTY;
		return new StringBuffer().append(value.trim()).append(PERCENT).toString();
    }
    
    /**
     * Splits the given String to String list by given split character.
     * 
     * @param str The String to be splited
     * @param split The split character
     * @return Splited String list
     */
    public static List<String> stringToList(String str, String split) {
    	if (isEmpty(str)) return null;
    	return Arrays.asList(str.split(split));
    }
    
    /**
     * Splits the given String to String array by given split character.
     * 
     * @param str The String to be splited
     * @param split The split character
     * @return Splited String array
     */
    public static String[] stringToArray(String str, String split) {
    	if (isEmpty(str)) return null;
    	return str.split(split);
    }
    
    /**
     * Concats the given String list to String by given connector character.
     * The default connector is comma (,) character.
     * 
     * @param str The String list to be concatted
     * @param connector The connector character
     * @return Concatted String
     */
    public static String listToString(List<String> strings, String... connector) {
    	if (isEmpty(strings)) return EMPTY;
    	
    	StringBuilder s = new StringBuilder();
    	int size = strings.size();
    	String c = (connector.length == 0 ? COMMA : connector[0]);
    	for (int i = 0; i < size; i++) {
    		s.append(strings.get(i));
    		if (i < size - 1) s.append(c);
    	}
    	return s.toString();
    }
    
    /**
     * Concats the given String list to String by given connector character.
     * The aliasMap is the String list's alias mapping table.
     * The default connector is comma (,) character.
     * This method generally be used to concat the SELECT columns SQL.
     * 
     * @param str The String list to be concatted
     * @param aliasMap The String list's alias mapping table
     * @param connector The connector character
     * @return Concatted String
     */
    public static String listToString(List<String> strings, Map<String, String> aliasMap, String... connector) {
    	if (isEmpty(strings)) return EMPTY;
    	if (aliasMap == null) aliasMap = new HashMap<String, String>();
    	
    	StringBuilder s = new StringBuilder();
    	int size = strings.size();
    	String c = (connector.length == 0 ? COMMA : connector[0]);
    	String str = null;
    	for (int i = 0; i < size; i++) {
    		str = strings.get(i);
    		if (aliasMap.containsKey(str)) {
    			s.append(aliasMap.get(str)).append(" AS \"").append(str).append("\"");
    		} else {
    			s.append(str);
    		}
    		if (i < size - 1) s.append(c);
    	}
    	return s.toString();
    }
    
    /**
     * Concats the given String array to String by given connector character.
     * The default connector is comma (,) character.
     * 
     * @param str The String array to be concatted
     * @param connector The connector character
     * @return Concatted String
     */
    public static String arrayToString(String[] strings, String... connector) {
    	if (isEmpty(strings)) return EMPTY;
    	
    	StringBuilder s = new StringBuilder();
    	int length = strings.length;
    	String c = (connector.length == 0 ? COMMA : connector[0]);
    	for (int i = 0; i < length; i++) {
    		s.append(strings[i]);
    		if (i < length - 1) s.append(c);
    	}
    	return s.toString();
    }
    
    /**
     * Concats the given String array.
     * 
     * @param strings The concatted String array
     * @return Concatted String
     */
    public static String concatStr(String... strings) {
    	StringBuffer s = new StringBuffer();
    	for (String str : strings) {
    		s.append(str);
    	}
    	return s.toString();
    }
    
    /**
     * Parses the given Object to int.
     * 
     * @param o The Object to be parsed
     * @return Parsed int value
     */
    public static int parseInt(Object o) {
    	if (o != null) {
    		String s = trimToNull(o.toString());
    		if (s != null) {
    			try { return Integer.parseInt(s); } 
    			catch (NumberFormatException e) {}
    		}
    	}
    	return 0;
    }
    
    /**
     * Parses the given Object to long.
     * 
     * @param o The Object to be parsed
     * @return Parsed long value
     */
    public static long parseLong(Object o) {
    	if (o != null) {
    		String s = trimToNull(o.toString());
    		if (s != null) {
    			try { return Long.parseLong(s); } 
    			catch (NumberFormatException e) {}
    		}
    	}
    	return 0L;
    }
    
    /**
     * Parses the given Object to float.
     * 
     * @param o The Object to be parsed
     * @return Parsed float value
     */
    public static float parseFloat(Object o) {
    	if (o != null) {
    		String s = trimToNull(o.toString());
    		if (s != null) {
    			try { return Float.parseFloat(s); } 
    			catch (NumberFormatException e) {}
    		}
    	}
    	return 0.0F;
    }
    
    /**
     * Parses the given Object to double.
     * 
     * @param o The Object to be parsed
     * @return Parsed double value
     */
    public static double parseDouble(Object o) {
    	if (o != null) {
    		String s = trimToNull(o.toString());
    		if (s != null) {
    			try { return Double.parseDouble(s); } 
    			catch (NumberFormatException e) {}
    		}
    	}
    	return 0.0;
    }
    
    /**
     * Capitalizes a String changing the first letter to title case.
     * No other letters are changed.
     * 
     * @param str The String to capitalize, may be null
     * @return The capitalized String, null if null String input
     */
    public static String capitalize(String str) {
		int length;
		if (str == null || (length = str.length()) == 0) return str;
		return new StringBuffer(length)
			.append(Character.toTitleCase(str.charAt(0)))
			.append(str.substring(1)).toString();
    }

    /**
     * Uncapitalizes a String changing the first letter to title case.
     * No other letters are changed.
     * 
     * @param str The String to uncapitalize, may be null
     * @return The uncapitalized String, null if null String input
     */
    public static String uncapitalize(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) return str;
		return new StringBuffer(length)
			.append(Character.toLowerCase(str.charAt(0)))
			.append(str.substring(1)).toString();
    }
    
    /**
     * Gets the field name from the Get/Set method.
     * 
     * @param str The Get/Set method name
     * @return The filed name
     */
    public static String fetchFieldName(String str) {
    	if (str == null || str.trim().length() <= 3) return EMPTY;
    	return uncapitalize(str.trim().substring(3));
    }
    
    /**
     * Judges the Get method.
     * 
     * @param str The judged method name
     * @return true if the method is a get method
     */
    public static boolean isGetMethod(String str) {
        return str != null && str.startsWith(GET);
    }
    
    /**
     * Judges the Set method.
     * 
     * @param str The judged method name
     * @return true if the method is a set method
     */
    public static boolean isSetMethod(String str) {
        return str != null && str.startsWith(SET);
    }
    
    /**
     * Truncates the given String to the given max length.
     * 
     * @param str The String to be truncated
     * @param maxLength The max length to be truncated
     * @return Truncated String
     */
    public static String truncateStr(String str, int maxLength) {
		if (str != null && str.length() > maxLength) {
			return str.substring(0, maxLength) + TRUNCATE_FILLING;
		}
    	return str;
    }
    
    /**
     * Truncates the given String util the given suffix.
     * The given suffix is excluded.
     * 
     * @param str The String to be truncated
     * @param subffix The suffix to be truncated
     * @return Truncated String
     */
    public static String truncateStr(String str, String subffix) {
		return str.substring(0, str.indexOf(subffix));
	}
    
    /**
     * Judges the same Objects.
     * 
     * @param o1 The judged Object o1
     * @param o2 The judged Object o2
     * @return true if the given two Objects is same
     */
    public static boolean isSame(Object o1, Object o2) {
		String s1 = nullToEmpty(o1);
		String s2 = nullToEmpty(o2);
		return s1.equals(s2);
	}

    /**
     * Compares two Strings, returning true if they are equal.
     * 
     * @param one The first String
     * @param two The second String
     * @return true(if they are equal, case sensitive, or both are null)
     */
    public static boolean equals(String one, String two) {
        return (one == null ? two == null : one.equals(two));
    }

    /**
     * Compares two Strings, returning true if they are equal ignoring the case.
     * 
     * @param one The first String
     * @param two The second String
     * @return true(if they are equal, case insensitive, or both are null)
     */
    public static boolean equalsIgnoreCase(String one, String two) {
        return (one == null ? two == null : one.equalsIgnoreCase(two));
    }

    /**
     * Checks if the String contains only unicode letters.
     *
     * @param str The String to check
     * @return true(if only contains letters, and is not null)
     */
    public static boolean isAlpha(String str) {
        if (str == null) {
            return false;
        }
        int strLength = str.length();
        for (int i = 0; i < strLength; i++) {
            if (Character.isLetter(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the String contains only digits.
     * @param str The String to check
     * @return true(if only contains digits, and is not null)
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int strLength = str.length();
        for (int i = 0; i < strLength; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the String contains only unicode letters or digits.
     * 
     * @param str The String to check
     * @return true(if only contains unicode letters or digits, and is not null)
     */
    public static boolean isAlphanumeric(String str) {
        if (str == null) {
            return false;
        }
        int strLength = str.length();
        for (int i = 0; i < strLength; i++) {
            if (Character.isLetterOrDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes control characters (char <= 32) from both ends of the String.
     * Returning null if the String is null.
     * 
     * @param str The String to be trimmed
     * @return The trimmed String
     */
    public static String trim(String str) {
        return (str == null ? null : str.trim());
    }

    /**
     * Converts null to empty String if the Object is null..
     *
     * @param value The Object to be converted
     * @return The converted String
     */
    public static String nullToEmpty(Object value) {
        return (value == null ? EMPTY : value.toString());
    }

    /**
     * Gets the leftmost len characters of a String.
     *
     * @param str The String to get the leftmost characters from.
     * @param len The lenght of the required String, must be zero or positive
     * @return The leftmost len characters
     */
    public static String left(String str, int len) {
        if (str == null) return null;
        if (len < 0) return EMPTY;
        if (str.length() < len) {
            return str;
        } else {
            return str.substring(0, len);    
        }
    }

    /**
     * Gets the rightmost len characters of a String.
     *
     * @param str The String to get the rightmost characters from.
     * @param len The lenght of the required String, must be zero or positive
     * @return The rightmost len characters
     */
    public static String right(String str, int len) {
        if (str == null) return null;
        if (len < 0) return EMPTY;
        if (str.length() < len) {
            return str;
        } else {
            return str.substring(str.length() - len);
        }
    }
    
    /**
     * 给指定字符串补充指定长度的半角空格字符.
     * 
     * @param content 待补充的字符串
     * @param totalLength 补充长度
     * @return 补充后的字符串
     */
    public static String addSpace(String content, int totalLength) {
        String space = "                  ";
		if (totalLength < 1) {
			return content;
		}
		if ((null == content) || (EMPTY.equals(content))) {
			return space;
		}
		if (totalLength < content.length()) {
			return content;
		}
		content += space.substring(0, totalLength - content.length());
		return content;
    }
    
    /**
     * Gets the start time at the given date.
	 * 
	 * @param d The given date
	 * @return The start time
	 */
    public static Date getMinTime(Date d) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(d);
    	c.set(Calendar.HOUR, 0);
    	c.set(Calendar.MINUTE, 0);
    	c.set(Calendar.SECOND, 0);
    	return c.getTime();
    }
    
    /**
     * Gets the end time at the given date.
	 * 
	 * @param d The given date
	 * @return The end time
	 */
    public static Date getMaxTime(Date d) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(d);
    	c.add(Calendar.DAY_OF_MONTH, 1);
    	c.add(Calendar.SECOND, -1);
    	return c.getTime();
    }
    
    /**
     * Calculates the time difference by given two dates.
     * The time difference unit is seconds.
     * 
     * @param d1 The given date d1
     * @param d2 The given date d2
     * @return Calculated the time difference
     */
    public static long dateDifference(Date d1, Date d2) {
    	return (d2.getTime() - d1.getTime()) / 1000;
    }
    
    /**
     * Converts the given binary String to byte.
     * 
     * @param s The converted binary String
     * @return Converted byte value
     */
    public static byte stringToByte(String s) {
		try { Integer.parseInt(s); } catch (Exception e) { return 0; }
		
		char c;
		int m = 0;
		for (int i = 0, len = s.length(); i < len; i++) {
			c = s.charAt(i);
			if (c == '1') {
				m += (1 << (len - i - 1));
			}
		}
		return (byte) m;
	}
    
    /**
     * Converts the given byte to binary String.
     * 
     * @param b The converted byte
     * @param len The converted String length
     * @return String Converted String
     */
    public static String byteToString(byte b, int len) {
        int i = b & 0xFF;
        return toBinaryInt(i, len);
    }
    
    /**
     * Converts the given int to binary String.
     * 
     * @param i The converted int value
     * @param len The converted String length
     * @return Converted binary String
     */
    private static String toBinaryInt(int i, int len) {
		StringBuilder s = new StringBuilder();
		for (int j = 0; j < len; j++) {
			s.append("0");
		}
		DecimalFormat df = new DecimalFormat(s.toString());
		int out = Integer.parseInt(Integer.toBinaryString(i));
		return df.format(out);
	}
    
    /**
     * Prints the given exception's stack track.
	 * 
	 * @param e The given exception
	 * @return The exception stack track
	 */
	public static String printStackTrack(Exception e) {
		StringBuffer errSb = new StringBuffer();
		errSb.append(e.toString());
		errSb.append("\r\n");

		StackTraceElement[] ee = e.getStackTrace();
		for (int i = 0; i < ee.length; i++) {
			errSb.append("\tat ");
			errSb.append(ee[i].toString());
			errSb.append("\r\n");
		}
		return errSb.toString();
	}
}
