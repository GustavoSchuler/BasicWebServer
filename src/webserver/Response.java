package webserver;

/**
 *
 * @author adm_gustavo
 */
public class Response {
    
    private String contentType;
    private String httpVersion;
    private String server;
    private String serverStatus;
    private String connection;
    private String contentLength;

    public Response(){
        
    }

    public String getContentType() {
        return contentType;
    }
    
    public void setContentType(String type) {
        this.contentType = "Content-Type: " + type;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }
    
    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
    
    public String getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(String serverStatus) {
        this.serverStatus = serverStatus;
    }
    
    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }
    
    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = "Content-Length: " + contentLength;
    }

    public void getContentType(String fileName){
        
        String contentType = "";
        
        if(fileName.endsWith(".html")) {
            contentType = "text/html";
        
        } else if(fileName.endsWith(".png")) {
            contentType = "image/png";
            
        } else if(fileName.endsWith(".htm")) {
            contentType = "text/html; charset=UTF-8";
            
        } else if(fileName.endsWith(".jpg")) {
            contentType = "image/jpg";

        } else if(fileName.endsWith(".jpeg")) {
            contentType = "image/jpeg";
        
        } else if(fileName.endsWith(".gif")) {
            contentType = "image/gif";
            
        } else if(fileName.endsWith(".json")) {
            contentType = "application/json";
        }
        
        if (!contentType.equals("")) 
            this.setContentType(contentType);
    }

    @Override
    public String toString() {
        
        String res;
        res = this.getHttpVersion() + " " + 
              this.getServerStatus() + "\n" + 
              this.getContentType() + "\n" + 
              this.getServer() + "\n" + 
              this.getConnection() + "\n" + 
              this.getContentLength() + "\n\n";
        
        return res;
    }
}
