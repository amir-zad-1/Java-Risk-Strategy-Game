/**
 * 
 */
package bootstrap;

/**
 * This class is used to implement the callback mechanism
 * <pre>
 * {@code
 *   object.getCallback(new Callback(){        
 *          public void called(int numberOfPlayers){
 *             //called whenever getCallback calls as
 *             // callback.called(value);  
 *          }
 *   })	
 * }
 * </pre>
 * @author m_guntur
 */
public class CallBack {
	
	/**
	 * @param value is the integers want to pass to caller
	 */
	@Deprecated
	public void called(int value){ 	
    }

	/**
	 * This callback passes two arguments to callback listener as stated bellow
	 * @param numberOfPlayers tells number of player going to play the game 
	 * @param strategies tells strategies they are going to follow
	 */
	public void called(int numberOfPlayers, String strategies) {
		
		
	}
	
	/**
	 * This call back method can be used to pass any java Object to listeners
	 * @param object which is generic type
	 */
	public <T> void called(T object){
		
	}
	
}
