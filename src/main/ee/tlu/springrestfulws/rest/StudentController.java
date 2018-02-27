package ee.tlu.springrestfulws.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ee.tlu.springrestfulws.dto.School;
import ee.tlu.springrestfulws.dto.Student;
import ee.tlu.springrestfulws.service.impl.StudentService;

@RestController
@RequestMapping(value = "/schools/{schoolId}/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getAllStudents(@PathVariable(value = "schoolId") Long schoolId) {
		List<Student> students = studentService.all(schoolId);
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}

	@RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
	public ResponseEntity<Student> getStudent(@PathVariable(value = "schoolId") Long schoolId,
			@PathVariable(value = "studentId") Long studentId) {
		Student existing = studentService.getById(schoolId, studentId);
		return new ResponseEntity<Student>(existing, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Student> insertStudent(@PathVariable(value = "schoolId") Long schoolId,
			@RequestBody Student student) {
		studentService.save(schoolId, student);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{studentId}", method = RequestMethod.PUT)
	public ResponseEntity<Student> updateStudent(@PathVariable(value = "schoolId") Long schoolId,
			@PathVariable(value = "studentId") Long studentId, @RequestBody Student student) {
		Student updated = studentService.update(schoolId, student);
		return new ResponseEntity<Student>(updated, HttpStatus.OK);
	}

	@RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE)
	public ResponseEntity<School> removeStudent(@PathVariable(value = "schoolId") Long schoolId,
			@PathVariable(value = "studentId") Long studentId) {
		studentService.delete(schoolId, studentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
