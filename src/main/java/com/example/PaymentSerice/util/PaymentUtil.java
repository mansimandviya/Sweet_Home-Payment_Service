package com.example.PaymentSerice.util;

import com.example.PaymentSerice.dto.BookingDTO;
import com.example.PaymentSerice.dto.TransactionDTO;
import com.example.PaymentSerice.model.BookingInfoEntity;
import com.example.PaymentSerice.model.TransactionDetailsEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentUtil {

    public static TransactionDetailsEntity convertDTOtoEntity(TransactionDTO transactionDTO){
        TransactionDetailsEntity transactionDetailsEntity = new TransactionDetailsEntity();
        transactionDetailsEntity.setPaymentMode(transactionDTO.getPaymentMode());
        transactionDetailsEntity.setBookingId(transactionDTO.getBookingId());
        transactionDetailsEntity.setCardNumber(transactionDTO.getCardNumber());
        transactionDetailsEntity.setUpiId(transactionDTO.getUpiId());
        return transactionDetailsEntity;
    }

    public static BookingInfoEntity convertDTOtoEntity(BookingDTO bookingDTO){
        BookingInfoEntity bookingInfoEntity = new BookingInfoEntity();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        bookingInfoEntity.setFromDate(LocalDate.parse(bookingDTO.getFromDate(), formatter));
        bookingInfoEntity.setToDate(LocalDate.parse(bookingDTO.getToDate(),
                formatter));
        bookingInfoEntity.setAadharNumber(bookingDTO.getAadharNumber());
        bookingInfoEntity.setNumOfRooms(bookingDTO.getNumOfRooms());
        return bookingInfoEntity;
    }

    public static TransactionDTO convertEntityToDTO(TransactionDetailsEntity transactionDetailsEntity) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setBookingId(transactionDetailsEntity.getBookingId());
        transactionDTO.setPaymentMode(transactionDetailsEntity.getPaymentMode());
        transactionDTO.setUpiId(transactionDetailsEntity.getUpiId());
        transactionDTO.setCardNumber(transactionDetailsEntity.getCardNumber());
        return transactionDTO;
    }
}
