import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.lang.Math;

class DecisionTreeNode {
	
	public static void main(String[] args) {

		// TEST DecisionTree contructor
		int numPatients = 70000;
		DecisionTree tree = new DecisionTree(numPatients);

		// Expected output: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
		for (int i = 0; i < numPatients; i++) {
			System.out.print(tree.root.activePatients.get(i) + ", ");
		}
		System.out.println();



		// TEST diseaseFraction()
		int[] outcomes1 = {1, 0, 0, 1, 1, 1, 0, 0, 1, 0};
		int[] outcomes2 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};


		DecisionTreeNode node = new DecisionTreeNode();
		node.activePatients = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3));

		// Expected output: 0.5
		System.out.println(node.diseaseFraction(outcomes1));

		// Expected output: 1.0
		System.out.println(node.diseaseFraction(outcomes2));



		// TEST entropy()
		// Expected output: 1.0
		System.out.println(node.entropy(outcomes1));

		// Expected output: 0
		System.out.println(node.entropy(outcomes2));


		// TEST informationGain()
		int[][] features  = {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}, {7, 7}, {8, 8}, {9, 9}, {10, 10}};
		node.threshold = 3;
		node.feature = 0;

		// Expected output: 0.311...
		System.out.println(node.informationGain(outcomes1, features));

		// Expected output: 0.0
		System.out.println(node.informationGain(outcomes2, features));


		// Once you're done with the three DecisionTreeNode methods, the full output should look like:
		/**
		 * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
		 * 0.5
		 * 1.0
		 * 1.0
		 * 0.0
		 * 0.31127812445913283
		 * 0.0
		 */

	}
	int threshold;
	int feature;

	DecisionTreeNode right;
	DecisionTreeNode left;

	boolean isPredictionNode;

	ArrayList<Integer> activePatients;
	int prediction;

	public DecisionTreeNode() {
		this.prediction = 0;
		this.threshold = 0;
		this.feature = 0;
		this.right = null;
		this.left = null;
		this.isPredictionNode = false;
		this.activePatients = new ArrayList<Integer> ();
	}

	// Compute the fraction of active patients at this node who have disease
	public double diseaseFraction(int[] outcomes) {
		//TODO
		int count = 0;
		for(int i = 0; i < this.activePatients.size();i++) {
			if(outcomes[activePatients.get(i)] == 1) {
				count++;
			}
		}
		if(this.activePatients.size() == 0) {
		//System.out.println("size of activePatients " + this.activePatients.size() );
		
		return 0;
		}
		return ((double)count)/((double)this.activePatients.size());
	}

	// Compute the entropy at this node
	public double entropy(int[] outcomes) {
		double frac = diseaseFraction(outcomes);
		//System.out.println("frac happens to be " + frac);
		if(frac == 0 || frac == 1) {
			return 0;
			
		}else {
			
        return (-Math.log(frac)
        		/(Math.log(2)))*(frac)-
        		((1-frac)*
        		((Math.log(1-frac))
        		/(Math.log(2))));		
        						
		}
		
		
		// TODO
		// Hint: to compute log base 2, use the change of base formula:
		// log_2(x) = log(x) / log(2)
		// Also, since log(0) is undefined, immediately return 0 if diseaseFraction() = 1 or 0

	}

	// Compute the information gain at this node
	public double informationGain(int[] outcomes, int[][] ptntFeat) {		
		split( outcomes, ptntFeat);
		double entropy =  this.entropy(outcomes);
		//System.out.println("entropy " + entropy);
		double leftFrac = ((double)(left.activePatients.size())/(double)this.activePatients.size());
		//System.out.println("leftFrac " + leftFrac);
		double leftEntropy = left.entropy(outcomes);
		//System.out.println("leftEntropy " + leftEntropy);
		//System.out.println();
		double rightFrac = ((double)(right.activePatients.size())/(double)this.activePatients.size());
		//System.out.println("rightFrac " + rightFrac);
		double rightEntropy = right.entropy(outcomes);
		//System.out.println("rightEntropy " + rightEntropy);
		//System.out.println("entropy " + entropy);
		//System.out.println(" right split size " + right.activePatients.size());
		//System.out.println("left split size " + left.activePatients.size());
		//System.out.println(" right entropy " + rightEntropy);
		//System.out.println(" left entropy " + leftEntropy);
		double finalEnt =  entropy-leftFrac*leftEntropy-rightFrac*rightEntropy;

		//System.out.println(finalEnt);

		return finalEnt;
	}
	public void split(int[] outcomes, int[][] ptntFeat) {
		DecisionTreeNode left = new DecisionTreeNode();
		DecisionTreeNode right = new DecisionTreeNode();
		
		for(int i = 0; i < activePatients.size();i++) {
			int currentPat = this.activePatients.get(i);
			int colNum = this.feature;
			if(ptntFeat[currentPat][colNum] <= this.threshold) {
				left.activePatients.add(currentPat);
				
			}else {
				right.activePatients.add(currentPat);
			}
		}
		this.left = left;
		this.right = right;
		if(left.diseaseFraction(outcomes) > 0.5) {
			left.prediction = 1;
		}
		if(right.diseaseFraction(outcomes) > 0.5) {
			right.prediction = 1;
		}
		
	}

}


class DecisionTree {

	DecisionTreeNode root;
	
	public DecisionTree(int numPatients) {
		this.root = new DecisionTreeNode();
		for(int i = 0; i < numPatients;i++) {
		this.root.activePatients.add(i);
		}
		
	}

	// Train the decision tree
	//replace features with "features"
	public void ID3(int[][] features, int[] outcomes) {
		helperID3(features, outcomes,this.root);
	}
	public void helperID3(int[][] features, int[] outcomes, DecisionTreeNode node) {
		if(node.activePatients.size() < 1 
		   || node.entropy(outcomes) == 0) {
			//System.out.println(" 183");
			//System.out.println("activePatients " + node.activePatients.size());

			return ;
		}
		System.out.println("activePatients " + node.activePatients.size());

		int threshold = 0;
	    int featureInd = 0;
	    double infoGain = 0;
		for(int featureIndex = 0; featureIndex < features[0].length;featureIndex++) {
		  Set<Integer> uniqueVals = new HashSet<Integer>();
		  for(int currentPatient = 0;currentPatient < features.length;currentPatient++) {	
		    uniqueVals.add(features[currentPatient][featureIndex]);
		  }
		    for(int featureVal : uniqueVals) {
		    node.threshold = featureVal;
			node.feature = featureIndex;
		    double infoGain2 = node.informationGain(outcomes, features);
		    if(infoGain2 > infoGain) {
		    	infoGain = infoGain2;
		    	threshold = featureVal;
		    	featureInd = featureIndex;
				System.out.println("infogain " + infoGain);
				System.out.println("threshold " + threshold);
				System.out.println("feature " + featureInd);

		    }
		    
		    }
		    
		    
		    
		    //store best infoGain --> threshold --> featureIndex
		    
		}
		node.threshold = threshold;
	    node.feature = featureInd; 
	    node.split(outcomes, features);
		helperID3(features, outcomes,node.left);
	    helperID3(features,outcomes,node.right);
	}
	public int testTree(int[]patientFeatures) {
		DecisionTreeNode node = root;
		while(node.right != null) {
		if(node.threshold<patientFeatures[node.feature]) {
			node = node.right;
		}
		else {
			node = node.left;
		}
		}
		return node.prediction;
		
	}
	
	//link code to construct tree; run on file read data
	}

