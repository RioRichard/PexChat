'use strict';


var stompClient = null;
var form = document.querySelector('#form');
 

function connect() {
    
     
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({},onConnected,onError);
}

// Connect to WebSocket Server.
connect();

function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/checkRoom', onMessageReceivedSubcribe);
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
    var messageInput = document.querySelector("#typeText");
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            room_id : "7d76b4d2-d17b-49f2-b374-58ca7308c73c",
            content : messageContent,
            user_id : "7d76b4d2-d17b-49f2-b374-58ca7308c73c",
        };
        stompClient.send("/chat/7d76b4d2-d17b-49f2-b374-58ca7308c73c/sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceivedSubcribe(payload) {
    
    var message = JSON.parse(payload.body);
    message.map(function(msg){
        stompClient.subscribe('/topic/room/'+msg.room_id, onMessageReceived);
    })
}

function onMessageReceived(payload) {
    // var message = JSON.parse(payload.body);

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
    console.log(payload);
}
 
 
form.addEventListener('submit', sendMessage, true);