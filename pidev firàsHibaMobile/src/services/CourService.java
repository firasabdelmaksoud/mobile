/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import entities.Cour;
import entities.Question;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static services.CourService.instance;

/**
 *
 * @author Firas
 */
public class CourService {
    public ArrayList<Cour> Cours;
    public static CourService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
     public CourService() {
        req = new ConnectionRequest();
    }
    
        public static CourService getInstance() {
        if (instance == null) {
            instance = new CourService();
        }
        return instance;
    }
    
      public ArrayList<Cour> GettAllCours() {
        String url = "http://127.0.0.1/pi/web/app_dev.php/api/cours";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Cours = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Cours;
    }  
      
     public ArrayList<Cour> parseCours(String jsonText) {
        
         ArrayList<Cour> cours = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Cour C = new Cour();
                float id = Float.parseFloat(obj.get("id").toString());
                
                C.setId((int) id);
                   C.setNom(obj.get("nomCours").toString());   
                  C.setDated(obj.get("dateDebut").toString());
                 C.setDatef(obj.get("dateFin").toString());
                 C.setType(obj.get("type").toString());
                 
                cours.add(C);
            }

        } catch (IOException ex) {

        }
      
        return cours;
    }
      
    public void Reserver(int user_id,int cour_id){
     String url = "http://127.0.0.1/pi/web/app_dev.php/api/reserver?userid=" + user_id + "&courid=" + cour_id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.getResponseData();
                System.out.println("Ok");
                 Dialog.show("Reservation Comfirmeé", "Merci de utiliser notre Application", "OK", "Cancel");
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
    
}
