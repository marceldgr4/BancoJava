package com.Banco;

import com.Banco.controller.BankController;
import com.Banco.model.Investment.InvestmentCompany;

import com.Banco.model.domain.Person.Client;
import com.Banco.model.domain.Employee.Cashier;
import com.Banco.model.domain.Employee.Supervisor;
import com.Banco.service.BankService;
import com.Banco.view.BankApp;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AppBanco {
    private static void startInterestScheduler(BankService service) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Para producción: TimeUnit.DAYS, 30
        // Para demostración: TimeUnit.MINUTES, 1
        scheduler.scheduleAtFixedRate(
                () -> {
                    service.accounts().applyMonthlyInterestToAllSavings();
                    System.out.println("[Scheduler] Monthly interest applied: "
                            + LocalDateTime.now());
                },
                30, 30, TimeUnit.DAYS
        );
    }
    public static void main(String[] args) {
        BankService service = new BankService();
        BankController controller = new BankController(service);

        seedDemoData(service, controller);
        //seedDemoData(controller, service);
        //startInterestScheduler(controller);
        startInterestScheduler(service);

        SwingUtilities.invokeLater(()->{
            applyLookAndFeel();
            //BankApp app =
                    new BankApp(controller).setVisible(true);
            //app.setVisible(true);
        });
    }

    private static void startInterestScheduler(BankController controller) {
        java.util.concurrent.ScheduledExecutorService scheduler = java.util.concurrent.Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                controller.accounts().applyMonthlyInterest();
                System.out.println("Monthly interests applied.");
            } catch (Exception e) {
                System.err.println("Error applying interests: " + e.getMessage());
            }
        }, 1, 30, java.util.concurrent.TimeUnit.DAYS); // In a real app this would be monthly, we use 30 days. For demo purposes we can use seconds, but let's stick to days.
    }

    private static void seedDemoData(BankService service, BankController controller) {
        // Create an investment company
        InvestmentCompany company = new InvestmentCompany("INV001", "Global Investments", 0.08, 2, 0.95);
        service.addCompany(company);

        // Create a client
        Client client = new Client(1, "John Doe");
        service.clients().addClient(client);

        // Create a savings account for the client using controller
        controller.accounts().openSavingsAccount(1, "SA001", 1000.0, 0.05);

        // Create employees
        service.employees().addEmployee(new Cashier(101, "Alice Smith", 2500.0, 3));
        service.employees().addEmployee(new Supervisor(102, "Bob Jones", 4500.0, 10));
    }

    private static void applyLookAndFeel() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ignored){

        }
    }
}
