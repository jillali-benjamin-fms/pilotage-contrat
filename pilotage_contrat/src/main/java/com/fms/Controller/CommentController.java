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
import com.fms.entity.Comment;
import com.fms.entity.Contract;
import com.fms.entity.Users;
import com.fms.repositoryServices.implementation.ClientRepositoryImplementation;
import com.fms.repositoryServices.implementation.CommentRepositoryImplementation;
import com.fms.repositoryServices.implementation.ContractRepositoryImplementation;
import com.fms.repositoryServices.implementation.UserRepositoryImplementation;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @author FMS
 * <p>Controller rest pour les points d'access des comment
 * url localhost:8181/pilotage_contrat/api/v1/comment</p>
 *
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/comment")
public class CommentController {
    
    /**
     * <p>cree l'injection pour le repertoire commentRepository.</p>
     */
    private final CommentRepositoryImplementation commentRepository;
    private final ContractRepositoryImplementation contractRepository;
    private final UserRepositoryImplementation userRepository;
    @Autowired
    public CommentController(CommentRepositoryImplementation commentRepository, 
            ContractRepositoryImplementation contractRepository, 
                            UserRepositoryImplementation userRepository) {
        
        this.commentRepository = commentRepository;
        this.contractRepository = contractRepository;
        this.userRepository = userRepository;
    }
    
    /**
     * <p>url: api/v1/comment/comments<p>    
     * @return retourne un list des clients
     */
    @GetMapping("/comments")
    public List<Comment> getClients(){
        return commentRepository.getComments();
    }
    
    /**
     * <p>url: api/v1/comment/comment/{id}</p>    
     * @return retourne une comment avec l'id
     */
    @GetMapping("/comment/{id}")
    public Comment getCommentById(@PathVariable("id") Long id) {
        return commentRepository.getById(id);        
    }
    
    /**
     * <p>url: api/v1/comment/commentscontract/{id}</p>    
     * @return retourne une liste des commentaire avec l'id de contrat
     */
    @GetMapping("/commentscontract/{id}")
    public List<Comment> getCommentByContractId(@PathVariable("id") Long id) {
        return commentRepository.findByContractId(id);        
    }
    
    /**
     * <p>url: api/v1/comment/commentsuser/{id}</p>    
     * @return retourne une liste des commentaire avec l'id d'utilisateur.
     */
    @GetMapping("/commentsuser/{id}")
    public List<Comment> getCommentByUserId(@PathVariable("id") Long id) {
        return commentRepository.findByUserId(id);        
    }
    
    /**
     * <p>url: api/v1/comment/newcomment</p>
     * <p>le @@RequestBody prendre les elements dans le cour de requette en generale un json</p>     
     * @return retourne un reponse si l'enregistremente de le commentaire est passe ou pas
     */
    @PostMapping("/{contractid}/newcomment")
    public ResponseEntity<Comment> saveComment(@PathVariable("contractid") long contractId, @RequestBody @Valid Comment comment) {
        Optional<Users> profil = userRepository.findById(comment.getProfil().getId());
        Optional<Contract> optionalContract = contractRepository.findById(contractId);
        Optional<Users> action = userRepository.findById(comment.getAction().getId());
        //Optional<Users> user = userRepository.findById(comment.getUser().getId());
        
        //Optional<Contract> contract = contractRepository.findById(comment.getContract().getId());
        log.info("profil: " + profil.isPresent());
        log.info("contract: " + optionalContract.isPresent());
       // Optional<Users> user = userRepository.findById(comment.getUser().getId());
        if(!profil.isPresent() && !optionalContract.isPresent() && !action.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        comment.setProfil(profil.get());
        comment.setAction(action.get());
        //comment.addUser(profil.get());
        comment.setContract(optionalContract.get());
        return ResponseEntity.ok(commentRepository.create(comment));
    }
    
    /**
     * <p>url: api/v1/comment/updatecomment</p>
     * <p>le @@RequestBody prendre les elements dans le cour de requette en generale un json</p>     
     * @return retourne un reponse si le modifications de commentaire est passe ou pas.
     */    
    @PostMapping("/updatecomment")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment){
        return ResponseEntity.ok(commentRepository.updateComment(comment));
    }
    
    /**
     * <p>url: api/v1/comment/deletecomment/{id}</p>  
     * @return retourne un reponse si le commentaire avec l'id est Suprime.
     */
    @DeleteMapping("/deletecomment/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable("id") Long id){
        return ResponseEntity.ok(commentRepository.delete(id));
    }
    
    /**
     * <p>url: api/v1/comment/deleteusercomments/{id}</p>  
     * @return retourne un reponse si les commentaire avec l'id d'utilisateurs est Suprime.
     */
    @DeleteMapping("/deleteusrcomments/{id}")
    public ResponseEntity<Boolean> deleteUserComments(@PathVariable("id") Long id){
        return ResponseEntity.ok(commentRepository.deleteByUserId(id));
    }
    
    
    

}

























