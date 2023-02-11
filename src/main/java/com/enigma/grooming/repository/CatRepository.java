package com.enigma.grooming.repository;

import com.enigma.grooming.model.Cat;
import com.enigma.grooming.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, String> {

//    List<Cat> findAll(Specification specification);
//@Query(
//        value = "SELECT * FROM cat WHERE "
//)
    Page<Cat> findAllByUser(User user,Pageable page);



//    Cat findByMailEquals(String mail);

}
