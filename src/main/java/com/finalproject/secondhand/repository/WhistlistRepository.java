package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Whistlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WhistlistRepository extends JpaRepository<Whistlist, Integer> {

    Whistlist findByOrderByWhistlistIdAsc();
}
