package com.intertec.username.persistence;

/**
 * Utility class to represent persistence of the usernames.
 * @author vitor barros
 *
 */
public class UsernamePersistence extends CollectionPersistence {
	
	//Path to the file where it will be saved the username collection
	private static final String USERNAME_LIST_FILE_PATH = "usernameList.txt" ;
	
	public UsernamePersistence(){
		super(USERNAME_LIST_FILE_PATH);
	}
		
}
