'use strict';

var elements = {
    usernamePage: document.querySelector('#username-page'),
    chatPage: document.querySelector('#chat-page'),
    usernameForm: document.querySelector('#usernameForm'),
    messageForm: document.querySelector('#messageForm'),
    messageInput: document.querySelector('#message'),
    messageArea: document.querySelector('#messageArea'),
    connectingElement: document.querySelector('.connecting'),
    nameInput: document.querySelector('#name')
};

var stompClient = null;
var username = null;

var colors = ['#2196F3', '#32c787', '#00BCD4', '#ff5652', '#ffc107', '#ff85af', '#FF9800', '#39bbb0'];

function connect(event) {
    username = elements.nameInput.value.trim();

    if (username) {
        elements.usernamePage.classList.add('hidden');
        elements.chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }

    event.preventDefault();
}

function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived);

    stompClient.send("/app/chat.addUser", {}, JSON.stringify({ sender: username, type: 'JOIN' }));

    elements.connectingElement.classList.add('hidden');
}

function onError(error) {
    elements.connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    elements.connectingElement.style.color = 'red';
}

function sendMessage(event) {
    var messageContent = elements.messageInput.value.trim();

    if (messageContent && stompClient) {
        var chatMessage = { sender: username, content: messageContent, type: 'CHAT' };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        elements.messageInput.value = '';
    }

    event.preventDefault();
}

function createMessageElement(message) {
    var messageElement = document.createElement('li');
    var textElement = document.createElement('p');

    if (message.type === 'JOIN' || message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + (message.type === 'JOIN' ? ' joined!' : ' left!');
    } else {
        messageElement.classList.add('chat-message');
        var avatarElement = createAvatarElement(message.sender);
        var usernameElement = document.createElement('span');
        usernameElement.appendChild(document.createTextNode(message.sender));

        messageElement.appendChild(avatarElement);
        messageElement.appendChild(usernameElement);
    }

    textElement.appendChild(document.createTextNode(message.content));
    messageElement.appendChild(textElement);

    return messageElement;
}

function createAvatarElement(sender) {
    var avatarElement = document.createElement('i');
    var avatarText = document.createTextNode(sender[0]);
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(sender);
    return avatarElement;
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var messageElement = createMessageElement(message);

    elements.messageArea.appendChild(messageElement);
    elements.messageArea.scrollTop = elements.messageArea.scrollHeight;
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

elements.usernameForm.addEventListener('submit', connect, true);
elements.messageForm.addEventListener('submit', sendMessage, true);
