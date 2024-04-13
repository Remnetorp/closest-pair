import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ClosestPair{

     /**
     * Method readInput reads an .in file and put the data into a collection.
     * Input The first line of input consists of a single integer, the number of Points(coordinates). 
     * Then follow N lines, the i-th line containing two integers, the x- and y-coordinates of the i-th Point.
     * All coordinates have absolute value less than 109.
     * 
     * @param output Decideds if extra information should be printed out or not in the terminal.
     * @return An array list containing the Points with x and y values from the input file.
     */
    public ArrayList<Point> readInput(Boolean output){
        ArrayList<Point> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int nCoordinates = scanner.nextInt();

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
        return list;
    }

    /**
     * Method closestPair is a recursive method describing the algorithm to find the pair of points that have
     * the least distance inbetween each other. It is not testing all possible combinations, instead it solves it 
     * with a divide and conquer approach.
     * 
     * @param list A list of points, which gets smaller and smaller with every recursive call.
     * @return min A double representing distance between two points. 
     */
    public double closestPair(List<Point> list){
        //Checks if the recursive call has a list containing 3 or less Points.
        if(list.size() <= 3){
            return checkDistance(list);
        }else{
            Collections.sort(list, (a,b) -> Double.compare(a.getX(), b.getX()));
            List<Point> xLeft = list.subList(0, list.size() / 2);
		    List<Point> xRight = list.subList(list.size() / 2, list.size());

            double minLeft = closestPair(xLeft);
            double minRight = closestPair(xRight);
            double min = Math.min(minLeft, minRight);

            //find the middle x to deicde if there are points cutoff by midline that are closer to each other than min distance. 
            double xMiddle = (xLeft.get(xLeft.size()-1).getX() + xRight.get(0).getX()) / 2;
            List<Point> midLine = new ArrayList<>();
            for(Point point : list){
                if (point.xDistance(xMiddle) < min){
                    midLine.add(point);
                }
            }
            Collections.sort(midLine, (a,b) -> Double.compare(a.getY(),b.getY()));

            //Check up to maximum 6 possible points underneath y, over y already checked. Only 6 points possible without being closer then min distance.
            for(int i = 0; i < midLine.size() ; i++){
                for(int j = 1; j < 7; j++){
                    int index = i + j;
                    if(index < midLine.size()){
                        double distance = (midLine.get(i).distanceTo(midLine.get(index)));
                        if (distance < min){
                            min = distance;
                        }
                    }
                }
            }
            return min;
        }
    }

    /**
     * Method checkDistance is a short method to try brute-force a result of shortest
     * distance between 3 or less points.
     * 
     * @param list A list of 3 points or less.
     * @return min A double representing distance between two points. 
     */
    public double checkDistance(List<Point> smallList){
        if(smallList.size() == 2){
            return smallList.get(0).distanceTo(smallList.get(1));
        }else{
            double distance = Double.MAX_VALUE;
            for(int i = 0; i < smallList.size(); i++) {
				for(int j = i; j < smallList.size(); j++) {
					if(i != j) {
						double newDistance = smallList.get(i).distanceTo(smallList.get(j));
						if (newDistance < distance) {
							distance = newDistance;
						}
					}
				}
			}
            return distance;
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
        ArrayList<Point> list = test.readInput(output);
        Double result = test.closestPair(list);
        DecimalFormat df = new DecimalFormat("0.000000");
        String formattedValue = df.format(result);
        System.out.println(formattedValue);
    }
}