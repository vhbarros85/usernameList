package com.intertec.username.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.intertec.username.domain.Result;
import com.intertec.username.persistence.UsernamePersistence;

/**
 * Class with the logic for username check.
 * @author vitor barros
 *
 */
public class UsernameServiceImpl implements UsernameService {
	
	/**
	 * Method responsible for checking if a username is valid.
	 * @param username the username to be checked
	 * @return The result with the check of the username.
	 */
	@Override
	public Result<Boolean,List<String>> checkUsername(String username){
		UsernamePersistence up = new UsernamePersistence();
		Set<String> usernameCollection = up.retrieveCollection();
		RestrictedWordService rws = new RestrictedWordServiceImpl();
		Result<Boolean,List<String>> restrictedWordsCheck = rws.containsRestrictedWord(username);
		if(restrictedWordsCheck.getValid() || usernameCollection.contains(username)){
			List<String> suggestions = generateSuggestionUsernames(username,restrictedWordsCheck);
			return new Result<Boolean, List<String>>(false, suggestions);
		}
		return new Result<Boolean, List<String>>(true, null);
	}
	
	protected List<String> generateSuggestionUsernames(String username, Result<Boolean,List<String>> restrictedWordsCheck ){
		 RestrictedWordService rws = new RestrictedWordServiceImpl();
		 Random rnd = new Random();
		 List<String> suggestions = new ArrayList<String>();
		 String newUsername = username;
		 if(restrictedWordsCheck.getValid()){
			 for(String restrictedWord : restrictedWordsCheck.getSuggestionList()){
				 
				 //I created from my own an algorithm to remove half the restricted letter for the username suggestion
				 // I made this in case of a person that would use the entire restricted word as username.
				 newUsername = newUsername.replace(restrictedWord.substring(0, restrictedWord.length() / 2), "");
				//TODO: VALIDAR SE O NOME JÁ EXISTE OU SE TEM NOVOS PALAVRAS RESTRITAS, TRATAMENTO DE EXCESSÃO E TESTES UNITARIOS
			 }
		 }
		 for(int i = 0 ; i < 14 ; i++){
			 suggestions.add(newUsername+rnd.nextInt(2));
			//TODO: VALIDAR SE O NOME JÁ EXISTE OU SE TEM NOVOS PALAVRAS RESTRITAS
		 }
		 
		return suggestions;
	}
	
}
