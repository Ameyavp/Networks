import java.io.*;
import java.net.*;
public class Client
{
  public static void main(String args[])
  {
    Socket s = null;
    BufferedReader br = null;
    PrintWriter pw = null;
    String msg;
    String servername = "localhost"; // Define a host server
    int port = 5000;                 // Define a port
    if(args.length<2)
    {
      System.out.println("Using host="+servername+" port number="+port);
    }
    else
    {
      servername = args[0];
      port = Integer.parseInt(args[1]);
    }
    try
    { 
      System.out.println("Connecting to "+servername+" on port"+port);
      s = new Socket(servername,port);    /*Attempts to connect to the
                                          specified server at the specified port*/
      System.out.println("Connected");
      br = new BufferedReader(new InputStreamReader(System.in));/*Create a
                       BufferedReader object to read charactors from the console*/
      pw = new PrintWriter(s.getOutputStream(),true);/*Create a PrintWriter object
                                               to write charactors to the socket*/
      do
      {
        msg = br.readLine();
        pw.println(msg);
      }
      while(!msg.equals("OK"));
      s.close();
      br.close();
      pw.close();
    }
    catch(Exception e)
    {
    }
  }
}
