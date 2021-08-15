package com.poli.servicedesk.usecase;

import com.poli.servicedesk.enums.StatusEnum;
import com.poli.servicedesk.model.Customer;
import com.poli.servicedesk.model.dto.CustomerDTO;
import com.poli.servicedesk.service.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerUseCase {

    private final CustomerRepository customerRepository;

    public Mono<Customer> saveCustomer(CustomerDTO customerDTO) {
        log.info("=== CustomerUseCase:::saveCustomer {}", customerDTO);
        return customerRepository.findById(customerDTO.getId())
                .map(customerToUpdate -> customerToUpdate.toBuilder()
                        .name(customerDTO.getName())
                        .firstName(customerDTO.getFirstName())
                        .secondName(customerDTO.getSecondName())
                        .documentNumber(customerDTO.getDocumentNumber())
                        .status(StatusEnum.idFromName(customerDTO.getStatus()))
                        .build())
                .defaultIfEmpty(Customer.builder()
                        .id(UUID.randomUUID().toString())
                        .name(customerDTO.getName())
                        .firstName(customerDTO.getFirstName())
                        .secondName(customerDTO.getSecondName())
                        .documentNumber(customerDTO.getDocumentNumber())
                        .status(StatusEnum.idFromName(customerDTO.getStatus()))
                        .build())
                .flatMap(customerRepository::saveCustomer);
    }

    public Mono<Customer> findById(String id){
        log.info("=== CustomerUseCase:::findById {}", id);
        return customerRepository.findById(id);
    }

    public Mono<Customer> findByDocument(String document){
        log.info("=== CustomerUseCase:::findByDocument {}", document);
        return customerRepository.findByDocument(document);
    }

    public Flux<Customer> findAll(){
        log.info("=== CustomerUseCase:::findAll");
        return customerRepository.findAll();
    }

    public Flux<Customer> findByStatus(String status){
        log.info("=== CustomerUseCase:::findByStatus");
        return Flux.just(StatusEnum.idFromName(status))
                .flatMap(customerRepository::findByStatus);
    }

}
