package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.MessageRepository;
import com.openclassrooms.chatop.service.mapper.MessageMapper;
import com.openclassrooms.chatop.service.mapper.RentalMapper;
import com.openclassrooms.chatop.service.mapper.UserMapper;
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
    Rental rental = rentalService.findEntityById(rentalId);
    User user = userMapper.toUser(userService.findById(userId));

    Message newMessage = new Message();
    newMessage.setRental(rental);
    newMessage.setUser(user);
    newMessage.setMessage(message);

    return messageRepository.save(newMessage);
  }
}
