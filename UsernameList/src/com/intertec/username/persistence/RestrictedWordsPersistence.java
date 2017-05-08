package com.intertec.username.persistence;

/**
 * Utility class to represent persistence of the restricted words.
 * @author vitor barros
 *
 */
public class RestrictedWordsPersistence extends CollectionPersistence{
	
	//Path to the file where it will be saved the restricted words collection
	private static final String RESTRICTED_WORDS_FILE_PATH = "restrictedwords.txt" ;
	
	public RestrictedWordsPersistence(){
		super(RESTRICTED_WORDS_FILE_PATH);
	}	
	
}
