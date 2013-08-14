import java.io.*;
import java.net.*;
public class Server 
{
  static Socket socket[] = new Socket[20];
  static ServerSocket ser;
  static ThreadServer s[] = new ThreadServer[20];  
  public static void main(String args[])
  {
    int i = 1;
    int port = 5000;  //Define a port
    if(args.length<1)
    {
    System.out.println("Using port number=" + port);
    }
    else
    {
      port = Integer.parseInt(args[0]);
    }
    try
    {
      ser = new ServerSocket(port);/*Attempts to create a server socket
                                    bound to the specified port*/
      System.out.println("The Server started. To stop it press CTRL+C");
    }
    catch(Exception e)
    {
      System.out.println("Error occured");
    }
    /*Create a new client socket for each connection and the connected
     socket is passed as argument to the constructor of the Thread class*/
    while(true)
    {
      try
      {
        socket[i] = ser.accept();
        System.out.println("Client"+i+"connected");
        s[i] = new ThreadServer(socket[i]);
        i++;
      }
      catch(Exception e)
      {
        System.out.println("Error occured");
      }
    }
  }
}
class ThreadServer implements Runnable
{
  Socket s;
  String msg;
  int j;
  BufferedReader br;
  PrintWriter pw;
  Thread t1 = null;
  /* Create thread object to read charactors from the client socket*/
  public ThreadServer(Socket s)
  {
    this.s = s;
    t1 = new Thread(this);
    t1.start();
  }
  public void run()
  {
    if(Thread.currentThread() == t1) 
    {
      try
      {
        do
        {
          br = new BufferedReader(new InputStreamReader(s.getInputStream()));
          msg = br.readLine();
          System.out.println(msg);
        }
        while(!msg.equals("OK"));
        s.close();
        br.close();
      }
      catch(Exception e)
      {
        System.out.println("Error occured");
      }
    }
  }
}
