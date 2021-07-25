import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.text.DateFormatter;

public class Librarysystem {

    static ArrayList<City> citylist ;
    static ArrayList<State> statelist ;
    static ArrayList<Cat> catlist; 
    static ArrayList<Publisher> publist; 
    static ArrayList<Author> authlist ; 
    static ArrayList<Book> booklist ; 
    static ArrayList<Library> liblist ; 
    static ArrayList<Customer> custlist;    
    static ArrayList<Bookstock> stocklist;
    static ArrayList<LibTran>  tranlist;     

    public static void main(String args[]){
        // catlist = new ArrayList<>(); 
        // publist = new ArrayList<>(); 
        // authlist = new ArrayList<>(); 
        // booklist = new ArrayList<>(); 
        // liblist = new ArrayList<>(); 
        // statelist = State.initializeFromFile();
        // citylist = City.initializeFromFile();
        // catlist = Cat.initializeFromFile();
        // System.out.println(citylist);
        // System.out.println(statelist);
        // System.out.println(catlist);

        
        // try{
        //     SimpleDateFormat df = new SimpleDateFormat("dd/mmm/yyyy");
        //     String sdate = System.console().readLine();
        //     Date dt = df.parse(sdate);
        //     System.out.println(new Date());
        // } catch (Exception c) {

        // }
        
        // just call bookstock
        // stocklist = new ArrayList<>();
        // stocklist.add(new Bookstock(1, 1, 100, new Date(), 100));
        // stocklist.add(Bookstock.add());
        // stocklist.add(Bookstock.add());
        // System.out.println(stocklist);
        // Bookstock.save(stocklist);

        // ArrayList<Bookstock> nblist = Bookstock.initializeFromFile() ;
        // // System.out.println(stocklist);
        // Bookstock.viewAvaiableBooks(nblist, 2);
        // Bookstock.viewAvaiableBooks(nblist, -1);
        // System.out.println("Book issued!");
        // Bookstock.IssueOrReceipt(nblist.get(1), -1);
        // Bookstock.viewAvaiableBooks(nblist, -1);
        // System.out.println("Book Received!");
        // Bookstock.IssueOrReceipt(nblist.get(1), 1);
        // Bookstock.viewAvaiableBooks(nblist, -1);
        // retrieve again

        stocklist = Bookstock.initializeFromFile() ;
        tranlist = LibTran.initializeFromFile();
        
        System.out.println(stocklist);
        System.out.println(tranlist);
        // now lets do some transaction
        // when user takes a book from the library
        // then we need to update entry in stocklist for that book 
        // and we need to add new entry in tranlist
        char type = 'I';
        char qty = 1 ;
        int libid = 1 ;
        int bookid = 3;
        int custid = 1 ;
        int transid = 1 ; // ideally this should be auto generated and increment sequentially
        LibTran tran = new LibTran(type,custid, transid, new Date(), libid, bookid, qty) ;
        tranlist.add(tran) ;
        int id  = Bookstock.findByID(stocklist, libid, bookid) ; // reduce or increse the stock
        Bookstock.IssueOrReceipt(stocklist.get(id),  type == 'I' ? -qty : qty) ;
        
        type = 'R';
        // qty = 1 ;
        transid = 2 ;
        tran = new LibTran(type, custid, transid, new Date(), libid, bookid, qty) ;
        tranlist.add(tran) ;
        LibTran.save(tranlist);
        id  = Bookstock.findByID(stocklist, libid, bookid) ;
        Bookstock.IssueOrReceipt(stocklist.get(id),  type == 'I' ? -qty : qty) ;
        Bookstock.save(stocklist);
        


        return ;
        // int choice = showMainMenu();

        // while (choice != 4) {
        //     choice = showMainMenu();
        // }
    }

    public static void check(ArrayList<Integer> list) {
        list.add(1);
    }
   
    public static int showMainMenu() {
        // about menu system
        // 1 for Master, 2 for Transaction and 3 for Reports
        System.out.println("=======================================================");
        System.out.println("==============Multi Location Library System============");
        System.out.println("=======================================================");
        System.out.println("Use Following options to interact with System!");
        System.out.println("1. For Master");
        System.out.println("2. For Transaction");
        System.out.println("3. For Reports");
        System.out.println("4. To Exit");
        System.out.println("=======================================================");
        System.out.println("================   By ITfyMe Studnets!  ===============");
        System.out.println("=======================================================");
        int userChoice = 4;
        try {
            userChoice = Integer.parseInt(System.console().readLine());
            switch (userChoice) {
                case 1:
                    handleMaster();
                    break ;
                case 2:
                    showTransactionMenu();
                    break ;
                case 3:
                    showReportMenu();
                    break ;
                case 4:
                    System.out.println("Exising the Library system. Goob bye!");
                    break;
                default:
                    System.out.println("Wrong input, input either 1 or 2 or 3 please!");
                    userChoice = -1 ;
                    break ;
            }
        } catch (Exception c) {
            userChoice = -1 ;
            System.out.println("Please enter either 1 or 2 or 3");
        }
        return userChoice;
    }

