/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sailenawale
 */
public class OnlinePaymentController {




  public void processPayment(int amount) {

    String apiKey = System.getProperty("STRIPE_SECRET_KEY");
    
    if (apiKey == null || apiKey.isEmpty()) {
        apiKey = System.getenv("STRIPE_SECRET_KEY");
    }
    
    if (apiKey == null || apiKey.isEmpty()) {
        System.err.println("ERROR: Stripe API key not found!");
        System.err.println("Set VM option: -DSTRIPE_SECRET_KEY=\"sk_test_...\"");
        return;
    }
    
    Stripe.apiKey = apiKey;
    
    try {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", 1000); 
        chargeParams.put("currency", "usd");
        chargeParams.put("source", "tok_visa"); 

        Charge charge = Charge.create(chargeParams); 
        System.out.println("Payment Successful! Charge ID: " + charge.getId()); 

    } catch (StripeException e) {
        System.err.println("Payment Failed: " + e.getMessage());
    }
}
}

