import * as SockJS from "sockjs-client";
import * as Stomp from "stompjs";

let stompClient;

function subscribeToTimer(cb) {
  let serverUrl = "http://localhost:8080/niteWebSocket/socket";
  let ws = new SockJS(serverUrl);

  stompClient = Stomp.over(ws);

  stompClient.connect(
    {},
    function(frame) {
      stompClient.subscribe("/nite", message => {
        if (message.body) {
          console.log(message.body);
          cb(null, message.body);
        }
      });
      stompClient.send("/app/send/message", {}, "nitesh");
    }
  );

  // console.log("\n\n\n cb ::", cb);

  // console.log("\n\n===>>> socket :: ", socket);
  //     socket.on('timer', timestamp =>
  //         cb(null, timestamp)
  //     );
  //     socket.emit('subscribeToTimer', 1000);
}

export { subscribeToTimer };
