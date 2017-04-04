package com.example;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.spring.security.restful.springboot.model.WordCount;

public class SpringRootRestApiSecurityExampleApplicationClient {

	
	 public static final String REST_SERVICE_URI = "http://localhost:8080/counter-api";
	 
	 
	 
	 /* GET */
	    @SuppressWarnings("unchecked")
	    private static void findWordCountWithoutAuthentication(){
	        System.out.println("Begin findWordCount API");
	          
	        RestTemplate restTemplate = new RestTemplate();
	        List<WordCount> wordCountList = restTemplate.getForObject(REST_SERVICE_URI+"/search/"  + "Duis,Sed,Donec,Augue,Pellentesque,123" , List.class);
	          
	        if(null != wordCountList && wordCountList.size() > 0)
	        {
	            for(WordCount wc : wordCountList)
	            {
	                System.out.println("Word= " + wc.getWord() +", Count=" + wc.getCount()+ "\n");;
	            }
	        }
	       
	    }
	    
	    
	    private static HttpHeaders createHeaders(String username, String password){
	    	   return new HttpHeaders() {{
	    	         String auth = username + ":" + password;
	    	         byte[] encodedAuth = Base64.encodeBase64( 
	    	            auth.getBytes(Charset.forName("US-ASCII")) );
	    	         String authHeader = "Basic " + new String( encodedAuth );
	    	         set( "Authorization", authHeader );
	    	      }};
	    	}
	    
	    

		 /* GET */
		    @SuppressWarnings("unchecked")
		    private static void findWordCountWithAuthentication(){
		        System.out.println("Begin findWordCount API");
		          
		        RestTemplate restTemplate = new RestTemplate();
		   //     List<WordCount> wordCountList = restTemplate.getForObject(REST_SERVICE_URI+"/search/"  + "Duis,Sed,Donec,Augue,Pellentesque" , List.class);
		          
		        ResponseEntity<List> response = restTemplate.exchange(REST_SERVICE_URI+"/search/"  + "Duis,Sed,Donec,Augue,Pellentesque", HttpMethod.GET, new HttpEntity<Object>(createHeaders("abcde", "fghij")), List.class);
		        
		        System.out.println("findWordCountWithAuthentication result =" + response.getBody());
		       /* if(null != wordCountList && wordCountList.size() > 0)
		        {
		            for(WordCount wc : wordCountList)
		            {
		                System.out.println("Word= " + wc.getWord() +", Count=" + wc.getCount()+ "\n");;
		            }
		        }*/
		       
		    }
	    
	    
	    /* GET */
	    @SuppressWarnings("unchecked")
	    private static void topWordCountWithoutAuthentication(){
	        System.out.println("Begin topWordCount API");
	          
	        RestTemplate restTemplate = new RestTemplate();
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
	
		
		findWordCountWithAuthentication();
		
		/*  Below method are for without authentication */
			topWordCountWithoutAuthentication();
			findWordCountWithoutAuthentication();
			
			
			

	}

}
