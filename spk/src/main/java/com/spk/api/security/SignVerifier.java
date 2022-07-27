package com.spk.api.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

	/**
	 * <p>서버 인증 처리</p>
	 */
	@Component
	@RestController
	public class SignVerifier {
		private final Logger logger = LoggerFactory.getLogger(this.getClass());

	    private static String apiKey;
	    private static String secretKey;
	    private static String signatureAlgorithm;
	    
	    /**
	     * @param value API-key
	     */	    
	    @Value("${api-key}")
	    public void KeyVal1(String value) {
	    	apiKey = value;
	    }
	    
	    /**
	     * @param value secret-key
	     */	    
	    @Value("${secret-key}")
	    public void KeyVal2(String value) {
	    	secretKey = value;
	    }
	    
	    /**
	     * @param value 암호화 algorithm
	     */	    
	    @Value("${signature-algorithm}")
	    public void KeyVal3(String value) {
	    	signatureAlgorithm = value;
	    }
	    
		/**
		 * <p>인증 처리</p>
		 * <ul>
		 * 	<li>클라이언트로부터 요청 받은 처리인지 검증한다. </li>
		 * 	<li>HmacSHA256 기반 인증처리를 한다</li>
	     * </ul>
	     * @param signature 클라이언트로 부터 받은 key
	     * @return boolean
		 */	    
	    public boolean verifySignature(String signature) {

	        if (StringUtils.isBlank(signature)) {
	            return false;
	        }

	        String madeSignature = getHmacSignature();
	        return signature.trim().equals(madeSignature);
	    }
		
		/**
		 * 암호화로 생성한 key값 리턴
		 * 
		 * @return genKey
		 */	    
	    public String getHmacSignature() {
	    	String aKey = apiKey.toString();
	    	String sKey = secretKey.toString();
	    	String genKey = "";
	    	
	        try {
	        	genKey = HmacAndHex(aKey, sKey, signatureAlgorithm);
	        } catch (Exception ignored) {
	        	logger.error("Error occured processing Mac init - Exception : {} :" + ignored );
	        }	        
	        return genKey;
	    }
	    
		public String HmacAndHex(String aKey, String sKey, String Algorithms) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
			String genKey = "";
			try {
				//1. SecretKeySpec 클래스를 사용한 키 생성 
				SecretKeySpec apiKey = new SecretKeySpec(aKey.getBytes("utf-8"), Algorithms);
				//2. 지정된  MAC 알고리즘을 구현하는 Mac 객체를 작성
				Mac hasher = Mac.getInstance(Algorithms);
				//3. 키를 사용해 이 Mac 객체를 초기화
				hasher.init(apiKey);
				//4. 암호화 하려는 데이터의 바이트의 배열을 처리해 MAC 조작을 종료
				byte[] hash = hasher.doFinal(sKey.getBytes());
				//5. Hex Encode to String
				genKey = Base64.encodeBase64String(hash);
				
			} catch (Exception ignored) {	
				logger.error("Error occured processing Mac init - Exception : {} :" + ignored );
			}
			return genKey;
		}	    
	    
	}