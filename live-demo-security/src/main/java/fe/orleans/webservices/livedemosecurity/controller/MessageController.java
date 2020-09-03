package fe.orleans.webservices.livedemosecurity.controller;


import fe.orleans.webservices.livedemosecurity.models.Message;
import fe.orleans.webservices.livedemosecurity.models.Utilisateur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.rmi.server.ServerCloneException;
import java.security.Principal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

@RestController
@RequestMapping("api")

public class MessageController {

    private static List<Message> messages=new ArrayList<>();
    private final AtomicLong compteur=new AtomicLong(1L);

    private static Map<String, Utilisateur> users=new TreeMap<>();
    public static Map<String, Utilisateur> getUtilisateurs(){
        return users;
    }

    static {
        Utilisateur walid=new Utilisateur("walid","walid",false);
        Utilisateur admin=new Utilisateur("admin","admin",true);
        users.put(walid.getLogin(),walid);
        users.put(admin.getLogin(),admin);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> create(Principal principal , @RequestBody Message message){
        Message m=new Message(compteur.getAndIncrement(),principal.getName()+ " :"+message.getTexte());
        messages.add(m);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(m.getId()).toUri();
        return ResponseEntity.created(location).body(m);
    }
    @GetMapping(value = "/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        return ResponseEntity.ok().body(messages);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable("id") Long id){
        Optional<Message> msg=messages.stream().filter(m->m.getId()==id).findAny();
        if(msg.isPresent()){
            return ResponseEntity.ok().body(msg.get());
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping ("/messages/{id}")
    public ResponseEntity deleteMessage(@PathVariable("id") Long id){
        for (int i=0;i<messages.size();i++){
            if(messages.get(i).getId()==id){
                messages.remove(i);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/utilisateur")
    public ResponseEntity<Utilisateur> createUser( @RequestBody Utilisateur utilisateur){
        Predicate<String> isOk= s-> (s!=null && s.length()>3);
        if(!isOk.test(utilisateur.getLogin()) || !isOk.test(utilisateur.getPassword()))
        {
            return ResponseEntity.badRequest().build();
        }
        if(users.get(utilisateur.getLogin())!=null){
            return ResponseEntity.badRequest().build();
        }
        users.put(utilisateur.getLogin(),utilisateur);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(utilisateur.getLogin()).toUri();
        return ResponseEntity.created(location).body(utilisateur);
    }

    @GetMapping("/utilisateur/{id}")
    //@PreAuthorize("#id==authentication.principal.username")
    public ResponseEntity<Utilisateur> getMessage(Principal principal,@PathVariable("id") String id){
        if(!principal.getName().equals(id)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Utilisateur u= users.get(id);
        if(u!=null){
            return ResponseEntity.ok().body(u);
        }
        return ResponseEntity.notFound().build();

    }
}
