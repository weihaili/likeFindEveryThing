package cn.org.kkl;

public class StringUtils {
	
	/**
	 * @param str :need to determine the string
	 * @return true: str ==null or str.length()=0;
	 * 		   false: str!=null and str.length()!=0
	 */
	public static boolean isEmptyStr(String str){
		return str==null || str.isEmpty()?true:false;
	}

}
