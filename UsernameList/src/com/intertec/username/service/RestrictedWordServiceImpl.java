package com.intertec.username.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.intertec.username.domain.Result;
import com.intertec.username.exception.InternalException;
import com.intertec.username.persistence.RestrictedWordsPersistence;

/**
 * Class responsible for the services involving restricted words.
 * @author vitor barros
 *
 */
public class RestrictedWordServiceImpl implements RestrictedWordService {
	
	/**
	 * Method responsible for checking if the word contains a restricted word.
	 * @param word the word to be checked.
	 * @return A result with a boolean indicating if the word has restricted words and a list with the restricted words founded.
	 * @throws InternalException Exception throw in case of any internal problem. 
	 */
	public Result<Boolean,List<String>> containsRestrictedWord(String word) throws InternalException{
		RestrictedWordsPersistence rwp = new RestrictedWordsPersistence();
		Set<String> restrictedWordsCollection = rwp.retrieveCollection();
		boolean hasRestrictedWords = false;
		List<String> containedRestrictedWords = new ArrayList<String>();
		for(String wordOnList : restrictedWordsCollection){
			if(StringUtils.containsIgnoreCase(word, wordOnList)){
				//**************************************************************************************
				// That way i get exactly the word the username typed without concerning case.
				//**************************************************************************************
				hasRestrictedWords = true;
				int begin = word.toLowerCase().indexOf(wordOnList.toLowerCase());
				containedRestrictedWords.add(word.substring(begin, begin+wordOnList.length()));
			}
		}
		return new Result<Boolean, List<String>>(hasRestrictedWords, containedRestrictedWords);
	}
	
	/**
	 * Method responsible for adding a restricted word in the dictionary.
	 * @param word the word to be added to the dictionary.
	 * @throws InternalException Exception throw in case of any internal problem. 
	 */
	public void addRestrictedWord(String word) throws InternalException{
		RestrictedWordsPersistence rwp = new RestrictedWordsPersistence();
		rwp.addItem(word);
	}
	
}
