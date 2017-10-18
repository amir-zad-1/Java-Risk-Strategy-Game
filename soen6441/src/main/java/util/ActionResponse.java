package util;

public class ActionResponse {

    private boolean ok = false;
    private String message;


    public ActionResponse()
    {
    }
    public ActionResponse(String message)
    {
        this.message = message;
    }
    public ActionResponse(boolean ok, String message)
    {
        this.ok = ok;
        this.message = message;
    }


    public boolean getOk() {return this.ok;}
    public void setOk(boolean ok) {this.ok=ok;}

    public String getMessage(){ return this.message; }
    public void setMessage(String message){ this.message = message; }


}
