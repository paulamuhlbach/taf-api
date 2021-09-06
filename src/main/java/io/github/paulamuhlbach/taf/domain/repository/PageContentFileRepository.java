package io.github.paulamuhlbach.taf.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.github.paulamuhlbach.taf.domain.model.PageContentFile;

@Repository
public interface PageContentFileRepository extends JpaRepository<PageContentFile, Long> {

}
