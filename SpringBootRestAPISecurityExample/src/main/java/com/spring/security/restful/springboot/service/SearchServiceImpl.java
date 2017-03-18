package com.spring.security.restful.springboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.spring.security.restful.springboot.model.WordCount;

/*
 * Main implementation to get the expected results. 
 * @Service annotation is essential here and the value used should match exactly to the variable declaration mentioned in controller class.
 * 
 */

@Service("searchService")
public class SearchServiceImpl implements SearchService {

	
	private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
	
	
	
	@Override
	public List<WordCount> wordCount(List<String> wordList)
	{
		logger.info("begin wordCount.");
		List<WordCount> wordCountList = new ArrayList<WordCount>();
		// TODO Auto-generated method stub
		int count = 0;
		logger.info("\n words to be searched are ]" + wordList.toString() + "[");
		
			for (String str : wordList)
			{	
				
				Pattern p = Pattern.compile(str);
				Matcher m = p.matcher( paragraph );
				while (m.find())
				{
				    count++;
				}
				WordCount wordCount = new WordCount(str, count);
				logger.info("\n  word to be searched is=]" + str + "[size]" + count +"[" );
				count =0;
				
				wordCountList.add(wordCount);
		}
		
			if (wordCountList.size() > 0)
			{	
				logger.info("wordCountList size=]" + wordCountList.size() + "[");
			logger.info("end wordCount.");
			return wordCountList;
			}
			else
			{
				logger.info("wordCountList size is zero.");
				return null;
			}
	}
	
	
	

	@Override
	public List<WordCount> wordCountTopNumber(int top) {
		
		logger.info("begin wordCountTopNumber");
		
		String [] strArray = paragraph.split("\\W+"); // creates array of words from paragraph.
		
		
		logger.info("list of words before duplicate removal found are ");
		for (int i =0 ; i < strArray.length ; i++)
		{
			logger.info( strArray[i] );
		}
		logger.info("\n");
		
		// Below code remove duplicates
		Set<String> uniqueWordSet = new HashSet<String>(Arrays.asList(strArray));
		String [] uniqueWordArray = uniqueWordSet.toArray(new String[uniqueWordSet.size()]);
		
		logger.info("list of words found are ");
		for (int i =0 ; i < uniqueWordArray.length ; i++)
		{
			logger.info("\n"+ uniqueWordArray[i] );
		}
		logger.info("\n");
		
		
		List<WordCount> wordCountList = new ArrayList<>();
		List<WordCount> topNumberWordCountList = new ArrayList<>();
		int count = 0;
		if (null != uniqueWordArray)
		{
			logger.info("strArray is ]=" + strArray.toString() + "[");
			for (String str : strArray)
			{	
				
				Pattern p = Pattern.compile("\\b" + str + "\\b");    // this regex searches for whole word only. it ignores contains.
				Matcher m = p.matcher( paragraph );
				while (m.find())
				{
				    count++;
				}
				WordCount wordCount = new WordCount(str, count);
				logger.info("\n  Next word added is=]" + str + "[size]" + count +"[" );
				count =0;
				
				wordCountList.add(wordCount);
			}
		}		
		
			HashSet<WordCount> wordCountSet = new HashSet<>();
			for(WordCount pr:wordCountList)
			{
	            wordCountSet.add(pr);
	        }
			
			//Below code set in descending order on basis of word count. WordCount implements comparable interface and overrides comapreTo() on basis of wordCount.
			wordCountList = new ArrayList<WordCount>(wordCountSet);
			logger.info("the list is sorted in descending order.");
			Collections.sort(wordCountList);
			
			
			logger.info("sorted list is ");
			for (int i =0 ; i < wordCountList.size() ; i++)
			{
				logger.info("\n"+ wordCountList.get(i).getWord() + "[ size]" +  wordCountList.get(i).getCount());
			}
			
		
			int counter = 0;
			if (wordCountList.size() > 0)
			{	
				logger.info("wordCountList size=]" + wordCountList.size() + "[");
				
				for(WordCount wordCount : wordCountList)
				{
					if (counter < top)   // here from the whole list will be taking top numbers for final result.
					{
						topNumberWordCountList.add(wordCount);
						counter++;
					}	
					else
						break;
				}
				
				logger.info("top" + top +" added to the list.");
				
					logger.info("end wordCountTopNumber.");
						return topNumberWordCountList;
			}
			else
			{
				logger.info("wordCountList size is zero.");
				return null;
			}
	}
	
	
	
