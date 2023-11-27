package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BankingImpl extends UnicastRemoteObject implements Banking {
    private int id;
    private String name;
    private double balance;


    protected BankingImpl() throws RemoteException {
        super();
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void createAccount(int id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0.0;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("O valor deve ser maior que 0 para ser depositado!");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount < 0) {
            System.out.println("O valor do saque deve ser positivo!");
        } else if (amount > this.balance || 0 >= this.balance) {
            System.out.println("Operação inválida! Não há saldo suficiente!");
        } else {
            this.balance -= amount;
            System.out.println("Saque realizado com sucesso!");
        }

    }

    @Override
    public void transfer(Banking account, double amount) {
        if (amount < 0) {
            System.out.println("O valor do saque deve ser positivo!");
        } else if (amount > this.balance || 0 >= this.balance) {
            System.out.println("Operação inválida! Não há saldo suficiente!");
        } else {
            try {
                this.balance -= amount;
                account.deposit(amount);
                System.out.println("Transferência realizada com sucesso!");
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
