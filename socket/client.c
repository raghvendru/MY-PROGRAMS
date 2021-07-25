#include <winsock2.h>
#include <windows.h>
#include <ws2tcpip.h>
#include <stdio.h>
#include <stdlib.h>

#pragma comment (lib, "Ws2_32.lib")


#define DEFAULT_BUFLEN 512
#define DEFAULT_PORT "27015"


void main(int argc, char **argv) {
	int server_fd, new_socket, valread;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    char buffer[1024] = {0};
    char *hello = "Hello from server";
      

	WSADATA wsaData;
    int iResult;

    SOCKET ConnectSocket = INVALID_SOCKET;
    SOCKET ClientSocket = INVALID_SOCKET;

    struct addrinfo *result = NULL;
    struct addrinfo hints;

    int iSendResult;
    char recvbuf[DEFAULT_BUFLEN];
	char sendbuf[DEFAULT_BUFLEN];
    int recvbuflen = DEFAULT_BUFLEN;
    
    // Initialize Winsock
    iResult = WSAStartup(MAKEWORD(2,2), &wsaData);
    if (iResult != 0) {
        printf("WSAStartup failed with error: %d\n", iResult);
        exit ;
    }

    ZeroMemory(&hints, sizeof(hints));
    hints.ai_family = AF_INET;
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_protocol = IPPROTO_TCP;
    hints.ai_flags = AI_PASSIVE;

    // Resolve the server address and port
    iResult = getaddrinfo("127.0.0.1", "27015", &hints, &result);
    if ( iResult != 0 ) {
        printf("getaddrinfo failed with error: %d\n", iResult);
        WSACleanup();
        exit ;
    }

    // Create a SOCKET for connecting to server
    ConnectSocket = socket(result->ai_family, result->ai_socktype, result->ai_protocol);
    if (ConnectSocket == INVALID_SOCKET) {
        printf("socket failed with error: %ld\n", WSAGetLastError());
       // freeaddrinfo(result);
        WSACleanup();
        exit ;
    }
	
	 // connect 
    iResult = connect( ConnectSocket, result->ai_addr, (int)result->ai_addrlen);
    if (iResult == SOCKET_ERROR) {
        printf("bind failed with error: %d\n", WSAGetLastError());
        freeaddrinfo(result);
        closesocket(ConnectSocket);
        WSACleanup();
        exit ;
    }
	printf("connected to server on port %s \n", DEFAULT_PORT);
	strcpy(sendbuf, "hello from client \n") ;
	//iResult = send(ConnectSocket, sendbuf, (int)strlen(sendbuf), 0) ;
	iResult = 1;
	do {
		
		printf("enter data to send to server \n");
		gets(sendbuf);
		
		printf("sending %d bytes with data %s: \n", (int) strlen(sendbuf), sendbuf);
			
		iResult = send(ConnectSocket, sendbuf, (int)strlen(sendbuf), 0) ;
		
		// iResult = recv(ConnectSocket, recvbuf, recvbuflen, 0);
		if (iResult > 0) 
			printf("Bytes sent: %d data is %s \n", iResult, sendbuf);
		else if (iResult == 0)
			printf("Connection closed\n");
		else
			printf("recv failed: %d\n", WSAGetLastError());
		strcpy(recvbuf, "");
	} while (iResult > 0);
	
}

