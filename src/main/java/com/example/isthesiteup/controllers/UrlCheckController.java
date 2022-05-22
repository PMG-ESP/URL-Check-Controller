package com.example.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class UrlCheckController {
    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOMWN = "Site is down!";
    private String INCORRECT_URL = "URL is incorrect!";


    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
        String returnMessage = "";
        try{
            URL urlObj = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            if(responseCodeCategory!=2){
                returnMessage = SITE_IS_DOMWN;
            }else{
                returnMessage = SITE_IS_UP;
            }

        }catch(MalformedURLException e){
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            returnMessage = INCORRECT_URL;
        }
        
        
        
        return returnMessage;
    }


}
