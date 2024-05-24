package com.microservices.paymentservice.services.imp;

import com.microservices.paymentservice.model.dtos.BookingRequest;
import com.microservices.paymentservice.model.dtos.save.PaymentDtoSave;
import com.microservices.paymentservice.model.dtos.send.PaymentDtoSend;
import com.microservices.paymentservice.model.entities.Payment;
import com.microservices.paymentservice.model.enums.PaymentEstatus;
import com.microservices.paymentservice.model.mappers.PaymentMapper;
import com.microservices.paymentservice.repository.PaymentRepository;
import com.microservices.paymentservice.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class PaymentServiceImp implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private  final RestTemplate restTemplate;
    @Override
    public PaymentDtoSend save(PaymentDtoSave paymentDtoSave) {
        String urlBase = "http://container3-app:8080/microservice/1.0.0./booking";
        String urlBooking = urlBase + "/" + paymentDtoSave.getIdBooking();
        String urlBookingCompletar = urlBase +"/completar/" + paymentDtoSave.getIdBooking();
        String urlBookingFail = urlBase +"/fail/" + paymentDtoSave.getIdBooking();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<BookingRequest> bookingResponseEntity =
                    restTemplate.getForEntity(urlBooking, BookingRequest.class);
            BookingRequest bookingRequest = bookingResponseEntity.getBody();

            Payment payment = paymentMapper.toEntity(paymentDtoSave);

            if (!isNull(bookingRequest) && (bookingRequest.getStatus().equals("CONFIRMED") ||
                    bookingRequest.getStatus().equals("FAILED"))) {
                payment.setStatus(PaymentEstatus.COMPLETED);
                payment.setTransactionId(UUID.randomUUID());

                restTemplate.exchange(urlBookingCompletar, HttpMethod.PUT, requestEntity, String.class);
                return paymentMapper.toDtoSend(paymentRepository.save(payment));
            }
            payment.setStatus(PaymentEstatus.FAILED);
            paymentMapper.toDtoSend(paymentRepository.save(payment));

            if (!isNull(bookingRequest) && !bookingRequest.getStatus().equals("COMPLETE")) {
                restTemplate.exchange(urlBookingFail, HttpMethod.PUT, requestEntity, String.class);
            }
            throw new RuntimeException("Failed to confirm booking");
        } catch (HttpClientErrorException ex) {
            throw new RuntimeException("Error de cliente al llamar al servicio de reserva: " + ex.getMessage(), ex);
        } catch (HttpServerErrorException ex) {
            throw new RuntimeException("Error de servidor al llamar al servicio de reserva: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error inesperado al llamar al servicio de reserva: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<PaymentDtoSend> findById(UUID id) {
        return Optional.ofNullable(paymentRepository.findById(id).map(paymentMapper::toDtoSend).orElseThrow(RuntimeException::new));
    }
}
