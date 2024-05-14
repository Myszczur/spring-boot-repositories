package pl.urbanik.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.urbanik.school.model.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
