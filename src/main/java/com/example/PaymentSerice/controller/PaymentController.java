package com.example.PaymentSerice.controller;

import com.example.PaymentSerice.dto.TransactionDTO;
import com.example.PaymentSerice.model.BookingInfoEntity;
import com.example.PaymentSerice.model.TransactionDetailsEntity;
import com.example.PaymentSerice.services.PaymentService;
import com.example.PaymentSerice.util.PaymentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveTransaction(@RequestBody TransactionDTO transactionDTO){

        TransactionDetailsEntity transactionDetailsEntity = PaymentUtil.convertDTOtoEntity(transactionDTO);
        TransactionDetailsEntity savedTransactionEntity = paymentService.savePayment(transactionDetailsEntity);

        // Call Booking Service to get Booking Details by booking Id
        BookingInfoEntity bookingInfoEntity = paymentService.getBookingById(savedTransactionEntity.getBookingId());

        String message = "Booking confirmed for user with aadhaar number: "
                + bookingInfoEntity.getAadharNumber()
                +    "    |    "
                + "Here are the booking details:    " + bookingInfoEntity.toString();
        System.out.println(message);
        return new ResponseEntity(savedTransactionEntity.getTransactionId(), HttpStatus.CREATED);
    }

    @GetMapping(value="/transaction/{id}")
    public ResponseEntity getPaymentBasedOnId(@PathVariable(name="id") int id){
        TransactionDetailsEntity transactionDetailsEntity = paymentService.getPaymentById(id);
        TransactionDTO transactionDTO = PaymentUtil.convertEntityToDTO(transactionDetailsEntity);
        return new ResponseEntity(transactionDTO, HttpStatus.OK);
    }
}
