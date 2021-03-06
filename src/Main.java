import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static StringBuilder logs = new StringBuilder();

    void diffie(Subscriber subA, Subscriber subB) throws Exception{
        Scanner in = new Scanner(System.in);

        System.out.print("Введите P, такое что P = 2q+1, где q - простое число: ");
        subA.setP(in.nextInt());
        subB.setP(subA.getP());
        Main.logToFile(subA.getLog());

        System.out.print("Введите A ((1 < A < P-1) && (A^q mod P != 1)): ");
        subA.setA(in.nextDouble());
        subB.setA(subA.getA());
        Main.logToFile(subA.getLog());

        System.out.print("Введите X для абонента А и абонента B: ");
        subA.setX(in.nextDouble());
        subB.setX(in.nextDouble());

        Main.logToFile("Подсчитываем Y...");
        subA.computeY(subA.getA(), subA.getX(), subA.getP());
        subB.computeY(subB.getA(), subB.getX(), subB.getP());

        Main.logToFile("Пересылаем Y между абонентами...");
        subA.recieveY(subB.getY());
        subB.recieveY(subA.getY());

        Main.logToFile("Подсчитываем ключи...");
        subA.computeKey(subA.getRecievedY(), subA.getX(), subA.getP());
        subB.computeKey(subB.getRecievedY(), subB.getX(), subB.getP());

        Main.logToFile("Проверяем ключи на равность...") ;
        if (subA.getK() == subB.getK()){
            Main.logToFile("Все верно!");
        } else{
            Main.logToFile("Где-то закралась ошибочка");
        }

    }

    static void logToFile(String logString){
        logs.append(logString + "\n");
        try (FileWriter fin = new FileWriter("res/logs")){
            fin.write(logs.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        Subscriber subscriberA = new Subscriber("Абонент A");
        Subscriber subscriberB = new Subscriber("Абонент B");
        main.diffie(subscriberA, subscriberB);
    }


}
