import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
import java.lang.Math;

public class ReadCardio {

	public static void main(String[] args) throws IOException { 
		File cardiotrain = new File("/Users/mac/CardioMlProject/cardio_train.csv");
		Scanner scan = new Scanner(cardiotrain);
		int count = 0;
		int i = 0;
		
		while(scan.hasNextLine()) {
			scan.nextLine();
			count++;	
			
		}
		count--;
		
		
		int features[][] = new int[count][11];
		int outcomes[] = new int[count];
		 scan = new Scanner(cardiotrain);
		 scan.nextLine();
		while(scan.hasNextLine()){
		  String str = scan.nextLine();
		
		  String arr[] = str.split(";");
		  outcomes[i] = Integer.parseInt(arr[arr.length-1]);
		 for(int j = 1; j < arr.length-1;j++) {
			 int atJ = Math.round(Float.parseFloat(arr[j]));
			 features[i][j-1] = atJ;
		 }
		  i++;
		}
		//for(int j = 0; j < features.length;i++)
		//System.out.println(outcomes[j]);
	//}
		for(int k = 0; k < features[0].length;k++) {
			System.out.println(features[0][k]);
		}
		
	    DecisionTree tree = new DecisionTree(count);
	    tree.ID3(features, outcomes);
        System.out.println("threshold:" + tree.root.threshold);
        System.out.println("feature:" + tree.root.feature);
	}
}
