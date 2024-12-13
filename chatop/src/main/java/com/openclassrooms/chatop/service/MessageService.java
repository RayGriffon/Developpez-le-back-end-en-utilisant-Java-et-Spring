package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.exceptions.UnauthorizedException;
import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.MessageRepository;
import com.openclassrooms.chatop.service.mapper.MessageMapper;
import com.openclassrooms.chatop.service.mapper.RentalMapper;
import com.openclassrooms.chatop.service.mapper.UserMapper;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private RentalService rentalService;

  @Autowired
  private UserService userService;

  @Autowired
  private MessageMapper messageMapper;

  @Autowired
  private RentalMapper rentalMapper;

  @Autowired
  private UserMapper userMapper;

  public Message createMessage(int rentalId, int userId, String message) {
    try{
      Rental rental = rentalService.findEntityById(rentalId);
      if (rental == null) {
        throw new BadRequestException("Location introuvable");
      }

      User user = userMapper.toUser(userService.findById(userId));
      if (user == null) {
        throw new BadRequestException("Utilisateur introuvable");
      }

      if (message == null || message.isEmpty()) {
        throw new BadRequestException("Le contenu du message ne peut pas être vide.");
      }

      Message newMessage = new Message();
      newMessage.setRental(rental);
      newMessage.setUser(user);
      newMessage.setMessage(message);

      return messageRepository.save(newMessage);
    } catch (UnauthorizedException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new RuntimeException("Une erreur interne est survenue");
    }
  }
}
