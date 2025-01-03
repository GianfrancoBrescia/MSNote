package it.linksmt.academy.msnote.repository;

import it.linksmt.academy.msnote.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.user.id = :idUser")
    List<Address> findByIdUser(@Param("idUser") long idUser);
}
