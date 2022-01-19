window.onload = () => {
    try {
        const sock = new WebSocket('ws://localhost:8080/chat_messaging')

        sock.addEventListener()
    } catch (ex) {
        console.log(ex)
    }
}
