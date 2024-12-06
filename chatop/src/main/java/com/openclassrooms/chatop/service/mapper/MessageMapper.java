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
    messageDTO.setRental_id(message.getRental().getId());
    messageDTO.setUser_id(message.getUser().getId());
    messageDTO.setMessage(message.getMessage());
    return messageDTO;
  }

  public Message toMessage(MessageDTO messageDTO) {
    Message message = new Message();
    message.setId(messageDTO.getId());
    message.setRental(rentalService.findById(messageDTO.getRental_id()));
    message.setUser(userService.findById(messageDTO.getUser_id()));
    message.setMessage(messageDTO.getMessage());
    return message;
  }
}
