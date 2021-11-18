//import SockJS from 'sockjs-client';
//import { Stomp } from '@stomp/stompjs';

var stompClient = null;
var handlers = [];

function connect() {
window.addEventListener("load", function(event) {
var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/activity', tours => {
                     handlers.forEach(handler => handler(JSON.parse(tours.body)));
    });
    console.log("All resources finished loading!");
  });
}



function addHandler(handler) {
    handlers.push(handler);
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendTours(tours) {
    stompClient.send("/app/changeTours", {}, JSON.stringify(tours));
}