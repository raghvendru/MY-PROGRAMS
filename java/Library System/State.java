import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class State implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static final String statefile = "./state.data" ;
    int ID;
    String name;
    public State(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "State [ID=" + ID + ", Name=" + name + "]";
    }

    // user interaction for State Object
    public static State add() {
        int     id;
        String  name;
        System.out.println("Please enter state id");
        id = Integer.parseInt(System.console().readLine());
        System.out.println("Please enter state name");
        name = System.console().readLine();
        return new State(id,name);
    }

    public static State update(State state) {
        int     id;
        String  name;
        System.out.println("City Information is:");
        System.out.println(state);  
        System.out.println("Please update state name");
        name = System.console().readLine();
        System.out.println("read name is " + name);
        state.name = name ;
        System.out.println("Updated name is " + state.name);
        return state ;
    }

    // callee will pass the citylist contianing list of cities to be saved / serialized
    public static void save(ArrayList<State> statelist) {
        System.out.print("Saving master state list in the file!!!");
        try {
            FileOutputStream fileOut =new FileOutputStream(statefile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(statelist);
            out.close();
            fileOut.close();
            System.out.println("State data is saved!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static ArrayList<State> initializeFromFile() {
        try {
            File f = new File(statefile) ;
            if (!f.exists()) {
                return new ArrayList<State>();
            }
            FileInputStream fileIn = new FileInputStream(statefile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<State> statelist  = (ArrayList<State>) in.readObject();
            in.close();
            fileIn.close();
            return statelist ;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("City class not found");
            c.printStackTrace();
            return null;
        }
    }

    public static int findByID(ArrayList<State> list, int id) {
        int idx = -1;
        int size = list.size() ;
        for (int i = 0; i < size; i++) {
            if (list.get(i).getID() == id) {
                idx = i ;
            }
        }
        return idx ;
    }
}
