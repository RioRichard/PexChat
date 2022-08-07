package com.example.PexChat.Controller;

import java.security.Principal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.PexChat.Model.Messenges;
import com.example.PexChat.Model.Room;
import com.example.PexChat.Model.Users;
import com.example.PexChat.Repo.RoomRepo;
import com.example.PexChat.Service.MessengesService;
import com.example.PexChat.SideModel.MessegesSideModel;
import com.example.PexChat.SideModel.SubscribeSideModel;
import com.google.gson.Gson;

import lombok.var;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController extends BaseController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @GetMapping("/")
    public String home(Model model, Room room) {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "loginPage";
        }
        var user = userService.GetCurrentUser();
        model.addAttribute("info", user);
        System.out.println("username: " + user.getUser_id());
        model.addAttribute("listRoom", roomService.getRooms(user.getUsername()));
        model.addAttribute("currentRoom", "");

        return "homepage";
    }

    @ResponseBody
    @GetMapping("/test")
    public String name() {

        Gson gson = new Gson();
        return gson.toJson(roomService.getSameRoom("26cf5c8c-34e4-40ab-ad8d-2193d1944b40",
                "c78add34-a17e-4c8c-99ea-cf8137fe25df"));
    }

    @MessageMapping("/room")
    @SendTo("/topic/checkRoom")

    public List<Room> rooms(Principal principal, SimpMessageHeaderAccessor headerAccessor) {

        var userName = principal.getName();
        var userId = userService.GetUser(userName).getUser_id();
        headerAccessor.getSessionAttributes().put("username", userId);
        return roomService.getRooms(userName);
    }

    @MessageMapping("/createRoom/{user_id}")

    public void userJoinRoom(@DestinationVariable UUID user_id, Principal principal) {

        String username = principal.getName();
        var result = new SubscribeSideModel();
        var currentUser = userService.GetUser(username);
        var otherUser = userService.GetUser(user_id);
        var checkSameRoom = roomService.getSameRoom(currentUser.getUser_id().toString(),
                otherUser.getUser_id().toString());
        if (checkSameRoom == null) {
            var newRoom = new Room(UUID.randomUUID(), currentUser.getUsername() + " and " + otherUser.getUsername(),
                    new Date(System.currentTimeMillis()));
            Messenges joinMessage1 = new Messenges(UUID.randomUUID(), currentUser, newRoom, "" + Messenges.JOIN,
                    new Date(System.currentTimeMillis()), Messenges.JOIN);
            Messenges joinMessage2 = new Messenges(UUID.randomUUID(), otherUser, newRoom, "" + Messenges.JOIN,
                    new Date(System.currentTimeMillis()), Messenges.JOIN);
            roomService.addRoom(newRoom);
            messengesService.addMesseges(joinMessage2);
            messengesService.addMesseges(joinMessage1);
            result.setRoom(newRoom);
            result.setMessengesType(Messenges.JOIN);
            messagingTemplate.convertAndSend("/topic/getNewRoom/" + user_id, result);
            result.setMessengesType(Messenges.INVITE);

            messagingTemplate.convertAndSend("/topic/getNewRoom/" + currentUser.getUser_id().toString(), result);
        } else {
            result.setMessengesType(Messenges.JOINED);
            result.setRoom(roomService.getRoom(UUID.fromString(checkSameRoom)));

            messagingTemplate.convertAndSend("/topic/getNewRoom/" + currentUser.getUser_id().toString(), result);
        }

        System.out.println("hello: " + user_id.toString());

    }

    @MessageMapping("/{roomId}/sendMessage")

    public Messenges sendMessage(@DestinationVariable String roomId,
            MessegesSideModel message) {

        var msg = messengesService.addMessengesFromChat(message);
        messagingTemplate.convertAndSend("/topic/room/" + roomId, msg);

        return msg;
    }
    // public MessegesSideModel sendMessage(@DestinationVariable String roomId,
    // MessegesSideModel message) {

    // messagingTemplate.convertAndSend("/topic/room/" + roomId, message);

    // return message;
    // }

    @GetMapping("/{roomId}")
    String showMess(@PathVariable(value = "roomId") UUID roomId, Model model, @Param("keyword") String keyword) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        var user = userService.GetCurrentUser();
        Room room = new Room();
        room.setRoom_id(roomId);
        model.addAttribute("currentRoom", roomId);
        model.addAttribute("messages", messengesService.getbyroom(room));
        model.addAttribute("info", user);
        model.addAttribute("listRoom", roomService.getRooms(user.getUsername()));
        return "homepage";
    }

    @ResponseBody
    @PostMapping("/search")
    public Users viewHomePage(String keyword) {
        var user = userService.findByUser(keyword);
        System.out.print(user.getUsername());
        return user;
    }
}
