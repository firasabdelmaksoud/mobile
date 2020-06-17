/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.ui.BrowserComponent;

/**
 *
 * @author Firas
 */
public class Map extends BaseForm{
    
   public Map(){
       BrowserComponent browser = new BrowserComponent();
        browser.setURL("http://localhost/pi/map.html");
        add(browser);
   }
    
}
