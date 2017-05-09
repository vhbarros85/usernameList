package com.intertec.username.domain;

/**
 * Domain class for the result of the username and restricted words check.
 * @author vitor barros
 *
 * @param <T1> flag to indicate if true or false.
 * @param <T2> list with the suggested usernames or restricted words used.
 */
public class Result<T1, T2> {
	private T1 flag; 
    private T2 list; 

    public Result(T1 valid, T2 suggestionList) {
        this.flag = valid;
        this.list = suggestionList;
    }

	/**
	 * @return the flag
	 */
	public T1 getFlag() {
		return flag;
	}

	/**
	 * @param flag to set
	 */
	public void setFlag(T1 flag) {
		this.flag = flag;
	}

	/**
	 * @return the list
	 */
	public T2 getList() {
		return list;
	}

	/**
	 * @param the list to set
	 */
	public void setList(T2 list) {
		this.list = list;
	}

    
}