	private static final String paragraph ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed suscipit metus,"
			+ " sit amet tristique purus. Etiam sit amet leo sollicitudin, tincidunt lectus vel, ultricies mauris. Donec ultrices lorem in est eleifend, "
			+ "et feugiat libero semper. Duis sodales gravida sapien eget efficitur. Ut mattis mollis blandit. Duis nec metus gravida, posuere dolor id, "
			+ "pretium urna. Aliquam vitae purus ex. Etiam vitae ipsum leo. Integer blandit, arcu eget commodo scelerisque, risus leo aliquet diam, in "
			+ "sagittis metus ex sed elit. Duis vel urna non est fringilla rutrum. Ut molestie sed risus in pharetra. Maecenas eget ante at nulla "
			+ "feugiat euismod. Suspendisse pharetra porttitor lacus non tristique. Vivamus varius posuere ligula. Nullam magna metus, elementum vel "
			+ "elementum eu, elementum non magna. Ut cursus arcu vel ligula mollis, in interdum velit maximus. Pellentesque arcu lorem, porttitor et "
			+ "quam vitae, imperdiet venenatis magna. Etiam imperdiet erat vel lectus rhoncus sollicitudin. Praesent at mi a est suscipit tempor sed eu "
			+ "diam. In hac habitasse platea dictumst. Morbi erat mi, iaculis id hendrerit a, sollicitudin et ligula. Vivamus justo nibh, cursus at ultricies "
			+ "sed, varius iaculis enim. Donec consequat luctus sapien, quis aliquam ante tristique sit amet. Pellentesque accumsan sollicitudin mi a blandit. "
			+ "Donec ac dui bibendum, pharetra nulla vitae, iaculis purus. Donec fermentum porttitor mollis. Mauris cursus fringilla ex, eget ullamcorper ipsum "
			+ "lacinia in. Nam eget vehicula dui. In eget turpis convallis, ultrices neque vitae, interdum turpis. Nullam non aliquam sapien, eget volutpat elit. "
			+ "Cras pharetra ex a orci faucibus tristique at ullamcorper nibh. Proin nec lacinia ante, eu rutrum sem. Curabitur id libero purus. Vivamus vel velit turpis. "
			+ "Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec at urna eget augue efficitur porttitor at eu elit. Fusce feugiat tempor nulla, at euismod "
			+ "lacus tincidunt sed. Curabitur ullamcorper dignissim nisl, eget iaculis orci vestibulum sed. Ut consectetur consectetur urna vestibulum ultricies. Maecenas "
			+ "non felis arcu. Fusce in tortor metus. Vestibulum vel felis ut lorem ultricies pretium quis ut metus. Aliquam erat volutpat. Praesent a lorem porttitor, "
			+ "venenatis nisl volutpat, placerat dui. Vivamus ut justo eu orci tincidunt malesuada. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam "
			+ "facilisis nulla vel leo pretium varius. Fusce eleifend tincidunt lacinia. Duis maximus, sapien ac fringilla pretium, augue leo aliquam ligula, quis rutrum "
			+ "leo sem vel magna. Duis commodo lobortis dui, ut rhoncus dolor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. "
			+ "Nam nec augue augue. Quisque eu orci arcu. Aliquam neque odio, eleifend a dolor sed, dapibus auctor justo. Aliquam sollicitudin arcu sit amet odio gravida, "
			+ "nec viverra nulla efficitur. Phasellus sed libero rutrum lacus sollicitudin mattis. Sed fermentum sapien ac dolor elementum, quis vehicula sem tempus. "
			+ "Etiam et orci non orci lobortis dictum id vitae massa. Aenean eu erat nulla. Sed posuere ullamcorper magna, tempor ultrices justo feugiat rhoncus. "
			+ "Cras fringilla ligula nec euismod tristique. Duis vitae enim eget augue consectetur ultricies. Nam laoreet sapien at dictum consectetur. Suspendisse "
			+ "tristique purus neque, ut blandit nunc tincidunt et. Duis pretium condimentum diam id viverra. Pellentesque sit amet dapibus eros, ac auctor lectus. "
			+ "Praesent eget tellus purus. Proin vel nisl sit amet orci laoreet faucibus eget eu nisi. Nulla id pharetra arcu. Lorem ipsum dolor sit amet, consectetur "
			+ "adipiscing elit. Vivamus ornare lectus eu metus venenatis, quis porttitor nibh convallis. Nulla nunc metus, tristique quis dui sed, interdum imperdiet nisl."
			+ " Vestibulum mattis tincidunt lacus, imperdiet mattis libero varius rhoncus. Nam in auctor nisl. Nunc tincidunt accumsan pulvinar. Class aptent taciti "
			+ "sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Mauris luctus scelerisque augue, vel finibus ligula semper vel. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nunc ultricies vel nunc eget mollis. Donec ligula felis, ultrices vel blandit ut, hendrerit vel turpis. Duis faucibus dapibus mi ac semper. Duis id tortor tempus augue euismod tempus. Integer vehicula velit ut leo blandit sagittis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc ut urna vel metus molestie venenatis nec non dui. Integer diam metus, aliquam a gravida et, varius id nulla. Nunc non porttitor ipsum. Aliquam sapien enim, eleifend nec nunc id, tempus tempus ex. Vivamus nec urna ornare, finibus leo at, posuere urna. Aenean est mi, porta ac gravida at, hendrerit quis elit. Quisque urna mauris, lobortis sit amet tortor eget, laoreet consectetur tortor. Suspendisse id imperdiet nisl, eget pellentesque tortor. Maecenas sit amet mi et ex ornare porta sollicitudin vitae tellus. Donec nulla lorem, imperdiet non sodales vitae, congue quis sapien. Quisque nec mattis lacus. Sed dapibus nisi nec libero ornare, in accumsan dolor porttitor. Praesent sodales commodo ultricies. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras id ipsum vestibulum, venenatis eros vitae, maximus magna. Mauris eu blandit tortor, condimentum eleifend dolor. Cras eu tellus feugiat, lobortis metus ac, consectetur orci. Phasellus bibendum tincidunt massa non venenatis. Nunc sed molestie metus, vel elementum tortor. Duis malesuada porta nisl ac molestie. In a tellus faucibus, convallis nunc nec, sodales lacus. Donec vulputate interdum massa sed posuere. In dapibus eu ligula at sodales. Sed facilisis a sem eget lobortis. Ut viverra ipsum dictum pharetra auctor. Duis tincidunt nulla sapien, sit amet facilisis ante rhoncus eu. Aliquam luctus dolor tortor, vitae interdum felis elementum eget. Nam mattis leo gravida ex elementum, id facilisis lacus ornare.";




}
