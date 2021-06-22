/**
 * ****************************************************************************
 * Purpose: The purpose of this class to create Address Book System and add
 * multiple persons details with their state or city reference.
 *
 * @author Dhiraj
 * @version 1.0
 * @since 22-06-2021 **********************************************************
 */
package bridgelabz.services;

import bridgelabz.model.Person;

import java.util.*;

public class AddressBookMain {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String ,Person> personMap = new HashMap();

    /**
     * This is the main method, Here is the starting point of the program.
     *
     * @param args
     */
    public static void main(String[] args){

        boolean isExit = false;

        do {
            System.out.println("\n\t\tAddress Book System");
            System.out.println("\n\t\tEnter A to Add Person ");
            System.out.println("\t\tEnter E to Edit Person");
            System.out.println("\t\tEnter D to Delete Person");
            System.out.println("\t\tEnter S to Show Person Detail");
            System.out.println("\t\tEnter Q to Quit ");
            System.out.print("\t\tPlease Select One Option : ");
            char userInput = scanner.nextLine().toUpperCase().charAt(0);
            switch (userInput) {
                case 'A':
                    //add
                    addPersonDetail();
                    break;
                case 'E':
                    //edit
                    break;
                case 'D':
                    //delete
                    break;
                case 'S':
                    //Show
                    System.out.println("\n\t\t" + personMap.get(personMap.size() - 1).toString());
                    break;
                case 'Q':
                    //quit
                    isExit = true;
                    break;
                default:
                    System.out.println("Please select correct option");
            }
        } while (!isExit);


    }

    /**
     * Method for taking person details and store them into storage.
     * In this program used hashmap.
     */
    private static void addPersonDetail(){
        Person person = new Person();
        System.out.print("Enter First Name : ");
        person.setFirstName(scanner.nextLine());
        System.out.print("Enter Last Name : ");
        person.setLastName(scanner.nextLine());
        System.out.print("Enter Address : ");
        person.setAddress(scanner.nextLine());
        System.out.print("Enter City : ");
        person.setCity(scanner.nextLine());
        System.out.print("Enter ZipCode : ");
        person.setZip(scanner.nextLine());
        System.out.print("Enter Phone Number : ");
        person.setPhone(scanner.nextLine());

        personMap.put(person.getFirstName(),person);
        System.out.println(personMap.toString());
    }
}
