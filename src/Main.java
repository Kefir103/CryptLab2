import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static StringBuilder logs = new StringBuilder();
    public static void main(String[] args) {
        SubscriberA subscriberA = new SubscriberA("Абонент A");
        SubscriberB subscriberB = new SubscriberB("Абонент B");
        Main.diffie(subscriberA, subscriberB);
    }

    static void diffie(SubscriberA subA, SubscriberB subB){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите P: ");
        subA.setP(in.nextInt());
        subB.setP(subA.getP());
        Main.logToFile(subA.getLog());
        System.out.print("Введите A: ");
        subA.setA(in.nextDouble());
        subB.setA(subA.getA());
        Main.logToFile(subA.getLog());
        System.out.print("Введите X для абонента А и абонента B: ");
        subA.setX(in.nextDouble());
        subB.setX(in.nextDouble());
        Main.logToFile(subA.getLog());
        Main.logToFile(subB.getLog());
        Main.logToFile("Подсчитываем Y...");
        subA.computeY(subA.getA(), subA.getX(), subA.getP());
        Main.logToFile(subA.getLog());
        subB.computeY(subB.getA(), subB.getX(), subB.getP());
        Main.logToFile(subB.getLog());
        Main.logToFile("Пересылаем Y между абонентами...");
        subA.recieveY(subB.getY());
        Main.logToFile(subA.getLog());
        subB.recieveY(subA.getY());
        Main.logToFile(subB.getLog());
        Main.logToFile("Подсчитываем ключи...");
        subA.computeKey(subA.getRecievedY(), subA.getX(), subA.getP());
        Main.logToFile(subA.getLog());
        subB.computeKey(subB.getRecievedY(), subB.getX(), subB.getP());
        Main.logToFile(subB.getLog());
        Main.logToFile("Проверяем ключи на равность...");
        if (subA.getK() == subB.getK()){
            Main.logToFile("Все верно!");
        } else{
            Main.logToFile("Где-то закралась ошибочка");
        }
    }

    static void logToFile(String logString){
        Main.logs.append(logString + "\n");
        try (FileWriter fin = new FileWriter("res/logs")){
            fin.write(logs.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
