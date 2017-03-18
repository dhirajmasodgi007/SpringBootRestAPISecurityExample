package com.example;

import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.spring.security.restful.springboot.SpringBootRestApiSecurityExampleApplication;
import com.spring.security.restful.springboot.model.WordCount;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {SpringBootRestApiSecurityExampleApplication.class})
public class SpringRootRestApiSecurityExampleApplicationTests {
	
	
/*	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
*/
	
	
	@Test
	public void contextLoads() {
	}
	
	 public static final String REST_SERVICE_WORDCOUNT_URI = "http://localhost:8080";
	
	
	/*@Test
	public void findByContaining() throws Exception {

		this.mvc.perform(
				get("/SpringBootRestApiSecurity/counter-api/top/5"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("_embedded.cities", hasSize(3)));
	}
	*/
	//TODO :- NOT COMPLETED YET.
	private static final Logger logger = LoggerFactory.getLogger(SpringRootRestApiSecurityExampleApplicationTests.class);
	

	
	//@Autowired
    //private static TestRestTemplate restTemplate;
	
	
	
	/*
     * Add HTTP Authorization header, using Basic-Authentication header which uses base64 format to send user-credentials.
     * 
     */
    private static HttpHeaders getHeaders(){
        String plainCredentials="abcde:fghij";
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
         
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }
    
    
    private static HttpHeaders getCSVHeaders(){
        String plainCredentials="abcde:fghij";
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
         
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
       // headers.setAccept(Arrays.asList(new MediaType ("Content-type" , "text/csv")));
        headers.add("Content-Type", "text/csv;charset=utf-8");
        return headers;
    }
    
    @SuppressWarnings("unchecked")
    private static void testWordCount()
    {
    	
    	 RestTemplate restTemplate = new RestTemplate();
    	 HttpEntity <String> request = new HttpEntity<String>(getHeaders());
    	 if (null != restTemplate)
    	 { 
    		 ResponseEntity<List> response = (ResponseEntity) restTemplate.exchange(REST_SERVICE_WORDCOUNT_URI+ "/SpringBootRestApiSecurity/counter-api/search/[{Duis,Sed,Donec,Augue,Pellentesque,123}]", HttpMethod.GET, request ,List.class);
    		 List<WordCount> list = response.getBody();
    		 for(WordCount wc : list)
        		 System.out.println(wc.toString());
    	 }
    	 else
    		 logger.info("resttemplate is null.");
    	  
    	
    }
    
    @SuppressWarnings("unchecked")
    private static void testTopNumberCount()
    {
    	 RestTemplate restTemplate = new RestTemplate();
    	 HttpEntity <String> request = new HttpEntity<String>(getCSVHeaders());
    	 ResponseEntity<List<WordCount>> response = (ResponseEntity) restTemplate.exchange(REST_SERVICE_WORDCOUNT_URI + "/SpringBootRestApiSecurity/counter-api/top/5", HttpMethod.GET, request, List.class);
    	 List<WordCount> list = response.getBody();
    	 
    	 for(WordCount wc : list)
    		 System.out.println(wc.toString());  
          
    }
    
    public static void main(String args[])
    {
    	testTopNumberCount();
    	testWordCount();
        
        
        
    }

}
