/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import services.CourService;
import entities.Cour;
import java.util.List;


/**
 *
 * @author Firas
 */
public class CourBack extends BaseForm {
    
    
    public CourBack(Resources res) {
        //template design
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Cours");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        add(LayeredLayout.encloseIn(sl));
                // Affichage
        Display.getInstance().scheduleBackgroundTask(()-> {
                    // this will take a while...
                    Display.getInstance().callSerially(() -> {
                       
                        
                         CourService SP = new CourService();
                         
                         
                    List<Cour> listcours = SP.GettAllCours();
                    for (Cour C : listcours) {
                        
                         
                           addStringValue("Nom",new Label(C.getNom()));
                            addStringValue("Type",new Label(C.getType()));
                           addStringValue("Date Debut",new Label(C.getDated()));
                           addStringValue("Date Fin",new Label(C.getDatef()));
                           Button rv =  new Button("Reserver");
                           addStringValue("Reserver",rv);
                           
                           rv.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent ev) {
                                      CourService SP1 = new CourService();                 
                                   SP1.Reserver(1, C.getId()); // USER 1 Static
                        }
                        });
                           
                           
                        revalidate();
                    }
                    });
        });
        
    }
        private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
}
