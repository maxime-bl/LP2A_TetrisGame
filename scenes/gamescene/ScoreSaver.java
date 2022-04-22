package scenes.gamescene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class ScoreSaver {
	
	public class ScoreComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer int1, Integer int2) {
			return Integer.compare(int1.intValue(), int2.intValue());
		}
		
	}

	private ArrayList<Integer> scores;
	private int nbScores;
	
	public ScoreSaver() {
		scores = new ArrayList<Integer>();
		nbScores = 0;
	}
	
	public void sortScores() {
		scores.sort(new ScoreComparator());
	}
	
	public ArrayList<Integer> getBestScores() {
		ArrayList<Integer> bestScores = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			bestScores.add(scores.get(i));
		}
		return bestScores;
	}
	
	private void readFile(String filePath) throws IOException {
		BufferedReader buffReader = null;
		Integer readedScore = null;
		String line;
		
		try {
			buffReader = new BufferedReader(new FileReader(new File("./resources/ScoreSave.txt")));
		} catch (FileNotFoundException exc) {
			System.out.println("Opening error");
		} while ((line = buffReader.readLine()) != null) {
			readedScore = Integer.valueOf(line);
			scores.add(readedScore);
			nbScores++;
		}
	}
}
