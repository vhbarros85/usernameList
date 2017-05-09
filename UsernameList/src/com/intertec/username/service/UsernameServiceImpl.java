package com.intertec.username.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.intertec.username.domain.Result;
import com.intertec.username.exception.InternalException;
import com.intertec.username.exception.InvalidUsernameException;
import com.intertec.username.persistence.UsernamePersistence;

/**
 * Class responsible for the service regarding username check.
 * @author vitor barros
 *
 */
public class UsernameServiceImpl implements UsernameService {
	
	private static final Integer USERNAME_MINIMAL_LENGHT = 6;
	private static final Integer USERNAME_GENERATION_SIZE = 14;
	
	/**
	 * Method responsible for checking if a username is valid.
	 * @param username the username to be checked.
	 * @return A result with a boolean indicating if the username is valid and a list in case of not valid with suggestions.
	 * @throws InvalidUsernameException In case the username has less than 6 characters.
	 * @throws InternalException Exception throw in case of any internal problem.
	 */
	@Override
	public Result<Boolean,List<String>> checkUsername(String username) throws InternalException, InvalidUsernameException{
		if(username != null && username.length() < USERNAME_MINIMAL_LENGHT){
			throw new InvalidUsernameException("Username must have at least 6 characters long");
		}
		Result<Boolean, List<String>> restrictedWordsCheck =  containsRestrictedWord(username);
		if(restrictedWordsCheck.getFlag() ||  !isAvailableUsername(username)){
			 Random rnd = new Random();
			 List<String> suggestions = null;
			 String newUsername = username;
			 if(restrictedWordsCheck.getFlag()){
				 newUsername = generateCleanUsernames(newUsername,restrictedWordsCheck.getList());			 
			 }
			 //***************************************************************************************
			 // I could use an Callable and use thread here, check restriction on name by name generated
			 // and put a maximum timeout for improvement but I am going by document description.
			 //***************************************************************************************
			 int attempts = 0;
			 while(attempts<3){
				 suggestions = new ArrayList<String>();
				 for(int i = 0 ; i < USERNAME_GENERATION_SIZE ; i++){
					 String suggestedUsername = newUsername+rnd.nextInt(100);
					 if(isAvailableUsername(suggestedUsername) && !suggestions.contains(suggestedUsername)){
						 suggestions.add(suggestedUsername);
					 }
				 }
				 if(suggestions.size() == USERNAME_GENERATION_SIZE){
					 break;
				 }else{
					 attempts++;
				 }
			 }
			return new Result<Boolean, List<String>>(false, suggestions);
		}
		return new Result<Boolean, List<String>>(true, null);
	}
	
	/**
	 * Internal method for reuse when need to check to see if the username has restricted words on RestrictedWordService. 
	 * @param username username to be checked
	 * @return the same result from RestrictedWordService
	 * @throws InternalException Exception throw in case of any internal problem.
	 */
	private Result<Boolean, List<String>> containsRestrictedWord(String username) throws InternalException{
		//****************************************************
		// If that project were on a container i would use CDI 
		//****************************************************
		RestrictedWordService rws = new RestrictedWordServiceImpl();
		Result<Boolean,List<String>> restrictedWordsCheck = rws.containsRestrictedWord(username);
		if(restrictedWordsCheck.getFlag()){
			return new Result<Boolean, List<String>>(true, restrictedWordsCheck.getList());
		}
		return new Result<Boolean, List<String>>(false,null);
	}
	
	/**
	 * Recursive method to generate usernames that are not using any restricted word.
	 * @param username the base username to generate the clean username.
	 * @param restrictedWordsList the result from the previous called restricted word service.
	 * @return String with the new username;
	 * @throws InternalException Exception throw in case of any internal problem.
	 */
	protected String generateCleanUsernames(String username, List<String> restrictedWordsList ) throws InternalException{		 		 
		 for(String restrictedWord : restrictedWordsList){
			 //***********************************************************************************************
			 // I created a simple algorithm to remove half the restricted letter for the username suggestion
			 // I made this in case of a person that would use the entire restricted word as username.
			 //***********************************************************************************************
			  username = username.replace(restrictedWord.substring(0, restrictedWord.length() / 2), "");				 
		 }
		 Result<Boolean, List<String>> newCheck = containsRestrictedWord(username);
		 if(newCheck.getFlag()){
			 username = generateCleanUsernames(username,newCheck.getList());
		 }
		 return username;
	}
	
	/**
	 * Internal method for reuse when need to check to see if the username is available. 
	 * @param username the username to be checked.
	 * @return flag indicating if the username is available
	 * @throws InternalException Exception throw in case of any internal problem.
	 */
	protected boolean isAvailableUsername(String username) throws InternalException{
		UsernamePersistence up = new UsernamePersistence();
		Set<String> usernameCollection = up.retrieveCollection();
		if(usernameCollection.contains(username)){
			return false;
		}
		return true;
	}	
	
	
	
}
