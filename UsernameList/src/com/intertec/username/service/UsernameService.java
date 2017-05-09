package com.intertec.username.service;

import java.util.List;

import com.intertec.username.domain.Result;
import com.intertec.username.exception.InternalException;
import com.intertec.username.exception.InvalidUsernameException;

/**
 * Interface for the username Service
 * @author vitor barros
 *
 */
public interface UsernameService {
	/**
	 * Signature of the method responsible for checking if a username is valid.
	 * @param username the username to be checked.
	 * @return A result with a boolean indicating if the username is valid and a list in case of not valid with suggestions.
	 * @throws InvalidUsernameException In case the username has less than 6 characters.
	 * @throws InternalException Exception throw in case of any internal problem.
	 */
	public Result<Boolean,List<String>> checkUsername(String username) throws InternalException, InvalidUsernameException;
	
}
