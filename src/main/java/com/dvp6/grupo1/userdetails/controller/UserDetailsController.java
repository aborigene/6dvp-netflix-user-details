package com.dvp6.grupo1.userdetails.controller;

import com.dvp6.grupo1.userdetails.broker.UserDetailsProducer;
import com.dvp6.grupo1.userdetails.model.FavoritesEntity;
import com.dvp6.grupo1.userdetails.model.LikeOrDislikeEntity;
import com.dvp6.grupo1.userdetails.model.OpenTicket;
import com.dvp6.grupo1.userdetails.model.FavoritesRepository;
import com.dvp6.grupo1.userdetails.model.UserEntity;
import com.dvp6.grupo1.userdetails.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
  Classe responsável por expor as APIs.
*/
@RestController
public class UserDetailsController {

  /*
   * Instancia a classe do repositório do JPA.
   */
  @Autowired
  public FavoritesRepository favoritesRepository;

  @Autowired
  public FavoritesEntity favoritesEntity;

  @Autowired
  public UserRepository userRepository;

  @Autowired
  public UserEntity userEntity;

  /*
   * Criando a rota / com o verbo GET.
   */
  @GetMapping("/")
  public ResponseEntity<String> home() {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /*
   * Criando a rota para adicinar um filme nos favoritos.
   */
  @PostMapping("/setFavorities")
  public ResponseEntity<String> SetFavorities(@RequestBody FavoritesEntity favoritesEntity) {
    try {
      favoritesRepository.save(favoritesEntity);
      favoritesEntity.setId(0);
      favoritesEntity.setUsername(null);
      favoritesEntity.setDateadded(null);
      favoritesEntity.setImdbid(null);

      return new ResponseEntity<>("Filme adicionado como favoritos.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error: " + e + "", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /*
   * Criando a rota para marcar um filme como gostei.
   */
  @PostMapping("/setLike")
  public ResponseEntity<String> SetLike(@RequestBody LikeOrDislikeEntity likeOrDislikeEntity) {
    try {
      UserDetailsProducer.publishLikeOrDislike(likeOrDislikeEntity.getImbdid(), likeOrDislikeEntity.getLikeOrDislike());
      return new ResponseEntity<>("Filme marcado como gostei!", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error: " + e + "", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /*
   * Criando a rota para marcar um filme como não gostei.
   */
  @PostMapping("/setDislike")
  public ResponseEntity<String> SetDislike(@RequestBody LikeOrDislikeEntity likeOrDislikeEntity) {
    try {
      UserDetailsProducer.publishLikeOrDislike(likeOrDislikeEntity.getImbdid(), likeOrDislikeEntity.getLikeOrDislike());
      return new ResponseEntity<>("Filme marcado como não gostei.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error: " + e + "", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /*
   * Criando a rota para abrir um chamado no suporte.
   */
  @PostMapping("/openTicket")
  public ResponseEntity<String> OpenTicket(@RequestBody OpenTicket openTicket) {
    try {
      UserDetailsProducer.publishOpenTicketSupport(openTicket.getUsername(), openTicket.getSubject(),
          openTicket.getDescription());
      return new ResponseEntity<>("Chamado aberto com sucesso!!", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error: " + e + "", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /*
   * Criando a rota /addUser com o verbo POST. Recebe um Json com os dados e cria
   * um novo usuário no sistema.
   */
  @GetMapping("/getUsers")
  public Iterable<UserEntity> getUsers() {
    return userRepository.findAll();
  }

  /*
   * Criando a rota /addUser com o verbo POST. Recebe um Json com os dados e cria
   * um novo usuário no sistema.
   */
  @PostMapping("/addUser")
  public ResponseEntity<String> addUser(@RequestBody UserEntity userEntity) {
    try {
      UserDetailsProducer.addUser(userEntity.getUsername());
      return new ResponseEntity<>("Usuário adicionado com sucesso!", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Erro: por favor valide se o usuário já existe.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
