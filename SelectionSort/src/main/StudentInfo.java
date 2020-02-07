package main;

public class StudentInfo implements Comparable {

	private String name;
	private double[] tests;
	private int testCount;
	private double[] quizzes;
	private int quizCount;
	private double hwAvg;
	
	public StudentInfo(String nm) {
		nm=name;
		tests=new double[5];
		quizzes=new double[8];
		testCount=0;
		quizCount=0;
	}
	
	public String getName() {
		return name;
	}
	
	public void addTest(double testScore) {
		tests[testCount]=testScore;
		testCount++;
	}
	
	public double getTestAvg() {
		double total=0;
		for(double d:tests) {
			total+=d;
		}
		return total/testCount;
	}
	
	public void addQuiz(double quizScore) {
		quizzes[quizCount]=quizScore;
		quizCount++;
	}
	
	public double getQuizAvg() {
		double total=0;
		for(double d:quizzes) {
			total+=d;
		}
		return total/quizCount;
	}
	
	public void addHwAvg(double hw) {
		hw=hwAvg;
	}
	
	public double getHwAvg() {
		return hwAvg;
	}
	
	public double getFinalAvg() {
		return 0.5*(getTestAvg())+0.3*(getQuizAvg())+0.2*(hwAvg);
	}

	public int compareTo(Object obj) {
		return name.compareTo(((StudentInfo)obj).getName());
	}
}
