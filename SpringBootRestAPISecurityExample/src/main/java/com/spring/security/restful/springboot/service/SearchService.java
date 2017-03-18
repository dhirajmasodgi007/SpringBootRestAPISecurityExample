package com.spring.security.restful.springboot.service;

import java.util.List;

import com.spring.security.restful.springboot.model.WordCount;

/*
 * Interface to declare the methods for both operations.
 * 
 * Task 1: Search the following texts, which will return the counts respectively.  
As a user, I will be able to execute the following curl command and returning results in the json format:  
Sample Request > curl http://host/counter-api/search -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" d’{“searchText”:[“Duis”, “Sed”, “Donec”, “Augue”, “Pellentesque”, “123”]}’ -H"ContentType: application/json" –X POST  
Result in JSON: > {"counts": [{"Duis": 11}, {"Sed": 16}, {"Donec": 8}, {"Augue": 7}, {"Pellentesque": 6}, {"123": 0}]}  


Note: Basic authorization header is using Base64 format with UTF-8 CharSet  
Task 2: Provide the top 20 (as Path Variable) Texts, which has the highest counts in the Sample Paragraphs provided.  
As a user, I will be able to execute the following curl command and expecting result in csv format. I will be able to put 10, 20, 30 or 5 as the top listing.  
Sample Request > curl http://host/counter-api/top/20 -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" H”Accept: text/csv”  

 * 
 */


public interface SearchService {
	
	
	public List<WordCount> wordCount (List<String> wordList);
	
	public List<WordCount> wordCountTopNumber (int top);

}
