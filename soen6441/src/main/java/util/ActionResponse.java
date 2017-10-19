package util;


/**
 * To return multiple results from certain methods.
 */
public class ActionResponse {

    private boolean ok = false;
    private String message;


    /**
     * constructor which does nothing
     */
    public ActionResponse()
    {
    }

    /**
     * sets message of the object
     * @param message desired message to be delivered
     */

    /**
     * Constructor
     * @param message initial value of message property
     */
    public ActionResponse(String message)
    {
        this.message = message;
    }

    /**
     * Constructor
     * @param ok initial value of ok property
     * @param message initial value of message property
     */
    public ActionResponse(boolean ok, String message)
    {
        this.ok = ok;
        this.message = message;
    }


    /**
     * return values of ok
     * @return if action is done or not
     */
    public boolean getOk() {return this.ok;}

    /**
     * set the ok property value
     * @param ok indicated if the action is done or not
     */
    public void setOk(boolean ok) {this.ok=ok;}

    /**
     * return value of message property
     * @return message value
     */
    public String getMessage(){ return this.message; }

    /**
     * sets message value
     * @param message value of message
     */
    public void setMessage(String message){ this.message = message; }


}
