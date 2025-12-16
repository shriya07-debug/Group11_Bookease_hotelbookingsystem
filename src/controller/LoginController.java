package controller;

import dao.userDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.UserModel;
import view.login;

public class LoginController {

    private final userDao userdao = new userDao();
    private final login userView;

    public LoginController(login userView) {
        this.userView = userView;
        this.userView.AddLoginListener(new LoginListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                String email = userView.getEmailField().getText();
                String password = String.valueOf(
                        userView.getPasswordField().getPassword()
                );

                UserModel usermodel = new UserModel(email, password);

                boolean check = userdao.login(usermodel);

                if (check) {
                    JOptionPane.showMessageDialog(userView, "Login Successful");
                    close();
                } else {
                    JOptionPane.showMessageDialog(userView, "Invalid Email or Password");
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
g