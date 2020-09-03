package fr.univ.orleans.webservices.liveserialisation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.univ.orleans.webservices.liveserialisation.Dto.MessageDTO;
import fr.univ.orleans.webservices.liveserialisation.modele.Message;
import fr.univ.orleans.webservices.liveserialisation.modele.Utilisateur;
import fr.univ.orleans.webservices.liveserialisation.modele.Views;
import fr.univ.orleans.webservices.liveserialisation.service.Services;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ChatController {
    @Autowired
    private Services services;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/utilisateurs/{idUser}/messages")
    //@JsonView(Views.MessageComplet.class)
    public ResponseEntity<MessageDTO> create(@PathVariable String idUser, @RequestBody MessageDTO messageDTO) {
        Message message =modelMapper.map(messageDTO,Message.class);
        Utilisateur utilisateur = services.findUtilisateurById(idUser)
                .orElseThrow(()->new RuntimeException("Utilisateur non trouvé"));
        message.setUtilisateur(utilisateur);
        Message messageRec = services.saveMessage(message);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(messageRec.getId()).toUri();
        MessageDTO messageR =modelMapper.map(messageRec,MessageDTO.class);
        return ResponseEntity.created(location).body(messageR);
    }

    @GetMapping("/utilisateurs/{idUser}/messages")
    //@JsonView(Views.MessageComplet.class)
    public ResponseEntity<Collection<MessageDTO>>  getAll(@PathVariable String idUser) {
        Collection<Message>  msgs=services.findUtilisateurById(idUser).get().getMessages();

        Collection<MessageDTO>  msgsDTO = msgs.stream()
                .map(message -> modelMapper.map(message, MessageDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(msgsDTO);
    }

}
