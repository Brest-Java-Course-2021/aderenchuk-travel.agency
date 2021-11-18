import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'

var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/activity', tours => {
            handlers.forEach(handler => handler(JSON.parse(message.body)));
        });
    });
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

window.addEventListener("load", function(event) {
    console.log("All resources finished loading!");
  });
