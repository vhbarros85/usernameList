package com.intertec.username.service;

import java.util.List;

import com.intertec.username.domain.Result;

/**
 * Interface for the Username Service
 * @author vitor barros
 *
 */
public interface UsernameService {
	/**
	 * Signature of the method responsible for checking if a username is valid.
	 * @param username the username to be checked
	 * @return The result with the check of the username.
	 */
	public Result<Boolean,List<String>> checkUsername(String username);
	
}
