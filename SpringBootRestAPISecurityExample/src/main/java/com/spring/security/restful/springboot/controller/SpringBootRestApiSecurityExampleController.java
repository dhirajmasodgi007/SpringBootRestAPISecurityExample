package com.spring.security.restful.springboot.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.restful.springboot.model.WordCount;
import com.spring.security.restful.springboot.service.SearchService;
import com.spring.security.restful.springboot.util.CSVMessageConverter;
import com.spring.security.restful.springboot.util.CustomErrorMessage;

/*******
 * 
 * @author dhirajm
 * 
 * @RestController combines @Controller and @ResponseBody, two annotations that results in web requests returning data rather than a view.
 *
 */


@RestController

@RequestMapping("/counter-api")
public class SpringBootRestApiSecurityExampleController {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootRestApiSecurityExampleController.class);
	
	@Autowired          //Helps to map the defined bean.
	private SearchService searchService;
	

	/*
	 * @PathVariable annotation is the easy way to extract the data from the rest URI and map it to the method argument.
	 * see below in method arguments.
	 */
	
	
	@RequestMapping(value="/search/[{comaseparatedwords}]" , method=RequestMethod.GET)
	public ResponseEntity<?> wordCount(@PathVariable("comaseparatedwords") List<String> words)
	{
		HttpEntity<String> request = new HttpEntity<String>(getHeaders());
		List<WordCount> wordList = new ArrayList<WordCount>();
		logger.info("list of words.]" + words.toString() + "[");
		
		
		
		
		if (null != words && words.size() > 0)
		{
			Pattern p = Pattern.compile("[^a-zA-Z ]");
			boolean b =  false;
			
			for(String word : words)
			{
				Matcher m = p.matcher(word);
				b = m.find();
				if (b)
					return new ResponseEntity<CustomErrorMessage> (new CustomErrorMessage("Request contain special characters which are not allowed.") , HttpStatus.BAD_REQUEST);
				else
					continue;
			}
			wordList = searchService.wordCount(words);
			
		}	
		else
			return new ResponseEntity<CustomErrorMessage> (new CustomErrorMessage("No words passed in URI request to be searched.") , HttpStatus.BAD_REQUEST);
		
		if (null != wordList && wordList.size() > 0)
			return new ResponseEntity<List<WordCount>> (wordList , HttpStatus.OK );
		else
			return new ResponseEntity<CustomErrorMessage> (new CustomErrorMessage("Words not found.") , HttpStatus.NOT_FOUND );
	}
	
	/* @ResponseBody annotation is used to map the response object in the response body. Once the response object is returned by the handler method, 
	 * messageConverter kicks in.
	 * 
	 */
	
	@RequestMapping(value="/top/{num}" , method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> wordCount(@PathVariable("num") int num)
	{
		HttpEntity<String> request = new HttpEntity<String>(getHeaders());
		List<WordCount> wordList = new ArrayList<WordCount>();
		logger.info(" top ]" + num + "[words with count expected in result.[");
		
		
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/csv;charset=utf-8");
	    
	    if(num < 0 || num == 0)
			return new ResponseEntity<CustomErrorMessage> (new CustomErrorMessage("Please enter valid number.") , HttpStatus.BAD_REQUEST );
		
		if (num > 0)
			wordList = searchService.wordCountTopNumber(num);
		else
			return new ResponseEntity<CustomErrorMessage> (new CustomErrorMessage("Please pass number to search top most found words with count.") , HttpStatus.NO_CONTENT );
		
		
		if (null != wordList && wordList.size() > 0)
			return new ResponseEntity<List<WordCount>> (wordList , HttpStatus.OK );
		else
			return new ResponseEntity<CustomErrorMessage> (new CustomErrorMessage("Top" + num + " not found") , HttpStatus.NOT_FOUND );
	}
	
	
	
	
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
    
    
    
	
}
