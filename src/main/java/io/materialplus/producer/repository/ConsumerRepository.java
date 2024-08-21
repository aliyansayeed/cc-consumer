package io.materialplus.producer.repository;

import io.materialplus.producer.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Transaction, String> {
}