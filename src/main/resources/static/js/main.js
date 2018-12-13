'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var videoPage = document.querySelector('#video-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var videoForm = document.querySelector('#videoForm');
var btnShowCamera = document.querySelector('#btnShowCamera');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = document.querySelector('#name').value.trim();

    if(username) {
        usernamePage.classList.add('hidden');
        videoPage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}


function onError(error) {    
    connectingElement.textContent = 'Impossible de se connecter au Serveur Websocket. Veuillez actualiser la page !';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };

        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' vient de se connecter !';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' a quitte la discussion !';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

function videoSubmit(event) {
    username = document.querySelector('#name').value.trim();

    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.add('hidden');
        videoPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}

//function GetUserMedia(){
//    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia
//                    || navigator.mozGetUserMedia || navigator.msGetUserMedia;
//    return navigator.getUserMedia;
//}

function showWebcam(event){
    var userMedia = GetUserMedia();
    if(userMedia){        
        navigator.getUserMedia({video: true, audio: true}, function(stream){
    
            document.getElementsByTagName("video")[0].src = window.URL.createObjectURL(stream);
    
        }, function(error){
            console.log("There was an error in GetUserMedia!!!");
        });
    }
    event.preventDefault();
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)
videoForm.addEventListener('submit', videoSubmit, true)
btnShowCamera.addEventListener('click', showWebcam, true)



//websocket video call config

//server.js

const express = require("express");
const app = express();
const hostname = "127.0.0.1";
const port = 8090;

var http = require('http').Server(app);
var io = require("socket.io")(http);
app.use(express.static('.'));

io.on('connection', function(client){
    console.log("Connection established!");
    client.on("candidate", function(msg){
        console.log("candidate message recieved!");
        client.broadcast.emit("candidate", msg);
    });
    client.on("sdp", function(msg){
        console.log("sdp message broadcasted!");
        client.broadcast.emit("sdp", msg);
    });
    client.on("desc", function(desc){
        console.log("description received!");
        client.broadcast.emit("desc", desc);
    });
    client.on("answer", function(answer){
        console.log("answer broadcasted");
        client.broadcast.emit("answer", answer);
    });
});

http.listen(port, hostname);


// wbrtc.server.js

//To iron over browser implementation inconsistencies like prefixed naming conventions
GetUserMedia();
GetRTCPeerConnection();
GetRTCSessionDescription();
GetRTCIceCandidate();

//Initializing a peer connection
var caller = new window.RTCPeerConnection();

//Listen for ICE Candidates and send them to remote peers
caller.onicecandidate = function(evt){
  if(!evt.candidate) return;
  console.log("onicecandidate called");
  onIceCandidate(caller, evt);    
};

//onaddstream handler to receive remote feed and show in remoteview video element
caller.onaddstream = function(evt){
  console.log("onaddstream called");
  if(window.URL){
      document.getElementById("remoteview").src = window.URL.createObjectURL(evt.stream);
  } else {
      document.getElementById("remoteview").src = evt.stream;
  }
};    
//Get local audio/video feed and show it in selfview video element 
navigator.getUserMedia({video: true, audio: true}, function(stream){
  if(window.URL){
      document.getElementById("selfview").src = window.URL.createObjectURL(stream);
  } else {
      document.getElementById("selfview").src = stream;
  }
  caller.addStream(stream);    

}, function(evt){
  console.log("Error occurred!");
});
function GetRTCIceCandidate(){
  window.RTCIceCandidate = window.RTCIceCandidate || window.webkitRTCIceCandidate
              || window.mozRTCIceCandidate || window.msRTCIceCandidate;

  return window.RTCIceCandidate;
}
function GetUserMedia(){
  navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia
                  || navigator.mozGetUserMedia || navigator.msGetUserMedia;
  return navigator.getUserMedia;
}
function GetRTCPeerConnection(){
  window.RTCPeerConnection = window.RTCPeerConnection || window.webkitRTCPeerConnection
                      || window.mozRTCPeerConnection || window.msRTCPeerConnection;
  return window.RTCPeerConnection;
}
function GetRTCSessionDescription(){
  window.RTCSessionDescription = window.RTCSessionDescription || window.webkitRTCSessionDescription
                  ||  window.mozRTCSessionDescription || window.msRTCSessionDescription;
  return window.RTCSessionDescription;
}

//Create and send offer to remote peer on button click
document.getElementById("makeCall").addEventListener("click", function(){   
  caller.createOffer().then(function(desc){
      caller.setLocalDescription(new RTCSessionDescription(desc));
      socket.emit("sdp", JSON.stringify({"sdp": desc}));
  });
});

//Send the ICE Candidate to the remote peer
function onIceCandidate(peer, evt){
  if(evt.candidate){        
      socket.emit("candidate", JSON.stringify({"candidate": evt.candidate}));
  }
}

//Communications with the remote peer through signaling server
socket.on("connect", function(client){
  //Connection established with the signaling server
  console.log("connected!");

  //Listening for the candidate message from a peer sent from onicecandidate handler
  socket.on("candidate", function(msg){
      console.log("candidate received");
      caller.addIceCandidate(new RTCIceCandidate(JSON.parse(msg).candidate));
      
  });

  //Listening for Session Description Protocol message with session details from remote peer
  socket.on("sdp", function(msg){
      console.log("sdp received");
      var sessionDesc = new RTCSessionDescription(JSON.parse(msg).sdp);
      caller.setRemoteDescription(sessionDesc);
      caller.createAnswer().then(function(sdp){
          caller.setLocalDescription(new RTCSessionDescription(sdp));
          socket.emit("answer", JSON.stringify({"sdp": sdp}));
      });         
  });

  //Listening for answer to offer sent to remote peer
  socket.on("answer", function(answer){
      console.log("answer received");
      caller.setRemoteDescription(new RTCSessionDescription(JSON.parse(answer).sdp));
  });
});

