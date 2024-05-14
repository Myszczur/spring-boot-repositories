package pl.urbanik.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.urbanik.school.client.StudentClient;
import pl.urbanik.school.model.FullSchoolResponse;
import pl.urbanik.school.model.School;
import pl.urbanik.school.repository.SchoolRepository;

import java.util.List;

/**
 * @author kurbanik
 */

@Service
@RequiredArgsConstructor
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private StudentClient studentClient;

    public void saveSchool(School school) {
        schoolRepository.save(school);
    }

    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    public FullSchoolResponse findAllSchoolsWithStudents(Long schoolId) {
        var school = schoolRepository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        var students = studentClient.findAllStudentsBySchool(schoolId);

        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }
}
