package com.example.PaymentSerice.services;

import com.example.PaymentSerice.dao.PaymentDAO;
import com.example.PaymentSerice.dto.BookingDTO;
import com.example.PaymentSerice.model.BookingInfoEntity;
import com.example.PaymentSerice.model.TransactionDetailsEntity;
import com.example.PaymentSerice.util.PaymentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentDAO paymentDAO;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public TransactionDetailsEntity savePayment(TransactionDetailsEntity transactionDetailsEntity){
        return paymentDAO.save(transactionDetailsEntity);
    }

    @Override
    public TransactionDetailsEntity getPaymentById(int transactionId){
        return paymentDAO.getById(transactionId);
    }

    @Override
    public BookingInfoEntity getBookingById(int id){
        String uri = "http://localhost:9191/hotel/booking/{id}";
        Map<String, String> bookingUriMap = new HashMap<>();
        bookingUriMap.put("id", String.valueOf(id));

        BookingDTO bookingDTO = restTemplate.getForObject(uri, BookingDTO.class, bookingUriMap);
        BookingInfoEntity bookingInfoEntity = PaymentUtil.convertDTOtoEntity(bookingDTO);
        return bookingInfoEntity;
    }



}
