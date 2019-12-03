The server and client directory contains the compiled java class files which should be run using the command line.
The "data.txt" file starts off in the server directory only.
Run the server by running "java Server" from the command line while in the server directory.
Then use "java MyClient 127.0.0.1 4400 -d data.txt" in a separate terminal from the client directory,
and observe as the file copied from the server to client.
Similarly, using <-u> copies files from the client to server.