package com.example.PexChat.Controller;

import java.security.Principal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.PexChat.Model.Messenges;
import com.example.PexChat.Model.Room;
import com.example.PexChat.Model.Users;
import com.example.PexChat.Repo.RoomRepo;
import com.example.PexChat.Service.MessengesService;
import com.example.PexChat.SideModel.MessegesSideModel;
import com.google.gson.Gson;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController extends BaseController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @GetMapping("/")
    public String home(Model model) {
             
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "loginPage";
        }
        var user = userService.GetCurrentUser();
        model.addAttribute("info", user);
        System.out.println("username: "+ user.getUsername());
        model.addAttribute("listRoom", roomService.getRooms(user.getUsername()));
        
        return "homepage";
    }

    // @ResponseBody
    // @GetMapping("/test")
    // public String name() {
        

    //     Gson gson = new Gson();
    //     return gson.toJson(messengesService.test());
    // }

    @MessageMapping("/room")
    @SendTo("/topic/checkRoom")

    public List<Room> rooms(Principal principal) {
        System.out.println(1);
        return roomService.getRooms(principal.getName());
    }
    @MessageMapping("/chat/createRoom/{user_id}")
    @SendTo("/topic/room")
    public void userJoinRoom(@DestinationVariable UUID user_id, Principal principal) {
        // with enabled spring security
        String username = principal.getName();

        var currentUser = userService.GetUser(username);
        var otherUser = userService.GetUser(user_id);

        var newRoom = new Room(UUID.randomUUID(), currentUser.getUsername() + " " + otherUser.getUsername(),
                new Date(System.currentTimeMillis()));
        Messenges joinMessage1 = new Messenges(UUID.randomUUID(), currentUser, newRoom, "" + Messenges.JOIN,
                new Date(System.currentTimeMillis()), Messenges.JOIN);
        Messenges joinMessage2 = new Messenges(UUID.randomUUID(), otherUser, newRoom, "" + Messenges.JOIN,
                new Date(System.currentTimeMillis()), Messenges.JOIN);
        roomService.addRoom(newRoom);
        messengesService.addMesseges(joinMessage2);
        messengesService.addMesseges(joinMessage1);

    }

    @MessageMapping("/{roomId}/sendMessage")
    
    public MessegesSideModel sendMessage(@DestinationVariable String roomId, @Payload MessegesSideModel message) {
        System.out.println(message.getContent());
        System.out.println(message.getRoom_id());
        System.out.println(message.getUser_id());
        messagingTemplate.convertAndSend("/topic/room/"+ roomId, message);

        
        return message;
    }
    @Autowired
    RoomRepo roomRepo;
    @GetMapping("/{roomId}")
    String showMess(@PathVariable (value= "roomId") UUID roomId, Model model){
        var user = userService.GetCurrentUser();
        model.addAttribute("messages", messengesService.getbyroom(roomRepo.getById(roomId)));
        model.addAttribute("info", user);
        model.addAttribute("listRoom", roomService.getRooms(user.getUsername()));
        return "homepage";
    }
}
