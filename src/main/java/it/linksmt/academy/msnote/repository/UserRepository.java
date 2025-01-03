package it.linksmt.academy.msnote.repository;

import it.linksmt.academy.msnote.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByCodiceFiscale(@Param("codiceFiscale") String codiceFiscale);
}
