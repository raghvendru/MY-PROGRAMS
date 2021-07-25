import socket

s = socket.socket()

host = '127.0.0.1'
port = 12345

s.connect((host, port))

msg = input("Enter a message to send to server")

en_msg = msg.encode()

s.send(en_msg)

message_from_server = s.recv(1024)
decoded_message = message_from_server.decode()

print(decoded_message)

s.close()