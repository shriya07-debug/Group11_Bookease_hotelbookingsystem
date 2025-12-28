package controller;

import model.ReviewModel;  
import java.util.ArrayList;
import java.util.List;

public class ReviewController {
    private final List<ReviewModel> reviews;
    private final String currentHotel;
    
    public ReviewController(String hotelName) {
        this.currentHotel = hotelName;
        this.reviews = new ArrayList<>();
        
        addSampleReviews();
    }
    
    private void addSampleReviews() {
        reviews.add(new ReviewModel(currentHotel, "John", "Great hotel!"));
        reviews.add(new ReviewModel(currentHotel, "Sarah", "Very clean rooms"));
        reviews.add(new ReviewModel(currentHotel, "Mike", "Amazing service"));
    }
    
    public void addReview(String userName, String comment) {
        ReviewModel newReview = new ReviewModel(currentHotel, userName, comment);
        reviews.add(newReview);
    }
    
    public List<ReviewModel> getReviews() {
        return reviews;
    }
    
    public String getCurrentHotel() {
        return currentHotel;
    }
}