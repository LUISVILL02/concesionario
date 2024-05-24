package com.microservice.bookingservice.service.imp;

import com.microservice.bookingservice.model.dtos.CarDto;
import com.microservice.bookingservice.model.dtos.CustomerDto;
import com.microservice.bookingservice.model.dtos.save.BookingDtoSave;
import com.microservice.bookingservice.model.dtos.send.BookingDtoSend;
import com.microservice.bookingservice.model.entities.Booking;
import com.microservice.bookingservice.model.enums.BookingStatus;
import com.microservice.bookingservice.model.mapper.BookingMapper;
import com.microservice.bookingservice.repository.BookingRepository;
import com.microservice.bookingservice.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingServiceImp implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final RestTemplate restTemplate;

    @Override
    public BookingDtoSend save(BookingDtoSave bookingDtoSave) {
        try {
            String urlCustomer = "http://container2-app:8080/microservice/1.0.0./customers/" + bookingDtoSave.getIdCustomer();
            String urlCar = "http://container1-app:8080/microservice/1.0.0./carrepository/" + bookingDtoSave.getIdCar();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<CustomerDto> customerDtoResponseEntity =
                    restTemplate.getForEntity(urlCustomer, CustomerDto.class);

            ResponseEntity<CarDto> carDtoResponseEntity = restTemplate.exchange(
                    urlCar, HttpMethod.PUT, requestEntity, CarDto.class);

            if (customerDtoResponseEntity.getStatusCode().is2xxSuccessful() &&
                    carDtoResponseEntity.getStatusCode().is2xxSuccessful()) {
                Booking booking = bookingMapper.toEntity(bookingDtoSave);
                booking.setStatus(BookingStatus.CONFIRMED);
                booking.setStartDate(LocalDate.now());
                return bookingMapper.toDtoSend(bookingRepository.save(booking));
            } else {
                throw new RuntimeException("Failed to confirm customer or car availability");
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new RuntimeException("External service call failed: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error saving booking: " + ex.getMessage(), ex);
        }
    }

    @Override
    public BookingDtoSend findById(UUID id) {
        Booking booking = verifier(id);
        return bookingMapper.toDtoSend(booking);
    }

    @Override
    public String cancelar(UUID id) {
        Booking booking = verifier(id);
        booking.setStatus(BookingStatus.CANCELLED);
        //crear otro metodo para solo cambiar la fecha de fin
        booking.setEndDate(LocalDate.now());
        return messaje(booking);
    }

    @Override
    public String completar(UUID id) {
        Booking booking = verifier(id);
        booking.setStatus(BookingStatus.COMPLETE);
        return messaje(booking);
    }

    @Override
    public String fail(UUID id) {
        Booking booking = verifier(id);
        booking.setStatus(BookingStatus.FAILED);
        return messaje(booking);
    }

    public Booking verifier(UUID id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public String messaje(Booking booking) {
        return bookingMapper.toDtoSend(bookingRepository.save(booking)).getStatus().name();
    }
}
