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
        
//        Stripe.apiKey = "sk_test_51SngdOEDyt2DDgGcX2wMkGoSDm4dEZ7ekcIhul3YcpNGN8EWMfDRyjq4zb3ah1YICWpkhKuE25k8CSGgIWcAV4DY00CMG7KPI9";
          Stripe.apiKey = System.getenv("STRIPE_SECRET_KEY");
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

