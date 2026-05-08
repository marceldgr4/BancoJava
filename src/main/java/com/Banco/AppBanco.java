package com.Banco;

import javax.swing.*;

public class AppBanco {
    public static void main(String[] args) {
        BankService service = new BankService();
        BankController controller = new BankController(service);

        seedDemoData(service);

        SwingUtilities.invokeLater(()->{
            applyLookAndFeel();
            BankApp app = new BankApp(controller);
            app.setVisible(true);
        });
    }

    private static void seedDemoData(BankService service) {
    }

    private static void applyLookAndFeel() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ignored){

        }
    }
}
