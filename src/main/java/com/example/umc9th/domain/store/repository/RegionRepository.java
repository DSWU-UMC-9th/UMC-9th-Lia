package com.example.umc9th.domain.store.repository;

import com.example.umc9th.domain.store.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}

