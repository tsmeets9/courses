package courses_ir;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @invar | getUsername() != null
 * @invar | getCourses() != null
 * @invar The bidirectional association between courses and students is consistent
 * 		  | getCourses().stream.allMatch(c->c.getStudents().contains(this))
 * 
 */

public class Student {
	
	/**
	 * @invar | getPeerGroup(this) != null
	 */
	String username;
	Map<String, Course> courses = new HashMap<String, Course>();
	//NOOIT public maken, dit is nu package accesible, mag wel
	
	public String getUsername() {
		return username;
	}
	public Set<Course> getCourses() {
		return Set.copyOf(courses.values());
		
	}
	
	public Course getCourse(String name) {
		return courses.get(name);
//		for (Course course : courses) {
//			if (course.name.equals(name))
//				return course;
//		}
//		return null;
	}
	public Student(String username) {
		this.username = username;
	}
	
	/**
	 * @pre We only look to the peergroup 
	 * @post | result == Course.getPeerGroup(this).stream().filter(object -> object instanceof Student &&
	 * 		 | ((Student)object).getCourses().equals(this.getCourses())).count()
	 */
	public int getNbStudentsWithSameProgram() {
		int size = 0;
		// Set<Student> students = new HashSet<Student>();
		for (Course course: this.courses.values()) {
			for (Student student: course.getStudents()) {
				if (student.courses.equals(courses))
					//students.add(student);
					size++;
			}
			break;
		}
		return size;
	}
	
	

}
