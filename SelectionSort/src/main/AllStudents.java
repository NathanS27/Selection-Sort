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
		for(int i=1;i<size-1;i++) {
			int min=i;
			for(int j=i+1;j<size;j++) {
				if(students.get(j).compareTo(students.get(min))<0) {
					min=j;
				}
			}
			if(min!=i) {
				StudentInfo temp=students.get(min);
				students.set(min, students.get(i));
				students.set(i, temp);
			}
		}
		return students;
	}
	
	public ArrayList<StudentInfo> sortGrades(){
		int size=students.size();
		for(int i=1;i<size-1;i++) {
			int min=i;
			for(int j=i+1;j<size;j++) {
				if(students.get(j).getFinalAvg()<students.get(min).getFinalAvg()) {
					min=j;
				}
			}
			if(min!=i) {
				StudentInfo temp=students.get(min);
				students.set(min, students.get(i));
				students.set(i, temp);
			}
		}
		return students;
	}
	
}
