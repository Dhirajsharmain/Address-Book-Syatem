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
            addPersonDetail();
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
