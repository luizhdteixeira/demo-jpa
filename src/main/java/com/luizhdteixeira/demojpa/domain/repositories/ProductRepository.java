package com.luizhdteixeira.demojpa.domain.repositories;

import com.luizhdteixeira.demojpa.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Query(value = "SELECT P FROM ProductEntity AS P WHERE P.name LIKE %?1%")
    Optional<ProductEntity> findByNameLiked(String name);

}
