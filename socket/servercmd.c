
#include <windows.h>
#include <winsock2.h>
#include <ws2tcpip.h>
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#pragma comment (lib, "Ws2_32.lib")


#define DEFAULT_BUFLEN 512
#define DEFAULT_PORT "27015"

#define CMDGET	"GET"
#define CMDPOST	"POST"
#define CMDLIST	"LIST"

int sendGET(SOCKET sendSocket) {
	char sendBuff[1024] = "GET METHHOD RETURN 200" ;
	int iResult = send(sendSocket, sendBuff, (int)strlen(sendBuff), 0) ;
	if (iResult == SOCKET_ERROR) {
		printf("send failed: %d\n", WSAGetLastError());
		closesocket(sendSocket);
		WSACleanup();
		return -1 ;
	}
}

int sendPOST(SOCKET sendSocket) {
	char sendBuff[1024] = "POST METHHOD RETURN 200" ;
	int iResult = send(sendSocket, sendBuff, (int)strlen(sendBuff), 0) ;
	if (iResult == SOCKET_ERROR) {
		printf("send failed: %d\n", WSAGetLastError());
		closesocket(sendSocket);
		WSACleanup();
	}
	return iResult ;
}

int sendLIST(SOCKET sendSocket) {
	char sendBuff[1024] = "LIST METHHOD RETURN 200" ;
	int iResult = send(sendSocket, sendBuff, (int)strlen(sendBuff), 0) ;
	if (iResult == SOCKET_ERROR) {
		printf("send failed: %d\n", WSAGetLastError());
		closesocket(sendSocket);
		WSACleanup();
	}
	return iResult ;
}

/*thread function definition*/
void* threadFunction(void *args)
{
	int iResult =1;
	char *hello = "Hello from server";
	char sendBuff[2048]="" ;
	char recvbuf[2048] = "" ;
	SOCKET *clientSocket = (SOCKET *) args ;
	
    while(iResult > 0)
    {
        iResult = recv(*clientSocket, recvbuf, 2048, 0);
		if (iResult == SOCKET_ERROR) {
			printf("receive failed: %d\n", WSAGetLastError());
			closesocket(*clientSocket);
			WSACleanup();
			exit ;
		}
		printf("Result: %d command received is %s \n", iResult, recvbuf);
		if (strcmp(recvbuf,CMDGET) == 0) {
			strcpy(sendBuff,"GET METHHOD RETURN 200");
			iResult = send(*clientSocket, sendBuff ,(int)strlen(sendBuff), 0) ;
		} else if (strcmp(recvbuf, CMDPOST) == 0) {
			strcpy(sendBuff,"CMD METHHOD RETURN 200");
			iResult = send(*clientSocket, sendBuff, (int)strlen(sendBuff), 0) ;
		} else if (strcmp(recvbuf, CMDLIST) == 0) {
			strcpy(sendBuff,"LIST METHHOD RETURN 200");
			iResult = send(*clientSocket, sendBuff ,(int)strlen(sendBuff), 0) ;
		} else {
			strcpy(sendBuff,"ERROR");
			iResult = send(*clientSocket, sendBuff ,(int)strlen(sendBuff), 0) ;
			printf("error wrong command ");
		}
		strcpy(recvbuf, "");
    }
}





void main(int argc, char **argv) {
	int server_fd, new_socket, valread;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    char buffer[1024] = {0};
    		
	WSADATA wsaData;
    int iResult;

    SOCKET ListenSocket = INVALID_SOCKET;
    
	SOCKET *clientSocket ;
	
    struct addrinfo *result = NULL;
    struct addrinfo hints;

    int iSendResult;
	int iNumClient = 0;
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
    ListenSocket = socket(result->ai_family, result->ai_socktype, result->ai_protocol);
    if (ListenSocket == INVALID_SOCKET) {
        printf("socket failed with error: %ld\n", WSAGetLastError());
        WSACleanup();
        exit ;
    }
	
	 // Setup the TCP listening socket
    iResult = bind( ListenSocket, result->ai_addr, (int)result->ai_addrlen);
    if (iResult == SOCKET_ERROR) {
        printf("bind failed with error: %d\n", WSAGetLastError());
        freeaddrinfo(result);
        closesocket(ListenSocket);
        WSACleanup();
        exit ;
    }
	if ( listen( ListenSocket, SOMAXCONN ) == SOCKET_ERROR ) {
		printf( "Listen failed with error: %ld\n", WSAGetLastError() );
		closesocket(ListenSocket);
		WSACleanup();
		exit ;
	}

	printf("created socket on port %s \n", DEFAULT_PORT);
	
	/* now accept connection from client */
	printf("Waiting for the client to connect \n");
	int Error = 0 ;
	while (Error == 0) {
		// accept socket
		// spawn thread to service this client
		pthread_t tid;
		clientSocket = (SOCKET *) malloc(sizeof(SOCKET)) ;
		SOCKET ClientSocket = INVALID_SOCKET;
		ClientSocket = accept(ListenSocket, NULL, NULL) ;
		
		if (ClientSocket == INVALID_SOCKET) {
			Error = 1 ;
			printf("accept failed: %d\n", WSAGetLastError());
			closesocket(ListenSocket);
			WSACleanup();
			exit ;
		} else {
			*clientSocket = ClientSocket;
			printf("accepted client  on port %s with clientSocket %d : Number of Client %d \n", DEFAULT_PORT, *clientSocket,++iNumClient);
			pthread_create(&tid, NULL, &threadFunction, (void *)clientSocket);
		}
	}
}

