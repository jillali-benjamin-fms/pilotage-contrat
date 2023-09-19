package com.fms.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fms.entity.Client;
import com.fms.repositoryServices.implementation.ClientRepositoryImplementation;

import jakarta.validation.Valid;


/**
 * 
 * @author FMS
 * <p>Controller rest pour les points d'access des client
 * url localhost:8181/pilotage_contrat/api/v1/client</p>
 *
 */
@RestController
@RequestMapping(path = "/api/v1/client")
public class ClientController {
    
    /**
     * <p>cree l'injection pour le repertoire clientRepository.</p>
     */
    private final ClientRepositoryImplementation clientRepository;
    @Autowired
    public ClientController(ClientRepositoryImplementation clientRepository) {
        this.clientRepository = clientRepository;
    }
    
    /**
     * <p>url: api/v1/client/clients<p>    
     * @return retourne un list des clients
     */
    @GetMapping("/clients")
    public List<Client> getClients(){
        return clientRepository.getClients();
    }
    
    /**
     * <p>url: api/v1/client/client/{id}</p>    
     * @return retourne une client avec l'id
     */
    @GetMapping("/client/{id}")
    public Client getClientById(@PathVariable("id") Long id) {
        return clientRepository.get(id);        
    }
    
    /**
     * <p>url: api/v1/client/newclient</p>
     * <p>le @@RequestBody prendre les elements dans le cour de requette en generale un json</p>     
     * @return retourne un reponse si l'enregistremente de le client est passe ou pas
     */
    @PostMapping("/newclient")
    public ResponseEntity<Client> saveClient(@RequestBody @Valid Client client) {
        return ResponseEntity.ok(clientRepository.create(client));
    }
    
    /**
     * <p>url: api/v1/client/updateclient</p>
     * <p>le @@RequestBody prendre les elements dans le cour de requette en generale un json</p>     
     * @return retourne un reponse si le modifications de client est passe ou pas.
     */    
    @PostMapping("/updateclient")
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        return ResponseEntity.ok(clientRepository.update(client));
    }
    
    /**
     * <p>url: api/v1/client/deleteclient/{id}</p>  
     * @return retourne un reponse si le client avec l'id est Suprime.
     */
    @DeleteMapping("/deleteclient/{id}")
    public ResponseEntity<Boolean> deleteClient(@PathVariable("id") Long id){
        return ResponseEntity.ok(clientRepository.delete(id));
    }

}
