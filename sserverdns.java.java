import java.io.*;
import java.net.*;
import java.util.*;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

class serverDNS
{
 public static void main(String args[])
 {
  try
   {
       DatagramSocket server=new DatagramSocket(1309);
       while(true)
       {
             byte[] sendbyte=new byte[1024];
             byte[] receivebyte=new byte[1024];
             DatagramPacket receiver=new DatagramPacket(receivebyte,receivebyte.length);
             server.receive(receiver);
             String str=new String(receiver.getData());
             String s=str.trim(); 
             InetAddress addr=receiver.getAddress();
	     int port=receiver.getPort();
	     InetAddress lm = InetAddress.getByName(s);
	     String name = lm.getHostName();
	     String ipadd = lm.getHostAddress();
                    if(s.equals(ipadd))
                       {
                        sendbyte=name.getBytes();
                        DatagramPacket sender=new DatagramPacket(sendbyte,sendbyte.length,addr,port);
			server.send(sender);
                	break;
                        }
                    else if(s.equals(name))
                       {
                        sendbyte=ipadd.getBytes();
                        DatagramPacket sender=new DatagramPacket(sendbyte,sendbyte.length,addr,port);
                        server.send(sender);
                        break;
                       }                         
       // }         
           break;
    }
  }
   catch(Exception e)
   {
    System.out.println(e);
   }
}
}
