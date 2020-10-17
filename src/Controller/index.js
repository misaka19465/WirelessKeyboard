/*
 * WirelessKeyboard
 * Controller
 * Copyleft ! 2020, Olddoctor Development Team.
 * Some rights reserved. Use under GPL license.
 */

const ioHook = require("iohook");

const readline = require("readline").createInterface({
    input: process.stdin,
    output: process.stdout
});

function heartbeat() {
    socket.write("H\n");
    setTimeout(heartbeat, 5000);
}

let socket;
let inputted = false;
readline.on("line", str => {
    socket = new require("net").Socket(6709, str);
    inputted = true;
    readline.close();
});
while(!inputted);

heartbeat();

ioHook.on("keydown", event => {
    socket.write("P" + String(event.keycode) + "\n");
});
ioHook.on("keyup", event => {
    socket.write("R" + String(event.keycode) + "\n");
});

ioHook.start();