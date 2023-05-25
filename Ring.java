import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Ring {

  public static void main(String[] args) {
// TODO Auto-generated method stub
    int temp, i, j;

    Scanner in = new Scanner(System.in);
    System.out.println("Enter the number of process : ");
    int num = in.nextInt();

    Rr[] proc = new Rr[num];
// object initialisation
    for (i = 0; i < num; i++) {
      proc[i] = new Rr();
    }

// getting input from users
    for (i = 0; i < num; i++) {
      proc[i].index = i;
      System.out.println("Enter the id of process : ");
      proc[i].id = in.nextInt();
      proc[i].state = "active";
      proc[i].f = 0;
    }
// sorting the processes from on the basis of id
    Arrays.sort(proc, Comparator.comparingInt(o -> o.id));

    for (i = 0; i < num; i++) {
      System.out.print(" [" + i + "]" + " " + proc[i].id);
    }

    int init;
    int ch;
    int temp1;
    int temp2;

//    int[] arr = new int[num];
    proc[num - 1].state = "inactive";
    System.out.println("\n process " + proc[num - 1].id + " selected as leader");

    while (true) {
      System.out.println("\n 1.election 2.quit ");
      ch = in.nextInt();
      for (i = 0; i < num; i++) {
        proc[i].f = 0;
      }
      switch (ch) {
        case 1 : {
          System.out.println("\n Enter the Process number who initialised election : ");
          init = in.nextInt();
          if (init >= num) {
            System.err.println("Invalid Process Number");
            continue;
          }
          temp2 = init;
          temp1 = (init + 1) % num;
          i = 0;
          while (temp2 != temp1) {

            if ("active".equals(proc[temp1].state) && proc[temp1].f == 0) {
              System.out.println("\nProcess " +
                  proc[init].id + " send message to " + proc[temp1].id);
              proc[temp1].f = 1;
              init = temp1;
//              arr[i] = proc[temp1].id;
//              i++;
            }

            temp1 = (temp1 + 1) % num;
          }
          System.out.println("\nProcess " + proc[init].id + " send message to " + proc[temp1].id);
//          arr[i] = proc[temp1].id;
//          i++;
          int max = -1;
// finding maximum for co-ordinator selection
//          for (j = 0; j < i; j++) {
//            if (max < arr[j]) {
//              max = arr[j];
//            }
//          }

          int ct = -1;
          for (int k = 0; k < num; k++) {
            if (proc[k].state.equals("active") && max < proc[k].id) {
              ct = k;
              max = proc[k].id;
            }
          }
          if (ct == -1) {
            System.out.println("All processes are inactive");
            return;
          }

          // co-ordinator is found then printing on console
          System.out.println("\n process " + max + " selected as leader");
//          for (i = 0; i < num; i++) {
//            if (proc[i].id == max) {
//              proc[i].state = "inactive";
//            }
//          }
          proc[ct].state = "inactive";
          break;
        }
        case 2 : {
          System.out.println("Program terminated ...");
          return;
        }
        default : System.out.println("\n invalid response \n");
      }
    }
  }
}

class Rr {

  public int index;
  public int id;
  public int f;
  String state;
}
