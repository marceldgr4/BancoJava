package com.Banco;

import com.Banco.controller.BankController;
import com.Banco.model.Investment.InvestmentCompany;
import com.Banco.model.domain.Account.BankAccount;
import com.Banco.model.domain.Account.SavingsAccount;
import com.Banco.model.domain.Person.Client;
import com.Banco.model.domain.Employee.Cashier;
import com.Banco.model.domain.Employee.Supervisor;
import com.Banco.service.BankService;

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
        // Create an investment company
        InvestmentCompany company = new InvestmentCompany("INV001", "Global Investments", 0.08, 2, 0.95);
        service.addCompany(company);

        // Create a client
        Client client = new Client(1, "John Doe");
        service.clients().registerClient(client);

        // Create a savings account for the client
        SavingsAccount savings = new SavingsAccount("SA001", client, 1500.0, 0.05);
        service.accounts().registerAccount(savings);

        // Create employees
        service.employees().registerEmployee(new Cashier(101, "Alice Smith", 2500.0, 3));
        service.employees().registerEmployee(new Supervisor(102, "Bob Jones", 4500.0, 10));
    }

    private static void applyLookAndFeel() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ignored){

        }
    }
}
