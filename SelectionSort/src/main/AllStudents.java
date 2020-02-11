package main;
import java.util.*;

public class AllStudents {

	ArrayList<StudentInfo> students=new ArrayList<>();
	
	public AllStudents() {
		
	}
	public void addStudent(StudentInfo s) {
		students.add(s);
	}
	
	public ArrayList<StudentInfo> sortNames(){
		int size=students.size();
		for(int i=0;i<size-1;i++) {
			int index=i;
			for(int j=i+1;j<size;j++) {
				if(students.get(j).compareTo(students.get(index))<0) {
					index=j;
				}
			}
			if(index!=i) {
				StudentInfo temp=students.get(index);
				students.set(index, students.get(i));
				students.set(i, temp);
			}
		}
		return students;
	}
	
	public ArrayList<StudentInfo> sortGrades(){
		int size=students.size();
		for(int i=0;i<size-1;i++) {
			int max=i;
			for(int j=i+1;j<size;j++) {
				if(students.get(j).getFinalAvg()>students.get(max).getFinalAvg()) {
					max=j;
				}
			}
			if(max!=i) {
				StudentInfo temp=students.get(max);
				students.set(max, students.get(i));
				students.set(i, temp);
			}
		}
		return students;
	}
	
}
