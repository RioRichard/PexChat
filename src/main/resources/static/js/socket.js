'use strict';


var stompClient = null;
var form = document.querySelector('#form');
const MESSAGE = 1;
const IMAGE = 2;
const JOIN = 3;
const JOINED = 4;
const INVITE = 5;

var client = null;






function connect() {
    var messageArea = document.getElementById('chat-scroll')
    if (messageArea != null) {
        messageArea.scrollTop = messageArea.scrollHeight;
    }
    if (form != null) {
        form.addEventListener('submit', sendMessage, true);

    }
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);

}

// Connect to WebSocket Server.
connect();

function onConnected() {
    // Subscribe to the Public Topic
    var userId = document.getElementById('userId').getAttribute('userId')

    client = stompClient.subscribe('/topic/checkRoom', onMessageReceivedSubcribe);
    stompClient.subscribe("/topic/getNewRoom/" + userId, onMessageReceivedNewRoom);
    console.log(stompClient);

    // Tell your username to the server
    stompClient.send("/chat/room",
        {},

    )




    // connectingElement.classList.add('hidden');
}
function onMessageReceivedNewRoom(payload) {
    var message = JSON.parse(payload.body);
    if (message.messengesType == JOINED || message.messengesType == INVITE) {
        window.location.href = "/" + message.room.room_id
    } else {
        stompClient.subscribe('/topic/room/' + message.room.room_id, onMessageReceived);
        var roomList = $('#room_list')
        var content = '';
        content += '<div idRoom= "' + message.room.room_id + '">'
        content += '<a href=/' + message.room.room_id + ' class="list-group-item list-group-item-action px-3 border-0" aria-current="true">'
        content += '<img src="/Image/avt2.jpg" alt="" class="border rounded-circle" style="height: 48px;" width="48px">'
        content += '<strong>' + message.room.room_name + '</strong>'
        content += '</a>'
        content += '</div>'
        console.log(content);
        roomList.prepend(content)
    }



}


function onError(error) {
    console.log('Could not connect to WebSocket server. Please refresh this page to try again!');
}


function sendMessage(event) {
    var script = document.createElement("SCRIPT");

    var messageInput = document.querySelector("#typeText");
    var messageContent = messageInput.value.trim();
    var userId = document.getElementById('userId').getAttribute('userId')
    var room_id = document.getElementById('room_id').innerHTML

    if (messageContent && stompClient) {
        var chatMessage = {
            room_id: room_id,
            content: messageContent,
            user_id: userId,
            msg_type: MESSAGE
        };
        var path = "/chat/" + room_id + "/sendMessage";



        stompClient.send(path, {}, JSON.stringify(chatMessage));
        // // var path = "/chat/createRoom/07e51d0e-f2da-4b5b-b98a-dac11969616c";
        // stompClient.send(path, {},);
        messageInput.value = '';
    }
    event.preventDefault();
}
function createRoom(user_id) {
    var path = "/chat/createRoom/" + user_id;
    stompClient.send(path, {},);


}

function onMessageReceivedSubcribe(payload) {

    var message = JSON.parse(payload.body);
    message.map(function (msg) {
        stompClient.subscribe('/topic/room/' + msg.room_id, onMessageReceived);
    })
    client.unsubscribe();

}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    // var messageElement = document.createElement('li');

    // if(message.type === 'JOIN') {
    //     messageElement.classList.add('event-message');
    //     message.content = message.sender + ' joined!';
    // } else if (message.type === 'LEAVE') {
    //     messageElement.classList.add('event-message');
    //     message.content = message.sender + ' left!';
    // } else {
    //     messageElement.classList.add('chat-message');   
    //     var usernameElement = document.createElement('strong');
    //     usernameElement.classList.add('nickname');
    //     var usernameText = document.createTextNode(message.sender);
    //     var usernameText = document.createTextNode(message.sender);
    //     usernameElement.appendChild(usernameText);
    //     messageElement.appendChild(usernameElement);
    // }

    // var textElement = document.createElement('span');
    // var messageText = document.createTextNode(message.content);
    // textElement.appendChild(messageText);

    // messageElement.appendChild(textElement);

    // messageArea.appendChild(messageElement);
    // messageArea.scrollTop = messageArea.scrollHeight;
    var script = document.createElement("SCRIPT");
    script.src = 'https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js';
    script.type = 'text/javascript';
    document.getElementsByTagName("head")[0].appendChild(script);
    var userId = document.getElementById('userId').getAttribute('userId')

    var content = '<div>';

    if (message.user.user_id != userId) {
        content += '<div class="d-flex justify-content-start">'
        content += '<img src="/Image/' + message.user.avartar + '" alt="" class="border rounded-circle me-2" style="height: 30px;" width="30px">'
        content += '<p class="bg-light p-3 message_circle" style="max-width: 533px;">'


    } else {

        content += '<div class="d-flex justify-content-end" style="margin-right: 50px;">'
        content += '<p class="bg-primary text-white p-3 message_circle" style="max-width: 533px;">'

    }

    content += message.content

    content += '</p>'
    content += '</div>'
    content += '</div>'


    // Load the script

    var path = $('#chat-scroll').append(content);
    path.scrollTop(path.prop("scrollHeight"));
    console.log(path);
    console.log(content);




    console.log(content);
}

$('#newUser').click(function (e) {
    console.log("hello");

});
