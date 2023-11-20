package Task_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Faculty {

	String name;
	String address;
	List<Course> courses;

	public Faculty(String name, String address) {
		super();
		this.name = name;
		this.address = address;
		this.courses = new ArrayList<>();
	}

	private void addCourse(Course course) {
		// TODO Auto-generated method stub
		this.courses.add(course);

	}

	// Cau 1:
	public Course getMaxPracticalCourse() {
		Course result = null;
		int max = 0;
		for (Course course : courses) {
			if (course.getType().equals("Practical") && course.getStudents().size() >= max) {
				max = course.getStudents().size();
				result = course;
			}
		}
		return result;
	}

	// Cau 2: 
	public Map<Integer, List<Student>> groupStudentsByYear() {
		Map<Integer, List<Student>> groupedStudents = new HashMap<>();

		for (Course course : courses) {
			for (Student student : course.getStudents()) {
				int year = student.getYear();
				if (!groupedStudents.containsKey(year)) {
					groupedStudents.put(year, new ArrayList<>());
				}
				groupedStudents.get(year).add(student);
			}
		}

		return groupedStudents;
	}

	//Cau 3
	public Set<Course> filterCourses(String type) {
		Set<Course> filteredCourses = new TreeSet<>(new Comparator<Course>() {
			@Override
			public int compare(Course course1, Course course2) {
				return Integer.compare(course2.getStudents().size(), course1.getStudents().size());
			}
		});

		for (Course course : courses) {
			if (course.getType().equals(type)) {
				filteredCourses.add(course);
			}
		}

		return filteredCourses;
	}

	public static void main(String[] args) {
		// Tạo các đối tượng để kiểm thử

		Student s1 = new Student("S01", "A", 2022);
		Student s2 = new Student("S02", "B", 2021);
		Student s3 = new Student("S03", "C", 2021);
		Student s4 = new Student("S04", "D", 2021);
		Student s5 = new Student("S05", "E", 2023);

		Course c1 = new Course("Course1: ", "Data Structure", "Practical", "D");
		Course c2 = new Course("Course2: ", "Web", "Practical", "A");

		c1.addStudent(s1);
		c1.addStudent(s2);
		c2.addStudent(s3);
		c2.addStudent(s4);
		c2.addStudent(s5);

		Faculty f = new Faculty("IT", "HCMC");
		f.addCourse(c1);
		f.addCourse(c2);

		// Kiểm thử phương thức getMaxPracticalCourse
		System.out.println("MaxPracticalCourse : " + f.getMaxPracticalCourse());

		// Kiểm thử phương thức groupStudentsByYear
		Map<Integer, List<Student>> groupedStudents = f.groupStudentsByYear();
		System.out.println("Grouped Students by Year:");
		for (Map.Entry<Integer, List<Student>> entry : groupedStudents.entrySet()) {
			System.out.println("Year " + entry.getKey() + ": " + entry.getValue());
		}

		// Kiểm thử phương thức filterCourses
		System.out.println("filterCourses : " + f.filterCourses("Practical"));
	}

}
