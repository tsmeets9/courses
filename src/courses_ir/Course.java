package courses_ir;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import logicalcollections.LogicalMap;
import logicalcollections.LogicalSet;

/*
 * @invar | getStudents() != null
 * @invar | getName() != null
 * @invar The bidirectional association between courses and students is consistent
 * 		  | getStudents().stream.allMatch(s -> s.getCourses().contains(this))
 */
public class Course {
	
	public static Set<Object> getPeerGroup(Object thisObject) {
		return LogicalSet.matching(m ->
			m.contains(thisObject) &&
			m.allMatch(object ->
			    object instanceof Course ?
			    		((Course)object).name != null &&
			    		((Course)object).students != null
			    :
			    		((Student)object).username != null &&
			    		((Student)object).courses != null) &&
			m.allMatch(object ->
			    object instanceof Course ?
			    		((Course)object).students.stream().allMatch(s ->
			    		    m.contains(s) &&
			    		    s.courses.contains((Course)object)
			    		)
			    :
			    	    ((Student)object).courses.stream().allMatch(c ->
			    	    	m.contains(c) &&
			    	    	c.students.contains((Student)object)
			    	    )
			)
		);
	}
	/**
	 * @invar | getPeerGroup(this) != null
	 */
	private String name;
	Set<Student> students = 
			new HashSet<Student>();
			//Hashtabel kan heel efficient nagaan of een element er in zit, in constante tijd
	
	public String getName() {
		return name;
	}
	public Set<Student> getStudents() {
		// return students; REPRESENTATION EXPOSURE
		return Set.copyOf(students);
		
	}
	
	public Course(String name) {
		this.name = name;
	}
	
	public void enroll(Student student) {
		students.add(student);
		student.courses.add(this);
		
	}
}