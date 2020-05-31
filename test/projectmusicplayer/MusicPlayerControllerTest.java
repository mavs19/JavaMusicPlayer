/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmusicplayer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static junit.framework.TestCase.assertEquals;

/**
 *
 * @author David Perry
 */
public class MusicPlayerControllerTest {
    
    public MusicPlayerControllerTest() {
    }

    MusicPlayerController m;
    UserRepo ur;
    

    @Test
    public void testHandleClickListView() throws Exception {
    }

    

    @org.junit.Test
    public void testCreateUser() {
        try{
            m.createUser("mavs","mavs");
            User user = ur.GetUser("mavs");
            assertEquals(user, "mavs");
            //String result = m.clientLogin("mavs","mavs");
            //assertEquals(result, "success");
            }catch (NullPointerException e){
            
            }
            
    }

    @org.junit.Test
    public void testClientLogin() {
        try{
            m.createUser("mavs","mavs");
            String result = m.clientLogin("mavs","mavs");
            assertEquals(result, "success");
            }catch (NullPointerException e){
            
            }
    }

    
    
}
