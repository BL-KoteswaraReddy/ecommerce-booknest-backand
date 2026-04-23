package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.entity.SampleEntity;

public interface SampleRepository extends JpaRepository<SampleEntity, Long> {
}
