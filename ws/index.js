window.onload = () => {
    try {
        const sock = new WebSocket('ws://localhost:8080/chat_messaging')
    } catch (ex) {
        console.log(ex)
    }
}
