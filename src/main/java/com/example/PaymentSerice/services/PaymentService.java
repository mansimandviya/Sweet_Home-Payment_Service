package com.example.PaymentSerice.services;

import com.example.PaymentSerice.model.BookingInfoEntity;
import com.example.PaymentSerice.model.TransactionDetailsEntity;

public interface PaymentService {

    public TransactionDetailsEntity savePayment(TransactionDetailsEntity transactionDetailsEntity);

    public TransactionDetailsEntity getPaymentById(int transactionId);

    public BookingInfoEntity getBookingById(int id);
}
