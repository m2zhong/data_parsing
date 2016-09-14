/****************************************************************************

                                                        Michael Zhong
                                                        September 6, 2016
                        REU Applicant Programing Assignment

File Name:      Output.java
Description:    This program reads two input files with information in
				<AS1> <AS2> <rel> and <AS> <num_router> format respectively.
				It then calculate the degree and number of router for 
				each AS element, and outputs the result in 
				<ASN> <total_degree> <num_router> format. 

****************************************************************************/
import java.io.*;				//needed for File and FileWriter
import java.util.Scanner;		//needed to scan each file
import javax.swing.*;			//needed for JFileChooser
import java.util.Hashtable;		//needed for Hash Table

public class Output {

	public static void main (String[] args) {
		File f1, f2, output;
		JFileChooser fc = new JFileChooser();
		String buffStr = "";
		String[] stringArr;
		Hashtable<String, Integer> hash = new Hashtable<String, Integer>();
		Integer degree = 1; 
		Integer degree2 = 1;

		/* Prompting user for first file selection*/
		System.out.println("Please select FIRST DATASET (Links).");
		fc.showOpenDialog(null);
		f1 = fc.getSelectedFile();

		/* Promting user for second file selection */
		System.out.println("Please select SECOND DATASET (Routers).");
		fc.showOpenDialog(null);
		f2 = fc.getSelectedFile();

		try{
			Scanner scanner = new Scanner(f1);		//for links file
			Scanner scanner2 = new Scanner(f2);		//for router file


			/* Read until End Of File */
			while(scanner.hasNextLine()){

				buffStr = scanner.nextLine();
				stringArr = buffStr.split(" ", 3);

				/* Updating degree count for visited AS nodes*/
				if(hash.get(stringArr[0]) != null ){
					degree = hash.get(stringArr[0]);
					degree++;

				}
				else
					degree = 1;

				if(hash.get(stringArr[1]) != null){
					degree2 = hash.get(stringArr[1]);
					degree2++;
				}

				/* Initializing degree count for unvisited AS nodes*/
				else{
					degree2 = 1;
				}

				hash.put(stringArr[0], degree);
				hash.put(stringArr[1], degree2);

			}

			/* Creating output file, and file writer */
			output = new File("output.txt");
			PrintWriter pw = new PrintWriter(output);

			/* Reading through router file */
			while(scanner2.hasNextLine()){
				/* Isolating AS number and router count */
				buffStr = scanner2.nextLine();
				stringArr = buffStr.split(" ", 2);

				/* Cross-checking for ASNs present in BOTH files */
				if (hash.get(stringArr[0]) != null){
					pw.println(stringArr[0] + " " + hash.get(stringArr[0]) + 
						" " + stringArr[1].trim());
				}
			}

			/* closing print writer*/
			pw.close();

			/* Telling user where file is stored */
			System.out.println("File saved as: " + output.getAbsolutePath());
		} catch(Exception e){e.printStackTrace();}
	}
}