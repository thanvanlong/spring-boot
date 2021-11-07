package com.example.testgit.config;

import com.example.testgit.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.util.ArrayList;
import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
public class UserInterCeptor implements ChannelInterceptor {

    private UserRepository userRepository;


    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message,StompHeaderAccessor.class);
        if(StompCommand.CONNECT.equals(accessor.getCommand())){
            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
            if(raw instanceof Map){
                Object name = ((Map<String, Object>) raw).get("username");
                if(name instanceof ArrayList){
                    accessor.setUser( userRepository.findByEmail1(((ArrayList<String>) name).get(0)));
                }
            }
        }

        return message;
    }
}
