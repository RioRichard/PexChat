'use strict';


var stompClient = null;
var form = document.querySelector('#form');
const MESSAGE = 1;
const IMAGE = 2;
const JOIN = 3;
var client = null;




function connect() {


    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);

}

// Connect to WebSocket Server.
connect();

function onConnected() {
    // Subscribe to the Public Topic

    client = stompClient.subscribe('/topic/checkRoom', onMessageReceivedSubcribe);
    console.log(stompClient);

    // Tell your username to the server
    stompClient.send("/chat/room",
        {},

    )




    // connectingElement.classList.add('hidden');
}


function onError(error) {
    console.log('Could not connect to WebSocket server. Please refresh this page to try again!');
}


function sendMessage(event) {
    var script = document.createElement("SCRIPT");
    
    var messageInput = document.querySelector("#typeText");
    var messageContent = messageInput.value.trim();
    var userId = document.getElementById('userId').getAttribute('userId')
    

    if (messageContent && stompClient) {
        var chatMessage = {
            room_id: "7d76b4d2-d17b-49f2-b374-58ca7308c73c",
            content: messageContent,
            user_id: userId,
            msg_type: MESSAGE
        };
        stompClient.send("/chat/7d76b4d2-d17b-49f2-b374-58ca7308c73c/sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
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
    script.src = 'https://code.jquery.com/jquery-3.2.1.slim.min.js';
    script.type = 'text/javascript';
    document.getElementsByTagName("head")[0].appendChild(script);
    
    var content = '';
    var userId = document.getElementById('userId').getAttribute('userId')

    if (message.user_id != userId) {
        content += '<div class="d-flex justify-content-end" style="margin-right: 50px;">'
        content += '<p class="bg-primary text-white p-3 message_circle" style="max-width: 533px;">'
    } else {
        content = '<div class="d-flex justify-content-start">'
        content += '<img src="/src/main/resources/Image/avt2.jpg" alt="" class="border rounded-circle me-2" style="height: 30px;" width="30px">'

        content += '<p class="bg-light p-3 message_circle" style="max-width: 533px;">'
    }

    content += message.content

    content += '</p>'
    content += '</div>'

    // Load the script

    var path = $('#chat-scroll').append(content);
    path.scrollTop(path.prop("scrollHeight"));
    console.log(path);
    console.log(content);




    console.log(content);
}


form.addEventListener('submit', sendMessage, true);