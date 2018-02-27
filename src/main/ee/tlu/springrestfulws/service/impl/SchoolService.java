package ee.tlu.springrestfulws.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ee.tlu.springrestfulws.dto.School;
import ee.tlu.springrestfulws.dto.Student;
import ee.tlu.springrestfulws.exception.ResourceNotFoundException;

@Service
public class SchoolService {

	private static Map<Long, School> schools = new HashMap<>();

	static {
		Map<Long, Student> s1 = new HashMap<>();
		s1.put((long) (s1.size() + 1), new Student(s1.size() + 1, "Juku", "Juurikas"));
		s1.put((long) (s1.size() + 1), new Student(s1.size() + 1, "Mari", "Maasikas"));
		Map<Long, Student> s2 = new HashMap<>();
		s2.put((long) (s2.size() + 1), new Student(s2.size() + 1, "Mart", "Murakas"));
		s2.put((long) (s2.size() + 1), new Student(s2.size() + 1, "Teele", "Tamme"));
		schools.put((long) (schools.size() + 1), new School(schools.size() + 1, "Gustav Adolfi Gümnaasium", s1));
		schools.put((long) (schools.size() + 1), new School(schools.size() + 1, "Kadrioru Saksa Gümnaasium", s2));
	}

	public List<School> all() {
		return new ArrayList<>(schools.values());
	}

	public School getById(Long id) {
		return schools.get(id);
	}

	public void save(School school) {
		long id = (long) schools.size() + 1;
		school.setId(id);
		schools.put(id, school);
	}

	public School update(School school) {
		School existing = schools.get(school.getId());
		if (existing == null) {
			throw new ResourceNotFoundException("School with the id of " + school.getId() + " does not exist");
		}
		
		existing.setName(school.getName());
		existing.setStudents(school.getStudents());
		return existing;
	}

	public void delete(Long schoolId) {
		schools.remove(schoolId);
	}

	public Map<Long, School> getSchools() {
		return schools;
	}

}
