package com.intertec.username.service;

import java.util.List;

import com.intertec.username.domain.Result;

public interface RestrictedWordService {
	public Result<Boolean,List<String>> containsRestrictedWord(String word);
	public void addRestrictedWord(String word);
}
