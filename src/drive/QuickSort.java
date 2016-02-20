package drive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuickSort
{

	/** QuickSort constructor. This class is written in a generic manner and may be used in any situation where a list of 
	 * values is needed from which a median may be calculated.
	 * @param size - the maximum size of the list of values. 
	 */
	public QuickSort(int size){
		this.arraySize = size;
		this.fixedSizeArray = new Double [size];
	}

	/** addNewValue - Method to allow values to be added to the list of all values. This method should be called 
	 * as often as necessary to provide an up to date list of values. It is from this list that a median is calculated.
	 * @param value
	 */
	public void addNewValue(Double value)
	{

		// Put the input into the class fixedSizeArray
		fixedSizeArray[currentIndex] = value;

		//increment index
		currentIndex += 1;

		// If the current index equals the length of the fixedSizeArray
		if (currentIndex >= arraySize)

			// Set the index back to zero so that the fixedSizeArray will wrap back to the beginning
			// creating a circular buffer
			currentIndex = 0;
	}

	/** getCurrentDistance 
	 * @return double - This is a specifically named method for use by the Shooter, or any client requiring a normalized 
	 * distance determined from the most recent measurements. 
	 */
	public Double getCurrentDistance() {

		// Call the generic method of this class
		return getMedian();
	}

	/** getMedian - Generic method to return the median value calculated from the array of all values
	 * @return double - the median of the array of values from the most recent entries
	 */
	public Double getMedian() {

		// Create a temp ordinary fixedSizeArray list to hold the sorted fixedSizeArray
		ArrayList<Double> sortedData = new ArrayList<Double>();

		// Copy the fixedSizeArray into an fixedSizeArray list
		for (int i = 0; i < fixedSizeArray.length; i++){
			sortedData.add(fixedSizeArray[i]);
		}

		// Sort the fixedSizeArray list
		sortedData = quicksort(sortedData);

		System.out.println("Sorted Data\n" + Arrays.toString(sortedData.toArray()));    

		// Get the median of the sorted data and return it
		return findMedian(sortedData);

	}

	/******************** Private Methods and Data Below *************************/
	
	/**
	 * @param sortedData
	 * @return double representing the median of the list of values
	 */
	private Double findMedian(ArrayList<Double> sortedData) {

		//assumes sortedData.size() != 0, >= 2
		if (sortedData.size() % 2 == 0){
			Integer medLoc1 = sortedData.size() / 2;
			Integer medLoc2 = medLoc1 - 1;

			Double median = (sortedData.get(medLoc1) + sortedData.get(medLoc2)) / 2;
			return median;
		}
		else{
			int medLoc = (sortedData.size() - 1) / 2;
			return sortedData.get(medLoc);
		}
	}

	/**
	 * This method sorts the input Array using the quick sort algorithm.
	 * @param ArrayList of doubles
	 * @return sorted ArrayList of doubles.
	 */
	private ArrayList<Double> quicksort(ArrayList<Double> input){

		if(input.size() <= 1){
			return input;
		}

		//		Integer middle = (Integer)Math.ceil(input.size() / 2);
		Integer middle = input.size() / 2;
		Double pivot =  input.get(middle);

		ArrayList<Double> less = new ArrayList<Double>();
		ArrayList<Double> greater = new ArrayList<Double>();

		for (int i = 0; i < input.size(); i++) {
			if(input.get(i) <= pivot){
				if(i == middle){
					continue;
				}
				less.add(input.get(i));
			}
			else{
				greater.add(input.get(i));
			}
		}

		return concatenate(quicksort(less), pivot, quicksort(greater));
	}

	/**
	 * Join the less fixedSizeArray, pivot integer, and greater fixedSizeArray
	 * to single fixedSizeArray.
	 * @param doubles integer ArrayList with values less than pivot.
	 * @param pivot the pivot integer.
	 * @param doubles2 integer ArrayList with values greater than pivot.
	 * @return the integer ArrayList after join.
	 */
	private ArrayList<Double> concatenate(ArrayList<Double> doubles, Double pivot, ArrayList<Double> doubles2){

		ArrayList<Double> list = new ArrayList<Double>();

		for (int i = 0; i < doubles.size(); i++) {
			list.add(doubles.get(i));
		}

		list.add(pivot);

		for (int i = 0; i < doubles2.size(); i++) {
			list.add(doubles2.get(i));
		}

		return list;
	}

	/**
	 * This method generates an ArrayList with length n containing random integers. 
	 * It is used for testing purposes.
	 * @param n the length of the ArrayList to generate.
	 * @return ArrayList of random integers with length n. 
	 */
	private ArrayList<Double> generateRandomNumbers(int n){

		ArrayList<Double> list = new ArrayList<Double>(n);
		Random random = new Random();

		for (int i = 0; i < n; i++) {
			//list.add(random.nextInt(n * 10));
			list.add(random.nextDouble() * 10);
		}

		return list;
	}

	// Private class variables
	private int arraySize;

	// A basic array is used instead of an ArrayList to make circular buffer and indexing easier  
	private Double[] fixedSizeArray;

	// Variable used for the circular buffer logic
	private int currentIndex = 0;

	public static void main(String[] args) {

		QuickSort app = new QuickSort(8);

		app.addNewValue(4.909);
		app.addNewValue(3.67);
		app.addNewValue(4.768);
		app.addNewValue(2.665);
		app.addNewValue(6.321);
		app.addNewValue(6.883);
		app.addNewValue(1.332);
		app.addNewValue(3.768);
		app.addNewValue(8.665);
		app.addNewValue(634.321);
		app.addNewValue(6.883);
		app.addNewValue(11.332);
		app.addNewValue(4.768);

		System.out.println("Original Array output");
		System.out.println(Arrays.toString(app.fixedSizeArray));

		app.addNewValue(9.765);
		app.addNewValue(99.99);

//		Debug output statements
//		System.out.println("Array output after wrap to index 0");
//		System.out.println(Arrays.toString(app.fixedSizeArray));

		System.out.println(app.getCurrentDistance());
		
	} // end main 

} // end class QuickSort
