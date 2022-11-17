package scenes.gamescene;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import utils.ColorConstants;

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
	
	/**
	 * Method used to get the 5 best scores of all time
	 * @return bestScores: ArrayList<Integer>
	 */
	public ArrayList<Integer> getBestScores() {
		try {
			readFile("./resources/ScoreSave.txt");
		} catch (IOException e) {
			System.out.println("The file can't be read");
		}
		sortScores();
		ArrayList<Integer> bestScores = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			if (scores.size() > i) {
				bestScores.add(scores.get(i));
			} else {
				bestScores.add(0);
			}
		}
		return bestScores;
	}
	
	/**
	 * Method used to get the best score
	 * @return int
	 */
	public int getBestScore() {
		try {
			readFile("./resources/ScoreSave.txt");
		} catch (IOException e) {
			System.out.println("The file can't be read");
		}
		sortScores();
		if (scores.isEmpty() == false) {
			return scores.get(0);
		} else {
			return 0;
		}
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
		
		scores.clear();
		while (entree.nextToken() == StreamTokenizer.TT_NUMBER) {
			readedScore = (int)entree.nval;
			scores.add(readedScore);
		}
		fr.close();
	}
	
	public void writeScore2Data(int score) throws IOException {
		FileWriter fw = null;
		// Initialization
		try {
		fw = new FileWriter("./resources/ScoreSave.txt", true);
		} catch (FileNotFoundException exc){
			System.out.println("Opening error, file not found");
		}
		fw.append(""+score+"\n");
		fw.close();
	}
	
	public void displayBestScores(PApplet w) {
		PFont font;
		font = w.loadFont("./resources/Ebrima-Bold-48.vlw");
		ArrayList<Integer> bestScores = this.getBestScores();
		
		w.push();
		
		w.noStroke();
		w.fill(ColorConstants.CYAN.getRed(), ColorConstants.CYAN.getGreen(), ColorConstants.CYAN.getBlue());
		w.rect(0,0,240,180,9);
		
		w.textFont(font, 16);
		w.fill(0);
		w.textAlign(PConstants.CENTER);
		w.text("TOP SCORES", 120, 20);
		
		w.fill(0);
		w.rect(5,25,230,30*5,9);
		w.fill(40);
		w.rect(5,55,230,30);
		w.rect(5,55+60,230,30);
		
		w.textFont(font, 20);
		w.fill(255);
		for (int i=0; i<5; i++) {
			w.text(bestScores.get(i),120,48 + i*30);
		}
		
		w.pop();
	}
}
