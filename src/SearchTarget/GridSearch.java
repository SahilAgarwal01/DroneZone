package SearchTarget;
import java.util.Scanner;
public class GridSearch {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       // Get the grid dimensions from user
       System.out.println("Enter grid dimensions (x y):");
       int x = scanner.nextInt();
       int y = scanner.nextInt();
       int[][] grid = new int[x][y];
       // Get target coordinates from user
       System.out.println("Enter target coordinates (X Y):");
       int targetX = scanner.nextInt();
       int targetY = scanner.nextInt();
       // Initialize drone positions
       int[] droneX = new int[4];
       int[] droneY = new int[4];
       // Placing drones in the corners of the grid
       droneX[0] = 0;
       droneY[0] = 0;
       droneX[1] = x - 1;
       droneY[1] = 0;
       droneX[2] = 0;
       droneY[2] = y - 1;
       droneX[3] = x - 1;
       droneY[3] = y - 1;
       // Display initial drone positions
       for (int i = 0; i < 4; i++) {
           System.out.println("Initial drone " + (i + 1) + " position: (" + droneX[i] + ", " + droneY[i] + ")");
       }
       boolean targetFound = false;
       // Loop to find the target
       while (!targetFound) {
           // Find the closest drone to the target
           int closestDrone = findClosestDrone(droneX, droneY, targetX, targetY);
           // Check if the closest drone reached the target
           if (droneX[closestDrone] == targetX && droneY[closestDrone] == targetY) {
               System.out.println("Target reached by drone " + (closestDrone + 1) + "! Drone stopped.");
               targetFound = true;
               break;
           }
           // Calculate the next coordinates for the closest drone
           int nextX = calculateNextCoordinate(droneX[closestDrone], targetX, x);
           int nextY = calculateNextCoordinate(droneY[closestDrone], targetY, y);
           // Display movement of the closest drone
           System.out.println("Move drone " + (closestDrone + 1) + " to: (" + nextX + ", " + nextY + ")");
           droneX[closestDrone] = nextX;
           droneY[closestDrone] = nextY;
           // Print the current state of the grid
           printGrid(grid, droneX, droneY);
       }
       // Close the scanner
       scanner.close();
   }
   // Method to calculate the next coordinate based on the target
   private static int calculateNextCoordinate(int current, int target, int max) {
       if (current < target) {
           return Math.min(current + 1, max - 1);
       } else if (current > target) {
           return Math.max(current - 1, 0);
       } else {
           return current;
       }
   }
   // Method to print the current state of the grid
   private static void printGrid(int[][] grid, int[] droneX, int[] droneY) {
       System.out.println("Current Grid:");
       for (int i = 0; i < grid.length; i++) {
           for (int j = 0; j < grid[i].length; j++) {
               boolean isDrone = false;
               // Check if a drone is at the current grid position
               for (int k = 0; k < droneX.length; k++) {
                   if (droneX[k] == i && droneY[k] == j) {
                       isDrone = true;
                       break;
                   }
               }
               // Print 'D' if a drone is present, '.' otherwise
               if (isDrone) {
                   System.out.print("D ");
               } else {
                   System.out.print(". ");
               }
           }
           System.out.println();
       }
       System.out.println();
   }
   // Method to find the closest drone to the target
   private static int findClosestDrone(int[] droneX, int[] droneY, int targetX, int targetY) {
       int closestDrone = 0;
       int minDistance = Integer.MAX_VALUE;
       // Calculate distances and find the minimum
       for (int i = 0; i < droneX.length; i++) {
           int distance = Math.abs(droneX[i] - targetX) + Math.abs(droneY[i] - targetY);
           if (distance < minDistance) {
               minDistance = distance;
               closestDrone = i;
           }
       }
       return closestDrone;
   }
}