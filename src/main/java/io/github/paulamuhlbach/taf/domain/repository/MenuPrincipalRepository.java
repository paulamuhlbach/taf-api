package io.github.paulamuhlbach.taf.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import io.github.paulamuhlbach.taf.domain.model.Page;

@Repository
public interface MenuPrincipalRepository extends JpaRepository<Page, Long> {

    List<Page> findByIdMenu(Long idMenu);

}
