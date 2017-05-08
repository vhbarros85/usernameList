import java.util.List;

import com.intertec.username.domain.Result;
import com.intertec.username.service.UsernameService;
import com.intertec.username.service.UsernameServiceImpl;

public class teste {

	public static void main(String[] args) {
		UsernameService us = new UsernameServiceImpl();
		Result<Boolean, List<String>> result = us.checkUsername("crackVictordrunk");
		System.out.println(result);
		
	}

}
