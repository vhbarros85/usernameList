package com.intertec.username.domain;

/**
 * Domain class for the result of the username valid check.
 * @author vitor barros
 *
 * @param <T1> flag to indicate if the username is valid
 * @param <T2> list with the suggested usernames.
 */
public class Result<T1, T2> {
	private T1 valid; 
    private T2 suggestionList; 

    public Result(T1 valid, T2 suggestionList) {
        this.valid = valid;
        this.suggestionList = suggestionList;
    }

	/**
	 * @return the valid
	 */
	public T1 getValid() {
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(T1 valid) {
		this.valid = valid;
	}

	/**
	 * @return the suggestionList
	 */
	public T2 getSuggestionList() {
		return suggestionList;
	}

	/**
	 * @param suggestionList the suggestionList to set
	 */
	public void setSuggestionList(T2 suggestionList) {
		this.suggestionList = suggestionList;
	}

    
}
