package webserver;

import java.net.Socket;

/**
 *
 * @author adm_gustavo
 */
public class App {
    
    public static void main(String[] args) {
        
        WebServer ws = new WebServer();
        ws.setUp();
        
        while(true){
            
            Socket s = ws.waitForConnections();
            String request = ws.receiveRequest(s);
            byte[] response = ws.processRequest(request); 
           
            ws.sendResponse(response, s);
            
        }
        
    }
    
}
