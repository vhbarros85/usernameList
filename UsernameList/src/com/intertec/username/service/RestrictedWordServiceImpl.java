package com.intertec.username.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.intertec.username.domain.Result;
import com.intertec.username.persistence.RestrictedWordsPersistence;

public class RestrictedWordServiceImpl implements RestrictedWordService {
	/**
	 * 
	 * @param username
	 * @return
	 */
	public Result<Boolean,List<String>> containsRestrictedWord(String word){
		RestrictedWordsPersistence rwp = new RestrictedWordsPersistence();
		Set<String> restrictedWordsCollection = rwp.retrieveCollection();
		boolean hasRestrictedWords = false;
		List<String> containedRestrictedWords = new ArrayList<String>();
		for(String wordOnList : restrictedWordsCollection){
			if(StringUtils.containsIgnoreCase(word, wordOnList)){
				hasRestrictedWords = true;
				containedRestrictedWords.add(wordOnList);
			}
		}
		return new Result<Boolean, List<String>>(hasRestrictedWords, containedRestrictedWords);
	}
	
	/**
	 * Method responsible for adding a new item to the restricted words
	 * @param word
	 */
	public void addRestrictedWord(String word){
		RestrictedWordsPersistence rwp = new RestrictedWordsPersistence();
		rwp.addItem(word);
	}
	
}
