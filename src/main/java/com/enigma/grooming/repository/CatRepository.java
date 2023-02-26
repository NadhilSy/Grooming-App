package com.enigma.grooming.repository;

import com.enigma.grooming.model.Cat;
import com.enigma.grooming.model.CatProjection;
import com.enigma.grooming.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, String> {

    //    List<Cat> findAll(Specification specification);
//@Query(
//        value = "SELECT * FROM cat WHERE "
//)
    @Query(value = "SELECT cat_id,cat_image_url,cat_name,color,gender,is_deleted,race,ua.name FROM  cat JOIN user_account ua ON cat.user_id = ua.user_id WHERE is_deleted = ?1 AND cat.user_id = ?2", nativeQuery = true)
    List<CatProjection> findAll(Boolean isDeleted, Integer userId);

    Page<Cat> findAllByUser(User user, Pageable page);

    @Modifying
    @Query(value = "UPDATE cat SET is_deleted = true WHERE cat_id = :id", nativeQuery = true)
    void deleteById(@Param("id") String id);

//    Cat findByMailEquals(String mail);

}
