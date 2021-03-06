package ee.tlu.springrestfulws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ee.tlu.springrestfulws.dto.School;
import ee.tlu.springrestfulws.dto.Student;
import ee.tlu.springrestfulws.exception.ResourceNotFoundException;

@Service
public class StudentService {

	@Autowired
	private SchoolService schoolService;

	public List<Student> all(Long schoolId) {
		School school = schoolService.getSchools().get(schoolId);
		if (school == null) {
			throw new ResourceNotFoundException("School with the id of " + schoolId + " was not found");
		}
		
		return new ArrayList<>(school.getStudents().values());
	}

	public Student findById(Long schoolId, Long studentId) {
		School school = schoolService.getSchools().get(schoolId);
		if (school == null) {
			throw new ResourceNotFoundException("School with the id of " + schoolId + " was not found");
		}
		
		return school.getStudents().get(studentId);
	}

	public void save(Long schoolId, Student student) {
		School school = schoolService.getSchools().get(schoolId);
		if (school == null) {
			throw new ResourceNotFoundException("School with the id of " + schoolId + " does not exist");
		}
		
		long id = (long) school.getStudents().size() + 1;
		student.setId(id);
		school.getStudents().put(id, student);
	}

	public Student update(Long schoolId, Long studentId, Student student) {
		School school = schoolService.getSchools().get(schoolId);
		if (school == null) {
			throw new ResourceNotFoundException("School with the id of " + schoolId + " does not exist");
		}
		
		Student existing = school.getStudents().get(studentId);
		if (existing == null) {
			throw new ResourceNotFoundException("School with the id of " + schoolId
					+ " does not have an existing student with the id of " + studentId);
		}
		
		existing.setGivenName(student.getGivenName());
		existing.setSurname(student.getSurname());
		return existing;
	}

	public void delete(Long schoolId, Long studentId) {
		School school = schoolService.getSchools().get(schoolId);
		if (school == null) {
			throw new ResourceNotFoundException("School with the id of " + schoolId + " does not exist");
		}
		
		school.getStudents().remove(studentId);
	}

}
