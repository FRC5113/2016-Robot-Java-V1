package drive;

import java.util.*;
import java.lang.*;
import java.io.*;

public class QuickSort
{
	/**
	 * Main method.
	 * @param args
	 */
	private int arraySize;
	private int currentIndex = 0;
	//private ArrayList<Double> array;
	
	private Double[] array;

	public QuickSort(int size){
		this.arraySize = size;
		//this.array = new ArrayList<Double>(size);
		array = new Double [size];
	}
	
	public static void main(String[] args){

		QuickSort app = new QuickSort(2);
		

		 //Generate an integer array of length 7
	    ArrayList<Double> input = app.generateRandomNumbers(8);
		
	    //Before sort
	    System.out.println("input before\t" + input);
	    
	    //After sort
	    ArrayList<Double> sortedData = app.sortData(input);
	  
//	    input = app.updateInput(input, 4);
	    app.addNewMeasurement(99.987);
	    sortedData = app.sortData(input);
	    
	    System.out.println("input after addition\t" + input);
	    System.out.println("sortedData\t" + sortedData);
	    
	    System.out.println("Median\t" + app.findMedian(sortedData));
	    
	    //System.out.println("sortedData" + sortedData);
	    
	    
	    app.addNewMeasurement(4.909);
	    app.addNewMeasurement(3.67);
	    app.addNewMeasurement(4.768);
	    app.addNewMeasurement(2.665);
	    app.addNewMeasurement(6.321);
	    app.addNewMeasurement(6.883);
	    app.addNewMeasurement(1.332);
	    app.addNewMeasurement(4.768);
	    
	    System.out.println("Array output\t" + app.array);
	    
	    app.addNewMeasurement(9.765);
	    app.addNewMeasurement(99.99);
	    
	    System.out.println("Array output after return to index 0\t" + app.array);
	}
	
	private Double findMedian(ArrayList<Double> sortedData){
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
	
/*	private ArrayList<Double> updateInput(ArrayList<Double> input, double i){
		input.remove(0);
		input.add(i);
		return input;
	}*/
	
	private ArrayList<Double> sortData(List<Double> input){
		ArrayList<Double> sortedData = new ArrayList<Double>();
	    for (int i = 0; i < input.size(); i++){
	    	sortedData.add(input.get(i));
	    }
	    sortedData = quicksort(sortedData);
	    return sortedData;
	}
	
	/**
	 * This method sort the input ArrayList using quick sort algorithm.
	 * @param input the ArrayList of integers.
	 * @return sorted ArrayList of integers.
	 */
	private ArrayList<Double> quicksort(ArrayList<Double> input){
		
		if(input.size() <= 1){
			return input;
		}
		
		//Integer middle = (Integer)Math.ceil(input.size() / 2);
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
	 * Join the less array, pivot integer, and greater array
	 * to single array.
	 * @param less integer ArrayList with values less than pivot.
	 * @param pivot the pivot integer.
	 * @param greater integer ArrayList with values greater than pivot.
	 * @return the integer ArrayList after join.
	 */
	private ArrayList<Double> concatenate(ArrayList<Double> less, Double pivot, ArrayList<Double> greater){
		
		ArrayList<Double> list = new ArrayList<Double>();
		
		for (int i = 0; i < less.size(); i++) {
			list.add(less.get(i));
		}
		
		list.add(pivot);
		
		for (int i = 0; i < greater.size(); i++) {
			list.add(greater.get(i));
		}
		
		return list;
	}
	
	/**
	 * This method generate a ArrayList with length n containing random integers . 
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
	
	public void addNewMeasurement(Double range)
	{
		if(currentIndex >= array.length)
			currentIndex = 0;

//		if(currentIndex < arraySize)
			array[currentIndex] = range;
				
		//increment index
		currentIndex += 1;
		//if index is > array size set index = 0;
	}
	
	

}
