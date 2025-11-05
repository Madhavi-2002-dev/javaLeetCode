package threadtopic;

public class OddEven {


	    private boolean oddTurn = true; // true means it's Odd thread's turn

	    // method to print odd numbers
	    public synchronized void printOdd() {
	        for (int i = 1; i <= 20; i += 2) {
	            while (!oddTurn) {
	                try {
	                    wait(); // wait until it's the odd thread's turn
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	            System.out.println("Thread B (Odd): " + i);
	            oddTurn = false; // now even's turn
	            notify(); // wake up even thread
	        }
	    }

	    // method to print even numbers
	    public synchronized void printEven() {
	        for (int i = 2; i <= 20; i += 2) {
	            while (oddTurn) {
	                try {
	                    wait(); // wait until it's the even thread's turn
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	            System.out.println("Thread A (Even): " + i);
	            oddTurn = true; // now odd's turn
	            notify(); // wake up odd thread
	        }
	    }

	    public static void main(String[] args) {
	        OddEven printer = new OddEven();

	        Thread oddThread = new Thread(() -> printer.printOdd());
	        Thread evenThread = new Thread(() -> printer.printEven());

	        oddThread.start();
	        evenThread.start();
	    }
	}



