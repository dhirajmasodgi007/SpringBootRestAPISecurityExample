package com.spring.security.restful.springboot.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.yaml.snakeyaml.constructor.Constructor;

import com.spring.security.restful.springboot.model.WordCount;

import liquibase.util.csv.opencsv.CSVReader;
import liquibase.util.csv.opencsv.CSVWriter;

/* This message converter is responsible for converting our response into csv format. This class has to override the AbstractHttpMessageConverter and
 * its mandatory to ovverride the 3 methods. Always follow this practice to avoid any issues or unexpected results. */

@Configuration("csvMessageConverter")
public class CSVMessageConverter extends AbstractHttpMessageConverter<List<WordCount>> {
	
	
	public static final MediaType MEDIA_TYPE = new MediaType("text", "csv", Charset.forName("utf-8"));

	public CSVMessageConverter() {
	    super(MEDIA_TYPE);
	}
	
	@Override
	protected boolean supports(Class<?> clazz) {
	    return true;   //For custom message converter to work its mandatory to override this method and return true. If its false the converter will not work.
	}

	

	@Override
	protected List<WordCount> readInternal(Class<? extends List<WordCount>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
	 
		List<WordCount> modList = new ArrayList<>();
	    return modList;
	}

	@Override
	protected void writeInternal(List<WordCount> message, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException
	{
	    try (final Writer wri = new OutputStreamWriter(outputMessage.getBody()))
	    {
	        final CSVWriter beanWriter = new CSVWriter(wri, CSVWriter.DEFAULT_SEPARATOR);

	        HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders.add("Content-Type", "text/csv;charset=utf-8");
	        
		    if (null != message)
		    {
		    	if (message.size() > 0)
		    	{
		    		for (WordCount wc : message)
		    		{
		    		  String[] word = new String []{wc.getWord(), Integer.toString(wc.getCount())};
		    		  beanWriter.writeNext(word);
		    		}
		    	}
		    }
	        beanWriter.flush();
	        beanWriter.close();
	    }
	}

}
