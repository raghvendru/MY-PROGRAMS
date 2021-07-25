import socket

def main():
    s = socket.socket()

    print("Socket created successfully")

    port = 12345

    # Binding

    s.bind(('', port))

    print("Socket binded to the port %s" %(port))

    s.listen(5)

    print("Server is listening on the port %s" %(port))

    while True:
        c, addr = s.accept()
        print("Got the connection request from ", addr)

        encoded_data_from_client = c.recv(2000)
        decoded_data_from_client = encoded_data_from_client.decode()


        message = "Recieved message " + decoded_data_from_client + " from client"
        encoded_message = message.encode()

        c.send(encoded_message)

    c.close()

main()