package com.fms.repositoryServices.implementation;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
//import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.fms.entity.Client;
import com.fms.entity.Users;
import com.fms.repositoryServices.ClientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


//@RequiredArgsConstructor
@Component
@Service
@Transactional
@Slf4j
public class ClientRepositoryImplementation{
    
    @Autowired
    private ClientRepository clientRepository;
    
    
    public Client create(Client client) {
        log.info("Registering new client");
        return clientRepository.save(client);
    }
    
    public List<Client> getClients(){
        log.info("Fetching clients");
        return clientRepository.findAll();
    }
    
    public Client get(long id) {
        log.info("Getting the client with " + id);
        return clientRepository.findById(id).get();
    }
    
    public Optional<Client> findById(long id){
        return clientRepository.findById(id);
    }
    
    public Client update(Client client) {
        log.info("Updating client ", client);
        return clientRepository.save(client);
    }
    
    public Boolean delete(long id) {
        log.info("Deleting client by id: ", id );
        clientRepository.deleteById(id);
        return true;
    }

}
