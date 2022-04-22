package scenes.gamescene;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ScoreSaver {
	
	public static class ScoreComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer int1, Integer int2) {
			return -1*Integer.compare(int1.intValue(), int2.intValue());
		}
		
	}

	private ArrayList<Integer> scores;
	
	public ScoreSaver() {
		scores = new ArrayList<Integer>();
	}
	
	private void sortScores() {
		scores.sort(new ScoreComparator());
	}
	
	public ArrayList<Integer> getBestScores() {
		try {
			readFile("./resources/ScoreSave.txt");
		} catch (IOException e) {
			System.out.println("The file can't be read");
		}
		sortScores();
		ArrayList<Integer> bestScores = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			bestScores.add(scores.get(i));
		}
		return bestScores;
	}
	
	public int getBestScore() {
		try {
			readFile("./resources/ScoreSave.txt");
		} catch (IOException e) {
			System.out.println("The file can't be read");
		}
		sortScores();
		return scores.get(0);
	}
	
	private void readFile(String filePath) throws IOException {
		FileReader fr = null;
		Integer readedScore = null;
		
		//Initialization
		try {
			fr = new FileReader(new File(filePath));
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found");
		}
		StreamTokenizer entree = new StreamTokenizer(fr);
		
		while (entree.nextToken() == StreamTokenizer.TT_NUMBER) {
			readedScore = (int)entree.nval;
			scores.add(readedScore);
		}
		fr.close();
	}
	
	public void writeScore2Data(int score) throws IOException {
		FileWriter fw = null;
		try {
		fw = new FileWriter("./resources/ScoreSave.txt", true);
		} catch (FileNotFoundException exc){
			System.out.println("Opening error, file not found");
		}
		fw.append(""+score+"\n");
		fw.close();
	}
}
