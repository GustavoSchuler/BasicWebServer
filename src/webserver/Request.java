package webserver;

/**
 *
 * @author adm_gustavo
 */
public class Request {

    private String[] request;
    private String path;
    
    public Request (String request) {
        this.request = request.split(" ");
        this.setPath(this.request[1]);
    }
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        if(path.equals("/")){
            path = "/index.html";
        }
        this.path = path;
    }
    
}
