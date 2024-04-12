import java.util.LinkedList;
import java.util.Scanner;

public class ClosestPair{
    private int nCoordinates;
    private LinkedList<Point> list = new LinkedList<>();

    public void readInput(Boolean output){
        Scanner scanner = new Scanner(System.in);
        nCoordinates = scanner.nextInt();

        for(int i=0; i < nCoordinates; i++){
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();

            Point point = new Point(x,y);
            list.add(point);

            // Print the preferences to make sure it works, call it by sending "output" in the argument when running from terminal.
            if (output){
                System.out.println("Point " + i+1 + " x:" + x + " y:" + y);
            }
        } 

        scanner.close();
    }

    public double closestPair(){
        if(nCoordinates == 0 || nCoordinates == 1){
            return -1;
        }else if(nCoordinates == 1){
            return list.get(0).distanceTo(list.get(1));
        }else{
            double minDistance = 10;

            return minDistance;
        }
    }


    public static void main(String[] args){
        ClosestPair test = new ClosestPair();
        boolean output = false;
        for(String arg : args){
            if (arg.equals("output")){
                output = true;
            }
        }
        test.readInput(output);



    }
}