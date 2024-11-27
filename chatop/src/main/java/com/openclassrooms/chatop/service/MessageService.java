package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.MessageRepository;
import com.openclassrooms.chatop.service.dto.MessageDTO;
import com.openclassrooms.chatop.service.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private MessageMapper messageMapper;

  public Message saveMessage(MessageDTO messageDTO) {
    return messageRepository.save(messageMapper.toMessage(messageDTO));
  }

}
