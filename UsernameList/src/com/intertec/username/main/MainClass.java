package com.intertec.username.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.intertec.username.domain.Result;
import com.intertec.username.exception.InternalException;
import com.intertec.username.exception.InvalidUsernameException;
import com.intertec.username.service.UsernameService;
import com.intertec.username.service.UsernameServiceImpl;

public class MainClass {
	
	/**
	 * The names [peterParker, johnDoe, maryJane] already exists and it has the restricted words [cannabis, damn, crack, drunk, abuse, grass]
	 * @param args
	 * @throws InternalException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InternalException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter username:");
        String username = br.readLine();	
		UsernameService us = new UsernameServiceImpl();
		Result<Boolean, List<String>> result = null;
		try {
			result = us.checkUsername(username);
			if( result.getList() != null ){
				List<String> resultList = result.getList();
				System.out.println("User is invalid, suggestions : ");
				for(String suggestion : resultList){
					System.out.println(suggestion);
				}
			}else{
				System.out.println("User is Valid.");
			}
		} catch (InvalidUsernameException e) {
			System.out.print("Username must have at least 6 characters.");
		}
		
		
	}

}
