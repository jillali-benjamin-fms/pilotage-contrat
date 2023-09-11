package Controller;

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

import com.fms.entity.Users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import repositoryServices.implementation.UserRepositoryImplementation;
/**
 * 
 * @author FMS
 * <p>Controller rest pour les points d'access des utilisateurs
 * url localhost:8181/pilotage_contrat/api/v1/user</p>
 *
 */
@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    
    /**
     * <p>cree l'injection pour le repertoire userRepository.</p>
     */
    private final UserRepositoryImplementation userRepository;
    @Autowired
    public UserController(UserRepositoryImplementation userRepository) {
        this.userRepository = userRepository;
    }
    
    /**
     * <p>url: api/v1/users/users<p>    
     * @return retourne un list des utilisateurs
     */
    @GetMapping("/users")
    public List<Users> getUsers(){
        return userRepository.getUsers();
    }
    
    /**
     * <p>url: api/v1/users/user/{id}</p>    
     * @return retourne une utilisateurs avec l'id
     */
    @GetMapping("/user/{id}")
    public Users getUserById(@PathVariable("id") Long id) {
        return userRepository.getById(id);        
    }
    
    /**
     * <p>url: api/v1/users/user/{email}</p>    
     * @return retourne un liste des utilisateurs avec l'email donnees
     */
    @GetMapping("/user/{email}")
    public Optional<Users> getUserByEmail(@PathVariable("email") String email) {
        return userRepository.findUsersByEmail(email);        
    }
    
    /**
     * <p>url: api/v1/users/newuser</p>
     * <p>le @@RequestBody prendre les elements dans le cour de requette en generale un json</p>     
     * @return retourne un reponse si l'enregistremente de l'utilisateur est passe ou pas
     */
    @PostMapping("/newuser")
    public ResponseEntity<Users> saveUser(@RequestBody @Valid Users user) {
        return ResponseEntity.ok(userRepository.create(user));
    }
    
    /**
     * <p>url: api/v1/users/updateuser</p>
     * <p>le @@RequestBody prendre les elements dans le cour de requette en generale un json</p>     
     * @return retourne un reponse si le modifications de l'utilisateur est passe ou pas.
     */    
    @PostMapping("/updateuser")
    public ResponseEntity<Users> updateUser(@RequestBody Users user){
        return ResponseEntity.ok(userRepository.updateUser(user));
    }
    
    /**
     * <p>url: api/v1/users/deleteuser/{id}</p>  
     * @return retourne un reponse si l'utilisateur avec l'id est Suprime.
     */
    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(userRepository.delete(id));
    }
    
    
    

}




















