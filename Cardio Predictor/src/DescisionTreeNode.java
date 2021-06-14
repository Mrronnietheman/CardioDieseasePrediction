import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

class DecisionTreeNode {
	int threshold;
	int feature;

	DecisionTreeNode right;
	DecisionTreeNode left;

	boolean isPredictionNode;

	ArrayList<Integer> activePatients;

	public DecisionTreeNode() {

		this.threshold = 0;
		this.feature = 0;
		this.right = null;
		this.left = null;
		this.isPredictionNode = false;
		this.activePatients = new ArrayList<Integer> ();
	}

	// Compute the fraction of active patients at this node who have disease
	public double diseaseFraction(int[] patientOutcomes) {
		//TODO
		int count = 0;
		for(int i = 0; i < patientOutcomes.length;i++) {
			if(outcomes.get(i) == 1) {
				count++;
			}
		}
		return count/patientOutcomes.length;
	}

	// Compute the entropy at this node
	public double entropy(int[] patientOutcomes) {
		if(diseaseFraction(patientOutcomes) == 0 || diseaseFraction(patientOutcomes) == 1) {
			return 0;
		}else {
			return -Math.log(diseaseFraction(patientOutcomes))/(Math.log(2))-(1-diseaseFraction(patientOutcomes)((Math.log(1-diseaseFraction(patientOutcomes)))/(Math.log(2)));		
			
		}
		// TODO
		// Hint: to compute log base 2, use the change of base formula:
		// log_2(x) = log(x) / log(2)
		// Also, since log(0) is undefined, immediately return 0 if diseaseFraction() = 1 or 0

	}

	// Compute the information gain at this node
	public double informationGain(int[] patientOutcomes, int[][] patientFeatures) {
		// TODO
		// hint: you need to compute the entropy of all active patients, the entropy of the patients below the threshold,
		// and the entropy of the patients above the threshold
		// To do this easily, make new left and right nodes with the apprropiate active patients.
		// Also: the nodes on the left have feature value <= threshold (notice the =)

		return 0;
	}

}


class DecisionTree {

	DecisionTreeNode root;

	public DecisionTree(int numPatients) {
		this.root = new DecisionTreeNode();
		for(int i = 0; i < numPatients;i++) {
		this.root.activePatients.add(outcomes.get(i));
		}
	}

	// Train the decision tree
	public void ID3(int[][] patientFeatures, int[] patientOutcomes) {
		// TODO LATER
	}





/*public class Cardio {

	public static void main(String[] args) {

		// TEST DecisionTree contructor
		int numPatients = 10;
		DecisionTree tree = new DecisionTree(numPatients);

		// Expected output: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
		for (int i = 0; i < numPatients; i++) {
			System.out.print(tree.root.activePatients.get(i) + ", ");
		}
		System.out.println();



		// TEST diseaseFraction()
		int[] patientOutcomes1 = {1, 0, 0, 1, 1, 1, 0, 0, 1, 0};
		int[] patientOutcomes2 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};


		DecisionTreeNode node = new DecisionTreeNode();
		node.activePatients = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3));

		// Expected output: 0.5
		System.out.println(node.diseaseFraction(patientOutcomes1));

		// Expected output: 1.0
		System.out.println(node.diseaseFraction(patientOutcomes2));



		// TEST entropy()
		// Expected output: 1.0
		System.out.println(node.entropy(patientOutcomes1));

		// Expected output: 0
		System.out.println(node.entropy(patientOutcomes2));


		// TEST informationGain()
		int[][] patientFeatures = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}};
		node.threshold = 3;
		node.feature = 0;

		// Expected output: 0.311...
		System.out.println(node.informationGain(patientOutcomes1, patientFeatures));

		// Expected output: 0.0
		System.out.println(node.informationGain(patientOutcomes2, patientFeatures));


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

