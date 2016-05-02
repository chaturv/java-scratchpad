package com.chaturv.dictionary;

public class DictionaryUnravel {
	
	String[] words = new String[]{
					"Aeroplane",
					"Apple",
					"Bite",
					"Cat",
					"Minimum",
					"Monkey",
					"Role",
					"Roll"};
			
	public static void main(String[] args) {
		new DictionaryUnravel().unravel();
	}
	
	
	private void unravel() {
		char[][] wordsArrayView = new char[words.length][];
		int i = 0;
		for (String word : words) {			 			 
			 wordsArrayView[i] = word.toCharArray();			 
			 i++;
		}		
		
		
		for (int j = 0; j < wordsArrayView.length - 1; j++) {
			char[] current = wordsArrayView[j];
			char[] next = wordsArrayView[j + 1];
			
			int comparisonLength = current.length >= next.length ? next.length : current.length;
			
			for (int k = 0; k < comparisonLength; k++) {
				if (current[k] == next[k]) {
					continue;
				} else {
					System.out.println(current[k] + " > " + next[k]);
					break;
				}
				
			}
		}
				
	}

}
