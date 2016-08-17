package webserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author adm_gustavo
 */
public class WebServer {

    private ServerSocket ss;
    private Request req;
    private Response res;
    
    private String serverName = "GAUDERIO 1.0";
    private int port = 8081;
    private String filesPath = "C:/Users/adm_gustavo/Documents/NetBeansProjects/Webserver/src/site";

    public void setUp() {
        try {
            ss = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket waitForConnections() {
        try {
            return ss.accept();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Socket Error!");
        }
    }

    public String receiveRequest(Socket s) {
        
        String command = null;
        String line;
        
        try {
            
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            command = in.readLine();
            
            while ((line = in.readLine()).length() > 0) {
                continue;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return command;
        
    }

    public byte[] processRequest(String request) {
        
        req = new Request(request);
        res = new Response();
        
        res.setContentType("text/html");
        res.setHttpVersion("HTTP/1.1");
        res.setServer("Server: " + serverName);
        res.setConnection("Connection: close");
        
        try{
            Path file = Paths.get(filesPath + req.getPath());
               
            res.setServerStatus("200 OK");
            printLog("request", request);
            
            return Files.readAllBytes(file);
            
        } catch (NoSuchFileException ex) {

            res.setServerStatus("404 Not Found");
            printLog(" ", "\n(" + res.getServerStatus() + ") " + request);

            return res.getServerStatus().getBytes();
            
        } catch (Exception ex){

            res.setServerStatus("500 Internal Server Error");
            printLog(" ", "\n(" + res.getServerStatus() + ") " + request);

            return res.getServerStatus().getBytes();
        }
        
    }

    public void sendResponse(byte responseData[], Socket s) {        
        try{
            OutputStream out = s.getOutputStream();

            res.getContentType(req.getPath());
            res.setContentLength(responseData.length);

            out.write(res.toString().getBytes());
            out.write(responseData);

            printLog("response", res.toString());

            out.flush();
            out.close();
            s.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    private void printLog(String type, String text) {
        
        if (type.equals("request")) {
            System.out.println("\n>>>>>> REQUEST <<<<<<");
            
        } else if (type.equals("response")) {
            System.out.println("\n\n>>>>>> RESPONSE <<<<<<");
        }
        System.out.print(text);
    }
    
}



