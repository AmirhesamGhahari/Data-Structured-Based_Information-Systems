
public class Student{
	
	private static int idMaker = 100;
	
	private String nameString;
	private double average;
	private int id;
	
	
	public Student(String n) {
		this.nameString = n;
		this.average = 0.0;
		this.id = Student.idMaker;
		id++;
		
	}
	
	
	public double getAverage() {
		return this.average;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}