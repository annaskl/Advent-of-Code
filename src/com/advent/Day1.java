package com.advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

public class Day1 {
	private static final String FILENAME = "input/input-day1.txt";
	private final static Logger LOGGER = Logger.getLogger(Day1.class.getName());

	public static void main(String[] args) {
		CalibratorHelper calibrator = new CalibratorHelper();
		ArrayList<Integer> frequencies = calibrator.readFrequenciesFromFile(FILENAME);
		if (frequencies == null) {
			return;
		}
		int frequency = calibrator.countFrequencies(frequencies);
		LOGGER.info("Day 1, answer 1: " + frequency);

		int duplicatedFrequency = calibrator.findFirstDuplicateResult(frequencies);
		LOGGER.info("Day 1, answer 2: " + duplicatedFrequency);
	}

}

class CalibratorHelper {
	private final static Logger LOGGER = Logger.getLogger(CalibratorHelper.class.getName());


	public ArrayList<Integer> readFrequenciesFromFile(String filename) {
		Scanner s;
		try {
			s = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			LOGGER.severe("File not found");
			return null;
		}
		ArrayList<Integer> frequencies = new ArrayList<Integer>();
		while (s.hasNextInt()) {
			frequencies.add(s.nextInt());
		}
		s.close();
		return frequencies;
	}

	public int countFrequencies(ArrayList<Integer> frequencies) {
		int sum = 0;
		for (Integer frequency : frequencies) {
			sum += frequency;
		}
		return sum;
	}

	public Integer findFirstDuplicateResult(ArrayList<Integer> frequencies) {
		int sum = 0;
		Set<Integer> results = new HashSet<Integer>();
		while (true) {
			for (Integer frequency : frequencies) {
				sum += frequency;
				if (results.contains(sum)) {
					return sum;
				}
				results.add(sum);
			}
		}
	}
}