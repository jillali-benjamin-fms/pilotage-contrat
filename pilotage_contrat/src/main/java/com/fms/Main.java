package com.fms;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fms.entity.Client;
import com.fms.repositoryServices.ClientRepository;



@SpringBootApplication
//@RestController
@ComponentScan({"com.fms"})
public class Main {
    
//    private final ClientRepository clientRepository;
//    
//    public Main(ClientRepository clientRepository) {
//        this.clientRepository = clientRepository;
//    }
//    
	public static void main(String[] args) {	    
	    
		SpringApplication.run(Main.class, args);
	}

}
