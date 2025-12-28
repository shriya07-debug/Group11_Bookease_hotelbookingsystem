import dao.ForgotPasswordDao;
import view.ForgotPassword;



public class Group11BookEase {
    
    public static void main(String [] args) {
        
        ForgotPasswordDao userDAO = new ForgotPasswordDao();
        String testEmail = "dikshyant1415@xavier.edu.np";
        
      if (userDAO.emailExists(testEmail)) {
            System.out.println("Email exists ✔");
        } else {
            System.out.println("Email not found ❌");
        }


        java.awt.EventQueue.invokeLater(() -> {
            new ForgotPassword().setVisible(true);
        });

}
}
