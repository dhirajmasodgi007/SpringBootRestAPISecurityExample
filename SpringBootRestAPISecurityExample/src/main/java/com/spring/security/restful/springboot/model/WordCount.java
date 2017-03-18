package com.spring.security.restful.springboot.model;

/*
 * This class is created to store word and count in a single object. The class has implemented
 * comparable interface as has to sort the WordCount List object on the basis of count in descending order.
 * 
 * See the implementation of compareTo() method below. Its basically designed to set the list in descending order.
 * 
 * toString() method is implemented and can be used to log the details while debugging.
 */


public class WordCount  implements Comparable<WordCount>{
	
	private String word;
	private int count;
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public  WordCount(String word, int count) {
	  this.word = word;
	  this.count = count;
	}
	
	@Override
	public String toString() {
		return "WordCount [word=" + word + ", count=" + count + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordCount other = (WordCount) obj;
		if (count != other.count)
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(WordCount o) {
		// TODO Auto-generated method stub
		int compareCount = o.getCount();
		return compareCount - this.count;
		 
	}
	
	

}
