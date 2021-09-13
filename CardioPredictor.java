import java.util.*;
import java.io.*;

public class CardioPredictor {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(new File("/Users/mac/eclipse/ronoklearnsjava/Cardio Predictor/src/smallcardio.in"));
		int count = 0;
		int i = 0;
		
		while(scan.hasNextLine()) {
			scan.nextLine();
			count++;	
			
		}
		count--;
		
		
		int features[][] = new int[count][11];
		int outcomes[] = new int[count];
		 scan = new Scanner(new File("/Users/mac/eclipse/ronoklearnsjava/Cardio Predictor/src/smallcardio.in"));
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

	    DecisionTree tree = new DecisionTree(count/2);
	    //LOOK AT 43, ID3 NEVER ENDING
	    int[] trainOutcomes = Arrays.copyOfRange(outcomes, 0, outcomes.length/2);
	    int[][] trainFeatures = Arrays.copyOfRange(features, 0, outcomes.length/2);
	    int[] testOutcomes = Arrays.copyOfRange(outcomes, outcomes.length/2, outcomes.length);
	    int[][] testFeatures = Arrays.copyOfRange(features,outcomes.length/2, outcomes.length);
	    tree.ID3(trainFeatures, trainOutcomes);
        System.out.println("threshold:" + tree.root.threshold);
        System.out.println("feature:" + tree.root.feature);
        int c = 0;
        int w = 0;
        for(int j = 0; j < testOutcomes.length;j++) {
        	System.out.println(tree.testTree(testFeatures[j]));
        	if(tree.testTree(testFeatures[j]) == testOutcomes[j]) {
        		c++;
        		
        	}else {
        		w++;
        	}
        }
        double acc = ((double)c/(double)testOutcomes.length) * 100;
        System.out.println("acc:"+acc);
	}
}
