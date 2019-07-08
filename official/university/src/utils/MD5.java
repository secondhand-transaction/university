package utils;
import java.math.BigInteger;
import java.security.MessageDigest;
 
public class MD5 {
	 /**
     * ���ַ���md5����(Сд��ĸ+����)
     *
     * @param str ����Ҫ���ܵ��ַ���
     * @return  MD5���ܺ���ַ���
     */
	
    public static String getMD5(String str) {
        try {           
            MessageDigest md = MessageDigest.getInstance("MD5");        
            md.update(str.getBytes());  
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }
    
    
    /**
     * ���ַ���md5����(��д��ĸ+����)
     *
     * @param str ����Ҫ���ܵ��ַ���
     * @return  MD5���ܺ���ַ���
     */
    
    public static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
 
        try {
            byte[] btInput = s.getBytes();
          
            MessageDigest mdInst = MessageDigest.getInstance("MD5");            
            mdInst.update(btInput);            
            byte[] md = mdInst.digest();          
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
}