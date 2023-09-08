package com.fms;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fms.entity.Client;

import repositoryServices.ClientRepository;



@SpringBootApplication
@RestController
@RequestMapping("api/v1/clients")
public class Main {
    
    private final ClientRepository clientRepository;
    
    public Main(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    
	public static void main(String[] args) {	    
	    
		SpringApplication.run(Main.class, args);
	}
	
	@GetMapping
	public List<Client> getCustomers(){
	    return clientRepository.findAll();
	}
	//to create a json rest response can be written as
	
	@GetMapping("/greet")
	public GreetResponse greet() {
		return new GreetResponse(
				"Hello",
				List.of("Java", "C", "MySQL"),
				new Person("B.O.B", 24, 100000)
				);
	}
	//record creates an immutable class where all the fields are finals
	record GreetResponse(
			String greet,
			List<String> favProgrammingLanguages,
			Person person
			) {}
	record Person(String name, int age, double savings) {}

}
