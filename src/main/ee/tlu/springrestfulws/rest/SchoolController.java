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
import ee.tlu.springrestfulws.service.impl.SchoolService;

@RestController
@RequestMapping(value = "/schools")
public class SchoolController {

	@Autowired
	private SchoolService schoolService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<School>> getAllSchools() {
		List<School> schools = schoolService.all();
		return new ResponseEntity<List<School>>(schools, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<School> getSchool(@PathVariable(value = "id") Long id) {
		School school = schoolService.getById(id);
		return new ResponseEntity<School>(school, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<School> insertSchool(@RequestBody School school) {
		schoolService.save(school);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<School> updateSchool(@RequestBody School school) {
		School updated = schoolService.update(school);
		return new ResponseEntity<School>(updated, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<School> removeSchool(@PathVariable(value = "id") Long id) {
		schoolService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
