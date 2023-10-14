package com.example.PaymentSerice.dao;

import com.example.PaymentSerice.model.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDAO extends JpaRepository<TransactionDetailsEntity, Integer> {
}
