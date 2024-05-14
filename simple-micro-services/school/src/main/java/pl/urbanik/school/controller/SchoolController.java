package pl.urbanik.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.urbanik.school.model.FullSchoolResponse;
import pl.urbanik.school.model.School;
import pl.urbanik.school.service.SchoolService;

import java.util.List;

/**
 * @author kurbanik
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSchool(@RequestBody School school) {
        schoolService.saveSchool(school);
    }

    @GetMapping
    public ResponseEntity<List<School>> findAllSchools() {
        return ResponseEntity.ok(schoolService.findAllSchools());
    }

    @GetMapping("/with-students/{schoolId}")
    public ResponseEntity<FullSchoolResponse> findAllSchoolsWithStudents(@PathVariable Long schoolId) {
        return ResponseEntity.ok(schoolService.findAllSchoolsWithStudents(schoolId));
    }
}