    public static void handleMaster() {
        int choice = showMasterMenu();
        while (choice != 100) {
            choice = showMasterMenu();
        }
    }
    public static int showMasterMenu() {
        System.out.println("=======================================================");
        System.out.println("                Master Maintenance");
        System.out.println("=======================================================");
        System.out.println("Use Following commands to maintain master");
        System.out.println("1. For City");
        System.out.println("2. For State");
        System.out.println("4. For Category");
        System.out.println("5. For Publisher");
        System.out.println("6. For Author");
        System.out.println("7. For Book");
        System.out.println("8. For Libray");
        System.out.println("9. For Viewing All");
        System.out.println("100. To Exit");
        System.out.println("=======================================================");
        System.out.println("================   By ITfyMe Studnets!  ===============");
        System.out.println("=======================================================");
        int userChoice = 100;
        try {
            userChoice = Integer.parseInt(System.console().readLine());
            if (userChoice != 100) {
                if (userChoice == 9) {
                    viewAll();
                } else {
                    doMaster(userChoice);
                }
            }
        } catch (Exception c) {
            System.out.println("Please enter either 1 or 2 or 3");
        }
        return userChoice;
    }

    public static void viewAll() {
        System.out.println(citylist);
        System.out.println(statelist);
        System.out.println(catlist);
        System.out.println(publist);
        return;
    }
    public static int doMaster(int master) { // this can be city, state, etc... depending on the number passed
        // what are all the different operation can you do?
        // you can create, update , delete or view ??
        int userChoice = 5;
        System.out.println("What do you want to do?");
        System.out.println("1 for create");
        System.out.println("2 for update/modify");
        System.out.println("3 for delete");
        System.out.println("4 for view");
        System.out.println("5 for save");
        System.out.println("6 for exit");
        try {
            userChoice = Integer.parseInt(System.console().readLine());
            switch (userChoice) {
                case 1:
                    doCreateMaster(master) ;
                    break ;
                case 2:
                    doUpdateMaster(master) ;
                    break ;
  
                    case 5:
                    doSaveMaster(master);
                    break ;
                default:
                    break ;
            }
        } catch (Exception c) {
            System.out.println("Please enter either 1 or 2 or 3");
        }
        return userChoice;
    }

    public static void doCreateMaster(int master) {
        int id ;
        String name;
        switch (master) {
            case 1:
                // create city master
                City city = City.add() ;
                if (city != null) {
                    citylist.add(city) ;
                }
                break ;
            case 2:
                // create state master
                State state = State.add() ;
                statelist.add(state) ;
                break ;
            case 3:
                // create Cat master
                Cat cat = Cat.add() ;
                catlist.add(cat);
                break ;
            default:
                break ;
        }
    }

    public static void doUpdateMaster(int master) {
        int id;
        int idx;

        switch(master) {
            case 1:
                System.out.println("Please enter the ID of the city for which you want to update");
                id = Integer.parseInt(System.console().readLine()) ;
                idx  = City.findByID(citylist, id) ;
                if (idx == -1) {
                    System.out.print("No city found with id " + id);
                } else {
                    citylist.set(idx,City.update(citylist.get(idx)));
                }
                break ;
            case 2:
                // udpate state master
                System.out.println("Please enter the ID of the State for which you want to update");
                id = Integer.parseInt(System.console().readLine()) ;
                idx  = State.findByID(statelist, id) ;
                if (idx == -1) {
                    System.out.print("No state found with id " + id);
                } else {
                    statelist.set(idx,State.update(statelist.get(idx)));
                }
                break ;
            case 3:
                System.out.println("Please enter the ID of the Category for which you want to update");
                id = Integer.parseInt(System.console().readLine()) ;
                idx  = Cat.findByID(catlist, id) ;
                if (idx == -1) {
                    System.out.print("No Category found with id " + id);
                } else {
                    catlist.set(idx,Cat.update(catlist.get(idx)));
                }
                break ;
        }
    }

    public static void doSaveMaster(int master) {
        switch (master) {
            case 1:
                // create city master
                City.save(citylist);
            case 2:
                // create city master
                State.save(statelist);
            case 3:
                // create city master
                Cat.save(catlist);
        }
    }

    public static void showTransactionMenu() {
        System.out.println("Transaction!");
    }
    public static void showReportMenu() {
        System.out.println("Report!");
    }
}