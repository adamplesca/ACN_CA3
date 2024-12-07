/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package acn_ca3;

/**
 *
 * @author adamp
 */
import java.io.*;
import java.net.*;

public class ACN_CA3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //defines the server, port & default domain
        String server = "whois.internic.net";
        int port = 43;
        String domain = "ncirl.ie"; 
        
        //checks if domain was provided as an argument
        if (args.length > 0) {
            domain = args[0];
        }

        System.out.println("Querying WHOIS info for: " + domain);

        try (Socket socket = new Socket(server, port);
             OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream()) {

            //send the domain query to the WHOIS server
            out.write((domain + "\r\n").getBytes());
            out.flush();

            //read and print the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (UnknownHostException e) {
            System.err.println("Error: Can't resolve to host " + server);
        } catch (IOException e) {
            System.err.println("Error: Unable to connect to server or read/write data.");
        }
    }  
}