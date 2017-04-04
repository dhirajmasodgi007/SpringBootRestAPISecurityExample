package com.example;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.spring.security.restful.springboot.model.WordCount;

public class SpringRootRestApiSecurityExampleApplicationClient {

	
	 public static final String REST_SERVICE_URI = "http://localhost:8080/counter-api";
	 
	 
	 
	 /* GET */
	    @SuppressWarnings("unchecked")
	    private static void findWordCount(){
	        System.out.println("Begin findWordCount API");
	          
	        RestTemplate restTemplate = new RestTemplate();
	        List<WordCount> wordCountList = restTemplate.getForObject(REST_SERVICE_URI+"/search/" + "[" + "Duis,Sed,Donec,Augue,Pellentesque,123" + "\\]", List.class);
	          
	        if(null != wordCountList && wordCountList.size() > 0)
	        {
	            for(WordCount wc : wordCountList)
	            {
	                System.out.println("Word= " + wc.getWord() +", Count=" + wc.getCount()+ "\n");;
	            }
	        }
	       
	    }
	    
	    
	    /* GET */
	    @SuppressWarnings("unchecked")
	    private static void topWordCount(){
	        System.out.println("Begin topWordCount API");
	          
	        RestTemplate restTemplate = new RestTemplate();
	        /*if (needsProxy()) {
	            System.setProperty("http.proxyHost", getProxyHost());
	            System.setProperty"http.proxyPort", getProxyPort());
	        } */
	        
	        List<WordCount> wordCountList = restTemplate.getForObject(REST_SERVICE_URI+"/top/5", List.class);
	          
	        if(null != wordCountList && wordCountList.size() > 0)
	        {
	            for(WordCount wc : wordCountList)
	            {
	                System.out.println("Word= " + wc.getWord() +", Count=" + wc.getCount()+ "\n");;
	            }
	        }
	       
	    }
	 
	
	    
	   /* private static HttpHeaders getHeaders(){
	        String plainCredentials="abcde:fghij";
	        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
	         
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "Basic " + base64Credentials);
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        return headers;
	    }*/
	    
	public static void main(String[] args) {
	
		//	topWordCount();
			findWordCount();

	}

}
