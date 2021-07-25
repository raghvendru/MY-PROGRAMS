import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Librarysystem {
    static ArrayList<City> citylist ;
    static ArrayList<State> statelist ;
    static ArrayList<Cat> catlist; 
    static ArrayList<Publisher> publist; 
    static ArrayList<Author> authlist ; 
    static ArrayList<Book> booklist ; 
    static ArrayList<Library> liblist ; 
    static ArrayList<Customer> custlist; 
    public static void main(String args[]){
        //catlist = new ArrayList<>(); 
        //publist = new ArrayList<>(); 
        //authlist = new ArrayList<>(); 
        booklist = Book.initializeFromFile(); 
        liblist = Library.initializeFromFile(); 
        publist = Publisher.initializeFromFile();
        authlist = Author.initializeFromFile();
        statelist = State.initializeFromFile();
        citylist = City.initializeFromFile();
        catlist = Cat.initializeFromFile();
        System.out.println(citylist);
        System.out.println(statelist);
        System.out.println(catlist);
        System.out.println(publist);
        System.out.println(authlist);
        System.out.println(liblist);
        System.out.println(booklist);
        int choice = showMainMenu();
        while (choice != 4 ) {
            choice = showMainMenu();
        }
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
        System.out.println("3. For Category");
        System.out.println("4. For Publisher");
        System.out.println("5. For Author");
        System.out.println("6. For Book");
        System.out.println("7. For Libray");
        System.out.println("8. For Viewing All");
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
        System.out.println(authlist);
        System.out.println(liblist);
        System.out.println(booklist);
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
                break;
            case 4:
                Publisher publisher=Publisher.add();
                publist.add(publisher);
                break;    
            case 5:
                Author author=Author.add();
                authlist.add(author) ;  
                break ;
            case 6:
                Book book=Book.add(authlist,publist,catlist);
                booklist.add(book) ;  
                break ;    
            case 7:
                Library library=Library.add();
                liblist.add(library);
                break;   
            
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
                idx  = Cat.findByID(catlist, id);
                if (idx == -1) {
                    System.out.print("No Category found with id " + id);
                } else {
                    catlist.set(idx,Cat.update(catlist.get(idx)));
                }
            break;    
            case 4:
                System.out.println("Please enter the ID of the Publisher for which you want to update");
                id = Integer.parseInt(System.console().readLine()) ;
                idx  = Publisher.findByID(publist, id);
                if (idx == -1) {
                System.out.print("No Category found with id " + id);
                } else {
                    publist.set(idx,Publisher.update(publist.get(idx)));
                }
            break ;
            case 5:
                System.out.println("Please enter the ID of the Author for which you want to update");
                id = Integer.parseInt(System.console().readLine()) ;
                idx  = Author.findByID(authlist, id);
                if (idx == -1) {
                System.out.print("No Category found with id " + id);
                } else {
                    authlist.set(idx,Author.update(authlist.get(idx)));
                }                
            break ;
            case 6:
                System.out.println("Please enter the ID of the Book for which you want to update");
                id = Integer.parseInt(System.console().readLine()) ;
                idx  = Book.findByID(booklist, id);
                if (idx == -1) {
                System.out.print("No Category found with id " + id);
                } else {
                    booklist.set(idx,Book.update(booklist.get(idx)));
                }                
            case 7:
                System.out.println("Please enter the ID of the Library for which you want to update");
                id = Integer.parseInt(System.console().readLine()) ;
                idx  = Library.findByID(liblist, id);
                if (idx == -1) {
                System.out.print("No Category found with id " + id);
                } else {
                    liblist.set(idx,Library.update(liblist.get(idx)));
                }
                
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
            case 4:
                //save publisher
                Publisher.save(publist);   
            case 5:
                //save author
                Author.save(authlist);
            case 6:
                //save book
                Book.save(booklist);    
            case 7:
                //save Library
                Library.save(liblist);     
        }
    }

    public static void showTransactionMenu() {
        System.out.println("Transaction!");
    }
    public static void showReportMenu() {
        System.out.println("Report!");
    }
}