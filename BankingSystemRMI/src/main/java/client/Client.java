package client;

import bank.Bank;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Bank bank = (Bank) Naming.lookup("rmi://localhost:1234/Banking");
            Scanner scanner = new Scanner(System.in);
            int option = -99;

            do{
                System.out.println("=============== Bem vindos ao Banco ===============");
                System.out.println("1 - Criar conta");
                System.out.println("2 - Depositar");
                System.out.println("3 - Sacar");
                System.out.println("4 - Transferir");
                System.out.println("5 - Saldo");
                System.out.println("6 - Sair");
                System.out.println("===================================================");
                System.out.print("Digite a opção desejada: ");
                option = scanner.nextInt();
                scanner.nextLine();

                switch(option){
                    case 1:
                        System.out.print("Digite o nome do cliente: ");
                        String name = scanner.nextLine();
                        System.out.println(bank.createAccount(name));
                        break;
                    case 2:
                        System.out.print("Digite o número da conta: ");
                        int accountNumber = scanner.nextInt();
                        System.out.print("Digite o valor a ser depositado: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println(bank.deposit(accountNumber, amount));
                        break;
                    case 3:
                        System.out.print("Digite o número da conta: ");
                        accountNumber = scanner.nextInt();
                        System.out.print("Digite o valor a ser sacado: ");
                        amount = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println(bank.withdraw(accountNumber, amount));
                        break;
                    case 4:
                        System.out.print("Digite o número da conta de origem: ");
                        int accountSendId = scanner.nextInt();
                        System.out.print("Digite o número da conta de destino: ");
                        int accountRecvId = scanner.nextInt();
                        System.out.print("Digite o valor a ser transferido: ");
                        amount = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println(bank.transfer(accountSendId, accountRecvId, amount));
                        break;
                    case 5:
                        System.out.print("Digite o número da conta: ");
                        accountNumber = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println(bank.getBalance(accountNumber));
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }

            }while(option != 0);

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
