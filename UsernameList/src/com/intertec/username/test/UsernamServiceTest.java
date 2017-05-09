package com.intertec.username.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.intertec.username.domain.Result;
import com.intertec.username.exception.InternalException;
import com.intertec.username.exception.InvalidUsernameException;
import com.intertec.username.service.UsernameService;
import com.intertec.username.service.UsernameServiceImpl;

public class UsernamServiceTest {

	@Test
	public void testAvailableUsername() {
		UsernameService usernameService = new UsernameServiceImpl();
		try {
			Result<Boolean, List<String>> result = usernameService.checkUsername("victorBarros");
			assertEquals(true,result.getFlag());
		} catch (InternalException | InvalidUsernameException e) {
			fail("Error");
		}
		
	}
	
	@Test
	public void testUsernameWithRestrictedWord() {
		UsernameService usernameService = new UsernameServiceImpl();
		try {
			Result<Boolean, List<String>> result1 = usernameService.checkUsername("DamnUser");
			assertEquals(false,result1.getFlag());
			assertNotNull(result1.getList());
			Result<Boolean, List<String>> result2 = usernameService.checkUsername("damnuser");
			assertEquals(false,result2.getFlag());
			assertNotNull(result2.getList());
			Result<Boolean, List<String>> result3 = usernameService.checkUsername("damncrack");
			assertEquals(false,result3.getFlag());
			assertNotNull(result3.getList());
		} catch (InternalException | InvalidUsernameException e) {
			fail("Error");
		}
		
	}
	
	@Test
	public void testUsernameAlreadyTaken() {
		UsernameService usernameService = new UsernameServiceImpl();
		try {
			Result<Boolean, List<String>> result1 = usernameService.checkUsername("johnDoe");
			assertEquals(false,result1.getFlag());
			assertNotNull(result1.getList());
		} catch (InternalException | InvalidUsernameException e) {
			fail("Error");
		}
		
	}
	
	@Test
	public void testInvalidUsername() {
		UsernameService usernameService = new UsernameServiceImpl();
		try {
			@SuppressWarnings("unused")
			Result<Boolean, List<String>> result1 = usernameService.checkUsername("vitor");
		} catch (InternalException | InvalidUsernameException e) {
			assertEquals(e.getClass(), InvalidUsernameException.class);
		}
		
	}

}
