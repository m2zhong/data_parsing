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
		String key;
		String buffStr = "";
		String[] buffArr;
		Hashtable<String, Integer> hash = new Hashtable<String, Integer>();
		Integer degree = 1; 

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
			while(scanner.hasNext()){
				buffStr = scanner.next();

				/* Ommitting AS-to-AS rel as input. Not needed for 
				calculation*/
				if (!buffStr.equals("0") && !buffStr.equals("1") &&
					!buffStr.equals("2") && !buffStr.equals("3")){

					/* Updating degree count for visited AS nodes*/
					if(hash.get(buffStr) != null){
						degree = hash.get(buffStr);
						degree++;
					}

					/* Initializing degree count for unvisited AS nodes*/
					else{
						degree = 1;
					}

					hash.put(buffStr, degree);
				}

				else
					continue;	
			}

			/* Creating output file, and file writer */
			output = new File("./output.txt");
			FileWriter fw = new FileWriter(output);

			/* Reading through router file */
			while(scanner2.hasNextLine()){

				/* Isolating AS number and router count */
				buffStr = scanner2.nextLine();
				buffArr = buffStr.split(" ", 2);


				/* Cross-checking for ASNs present in BOTH files */
				if (hash.get(buffArr[0]) != null){
					fw.write(buffArr[0] + " " + hash.get(buffArr[0]) + 
						" " + buffArr[1] + '\n');
				}

				else
					continue;
			}

			/* Creating output file */
			output.createNewFile();

			/* Telling user where file is stored */
			System.out.println("File saved at: " + fc.getCurrentDirectory());

		} catch(Exception e){
			e.printStackTrace();}
		}
	}