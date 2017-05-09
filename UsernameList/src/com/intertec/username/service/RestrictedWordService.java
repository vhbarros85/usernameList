package com.intertec.username.service;

import java.util.List;

import com.intertec.username.domain.Result;
import com.intertec.username.exception.InternalException;

/**
 * Interface for the restricted word service.
 * @author vitor barros
 *
 */
public interface RestrictedWordService {
	/**
	 * Signature for the method responsible for checking if the word contains a restricted word.
	 * @param word the word to be checked.
	 * @return A result with a boolean indicating if the word has restricted words and a list with the restricted words founded.
	 * @throws InternalException Exception throw in case of any internal problem. 
	 */
	public Result<Boolean,List<String>> containsRestrictedWord(String word) throws InternalException;
	
	/**
	 * Signature for the method responsible for adding a restricted word in the dictionary.
	 * @param word the word to be added to the dictionary.
	 * @throws InternalException Exception throw in case of any internal problem. 
	 */
	public void addRestrictedWord(String word) throws InternalException;
}
