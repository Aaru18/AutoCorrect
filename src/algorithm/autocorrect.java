package algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class autocorrect {
	

	/*
	 * Calculating the Levenshtein Edit Distance
	 * between the main string m
	 * and the new string n
	 */
	public static int lDistance(String m, String n )
	
	{
		int[][] matrix = new int[m.length()+1][n.length()+1];
		
		for(int i=0;i<=m.length();i++)
		{
			matrix[i][0] = i;
		}
		for(int j=0;j<=n.length();j++)
		{
			matrix[0][j] = j;
		}
		
		for(int i=1;i<=m.length();i++)
		{
			for(int j=1;j<=n.length();j++)
			{
				if(m.charAt(i-1) == n.charAt(j-1))
				{
					matrix[i][j] = matrix[i-1][j-1];
				}
				else
				{
					matrix[i][j] = Math.min(matrix[i-1][j],Math.min(matrix[i][j-1], matrix[i-1][j-1])) + 1;
					
				}
			}
		}
		
		return matrix[m.length()][n.length()];
		
	}
	
	/*
	 * Loading the words in the dictionary
	 * 
	 */
	public static ArrayList<String> loadDict()
	{
		String line = null;
		ArrayList<String> words = new ArrayList<String>();
		
		try
		{
			FileReader fr = new FileReader("C:\\Users\\arunima.mangal\\Desktop\\Dictionary.txt");
			BufferedReader br = new BufferedReader(fr);
			
			while((line = br.readLine()) != null)
			{
				words.add(line.trim().toLowerCase());
			}
			
			br.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return words;
	}
	
	/*
	 * 
	 * Main Function
	 */
	public static void main(String args[])
	{
		ArrayList<String> allWords = loadDict();
		//System.out.println(allWords);
		System.out.println("Enter a word:");
		Scanner sc = new Scanner(System.in);
		String mainWord = sc.nextLine();
		HashMap<Integer,ArrayList<String>> ledMap = new HashMap<Integer,ArrayList<String>>();
		for(String word : allWords)
		{
			int d = lDistance(mainWord,word);
			
			if(ledMap.containsKey(d))
			{
				ledMap.get(d).add(word);
			}
			else
			{
				ArrayList<String> newArray = new ArrayList<String>();
				newArray.add(word);
				ledMap.put(d, newArray);
			}
		}
		Map.Entry<Integer, ArrayList<String>> entry = ledMap.entrySet().iterator().next();
		
		ArrayList<String> suggested = entry.getValue();
		System.out.println(suggested);
		
		
		sc.close();
	}


}
