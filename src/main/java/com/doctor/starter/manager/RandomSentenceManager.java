package com.doctor.starter.manager;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("randomSentenceManager")
public class RandomSentenceManager {
	private final String[] sentences = { "I always rip out the last page of a book.Then it doesn't have to end.I hate endings.",
			"I'm the doctor,I'm  a time lord,I from planet Gallifrey by constellation Kasterborous.I'm 903 years old,and I might save your life you all 6 billion people on the planet below.",
			"We are not just fight with the flood, we are still fighting with the time, and I am going to win!",
			"I walked away from the Last Great Time War." };

	private Random random = new Random();

	public String randomString() {
		return sentences[random.nextInt(sentences.length)];
	}

	public String[] split(String str) {
		if (StringUtils.isBlank(str)) {
			return new String[] {};
		}

		return StringUtils.split(str);
	}
}
