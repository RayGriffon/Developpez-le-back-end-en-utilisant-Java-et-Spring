package com.openclassrooms.chatop.service.mapper;

import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.service.RentalService;
import com.openclassrooms.chatop.service.UserService;
import com.openclassrooms.chatop.service.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

  @Autowired
  private RentalService rentalService;

  @Autowired
  private UserService userService;

  public MessageDTO toMessageDTO(Message message) {
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setId(message.getId());
    messageDTO.setRentalId(message.getRental().getId());
    messageDTO.setUserId(message.getUser().getId());
    messageDTO.setMessage(message.getMessage());
    return messageDTO;
  }

  public Message toMessage(MessageDTO messageDTO) {
    Message message = new Message();
    message.setId(messageDTO.getId());
    message.setRental(rentalService.findById(messageDTO.getRentalId()));
    message.setUser(userService.findById(messageDTO.getUserId()));
    message.setMessage(messageDTO.getMessage());
    return message;
  }
}
