package main;

import java.util.ArrayList;

public class sorter {

	private ArrayList<Double> nums = new ArrayList<Double>();
	
	public sorter() {
	}
	
	public void addNum(String str) throws ImproperFormatException {
		try {
			String[] addedNums = str.split(","); // splits inputed numbers by the ,
			for(String numbers:addedNums) {
				nums.add(Double.parseDouble(numbers));
			}
		}
		catch(NumberFormatException e) {
		}
		sort();
	}
	
	public String print() {
		String str=String.format("%.2f", nums.get(0));
		for(int i=1;i<nums.size();i++) {
			str+=String.format(", %.2f", nums.get(i));
		}
		return str;
	}
	
	public void clear() {
		nums.clear();
	}
	
	private double addNums() {
		int total=0;
		for(Double i:nums) {
			total+=i;
		}
		return total;
	}
	
	public double getMean() {
		return (double)addNums()/nums.size();
	}
	
	public String getMedian() {
		if(nums.size()%2!=0) {
			return String.format("%.2f", nums.get((nums.size()/2)));
		}
		return String.format("%.2f", ((double)nums.get(nums.size()/2)+nums.get((nums.size()/2)-1))/2);
	}
	
	public String getMode() {
		ArrayList<Double>mode=new ArrayList<Double>();
		int modeCount =1;
		int count;
		for(int i=0; i<nums.size(); i++) {
			count=0;
			for(int j=0; j<nums.size(); j++) { 
				if(nums.get(i).equals(nums.get(j)) && !mode.contains(nums.get(i))) { 
					count++;
				}
			}
			if(count>modeCount) {
				mode.clear();
				mode.add(nums.get(i));
				modeCount = count;
			}
			else if(count==modeCount && modeCount>1) {
				mode.add(nums.get(i));
			}
		}
		if(mode.isEmpty()) {
			return "None";
		}
		String str = "";
		for(double i : mode) {
			if(i==mode.get(0)) {
				str += String.format("%.2f", i);
			}
			else {
				str += ", " + String.format("%.2f", i);
			}
		}
		return str;
	}
	
	public String getSD() {
		double[] sdArray=new double[nums.size()];
		
		for(int i=0;i<nums.size();i++) {
			sdArray[i]=Math.pow((nums.get(i)-getMean()), 2	);
		}
		
		double total=0;
		for(Double i:sdArray) {
			total+=i;
		}
		
		return String.format("%.2f", Math.sqrt(total/nums.size()));
	}
	
	private void sort(){
		int size=nums.size();
		for(int i=1;i<size-1;i++) {
			int min=i;
			for(int j=i+1;j<size;j++) {
				if(nums.get(j)<nums.get(min)) {
					min=j;
				}
			}
			if(min!=i) {
				double temp=nums.get(min);
				nums.set(min, nums.get(i));
				nums.set(i, temp);
			}
		}
		
	}

}
