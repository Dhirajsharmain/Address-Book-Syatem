/**
 * ****************************************************************************
 * Purpose: The purpose of this class to create Address Book System and add
 * multiple persons details with their state or city reference.
 *
 * @author Dhiraj
 * @version 1.0
 * @since 22-06-2021
 * ****************************************************************************
 */
package bridgelabz.services;

import bridgelabz.exception.ValidationException;
import bridgelabz.model.Person;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

public class AddressBookMain {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Person> personMap = new HashMap();
    private static Map<String, Map<String, Person>> addressBookMap = new HashMap();

    /**
     * This is the main method, Here is the starting point of the program.
     *
     * @param args
     */
    public static void main(String[] args) throws ValidationException, IOException {

        boolean isExit = false;

        do {
            System.out.println("\n\t\tAddress Book System");
            System.out.println("\n\t\tEnter A to Add Person ");
            System.out.println("\t\tEnter E to Edit Person");
            System.out.println("\t\tEnter D to Delete Person");
            System.out.println("\t\tEnter S to Search Person");
            System.out.println("\t\tEnter C to Count Persons In City");
            System.out.println("\t\tEnter P to Print Address Book With and Without Sorting");
            System.out.println("\t\tEnter Q to Quit ");
            System.out.print("\t\tPlease Select One Option : ");
            char userInput = scanner.nextLine().toUpperCase().charAt(0);
            switch (userInput) {
                case 'A':
                    //add
                    addBook();
                    break;
                case 'E':
                    //edit
                    System.out.print("\nEnter the first name of the person to edit : ");
                    String firstName = scanner.nextLine();
                    System.out.print("\nEnter the city name of the person to edit : ");
                    String cityName = scanner.nextLine();
                    editContact(firstName, cityName);
                    break;
                case 'D':
                    //delete
                    System.out.print("\nEnter the first name of the person to delete : ");
                    String personName = scanner.nextLine();
                    System.out.print("\nEnter the city name of the person to delete : ");
                    String city = scanner.nextLine();
                    deletePerson(personName, city);
                    break;
                case 'S':
                    //Search
                    System.out.print("\nEnter the city name of the person to search : ");
                    String pCity = scanner.nextLine();
                    System.out.println("\nPersons in city " + pCity + " is : " + searchPerson(pCity));
                    break;
                case 'C':
                    System.out.print("\nEnter the city name to count persons : ");
                    String countCity = scanner.nextLine();
                    System.out.println("\nNumber of Persons in city " + countCity + " is " + personsCountByCity(countCity));
                    break;
                case 'P':
                    //print
                    writeCSVIntoFile("csvOutput.csv");
                    writeIntoFile();
                    System.out.print("\nEnter the city name to sort : ");
                    String sortCity = scanner.nextLine();
                    System.out.println("\n\t\t Without sorting : " + addressBookMap.toString());
                    System.out.println("\n\t\t Sorted By Person Name : " + sortByPersonName(sortCity));
                    System.out.println("\n\t\t sorted By City : " + sortByCity());

                    break;
                case 'R':
                    readCSV("csvOutput.csv");
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
     * Method For editing the existing person details on the basis of first name.
     */
    private static void editContact(String firstName, String cityName) throws ValidationException {
        try {
            personMap = addressBookMap.get(cityName);
            System.out.println(personMap.toString());
            if (addressBookMap.get(cityName).get(firstName) != null) {
                Person editedPerson = contactFields();
                personMap.put(editedPerson.getFirstName(), editedPerson);
                addressBookMap.put(editedPerson.getCity(), personMap);
            } else {
                System.out.println("Record Not Found");
            }
            System.out.println("\n\t\t" + addressBookMap.toString());
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    /**
     * Method for taking person details in Person-object format.
     *
     * @return : Person Object
     */
    private static Person contactFields() {
        Person person = new Person();
        System.out.print("Enter First Name : ");
        person.setFirstName(scanner.nextLine());

        System.out.print("Enter Last Name : ");
        person.setLastName(scanner.nextLine());

        System.out.print("Enter Address : ");
        person.setAddress(scanner.nextLine());

        System.out.print("Enter State : ");
        person.setState(scanner.nextLine());

        System.out.print("Enter City : ");
        person.setCity(scanner.nextLine());

        System.out.print("Enter ZipCode : ");
        person.setZip(scanner.nextLine());

        System.out.print("Enter Phone Number : ");
        person.setPhone(scanner.nextLine());
        return person;
    }

    /**
     * Method for deleting the person from existing address book
     */
    private static void deletePerson(String firstName, String cityName) throws ValidationException {

        try {
            Person newPerson = addressBookMap.get(cityName).get(firstName);
            System.out.println(newPerson.toString());
            if (addressBookMap.get(cityName).get(firstName) != null) {
                addressBookMap.get(cityName).remove(firstName);
                System.out.println("Deleted Successfully");
            } else {
                System.out.println("Record not exist");
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }

    }

    /**
     * Method for taking person details and store them into storage with
     * their city name reference.
     * In this program used hashmap. Multiple person can add.
     */
    private static void addBook() throws ValidationException {
        try {
            Map<String, Person> newPersonMap = new HashMap();
            Person newPerson;
            newPerson = contactFields();

            if (addressBookMap.get(newPerson.getCity()) != null)
                newPersonMap = addressBookMap.get(newPerson.getCity());

            newPersonMap.put(newPerson.getFirstName(), newPerson);
            addressBookMap.put(newPerson.getCity(), newPersonMap);

            System.out.println("\n\t\t" + addressBookMap.toString());
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    /**
     * Method for search person in a city across address book by using stream.
     *
     * @param city : name of city to be search.
     * @return : Hashmap containing personList of city.
     */
    private static Map<String, Map<String, Person>> searchPerson(String city) throws ValidationException {
        Map<String, Map<String, Person>> personsByCity = new HashMap();
        try {
            addressBookMap.entrySet().stream()
                    .filter(e -> e.getKey().equalsIgnoreCase(city))
                    .forEach(entry -> personsByCity.put(entry.getKey(), entry.getValue()));
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
        return personsByCity;
    }

    /**
     * Method for counting Number of personList in particular city.
     *
     * @param city : name of city to be search
     * @return : count
     */
    private static int personsCountByCity(String city) throws ValidationException {
        Map<String, Map<String, Person>> personCount = searchPerson(city);
        return personCount.get(city).size();
    }

    /**
     * Method for sorting the address book by first name of person
     *
     * @param city : Name of address book as city of person
     */
    private static Map<String, Person> sortByPersonName(String city) throws ValidationException {
        try {
            Map<String, Person> temp = addressBookMap.get(city);
            Map<String, Person> sorted = temp.entrySet().stream()
                    .sorted(comparingByKey())
                    .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
            return sorted;
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    /**
     * Method for sorting the address book by City name
     */
    private static Map<String, Map<String, Person>> sortByCity() throws ValidationException {
        try {
            Map<String, Map<String, Person>> sorted = addressBookMap.entrySet().stream()
                    .sorted(comparingByKey())
                    .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
            return sorted;
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    /**
     * Method for writing the address book data into a json file using jackson
     * library.
     *
     * @throws ValidationException
     */
    private static void writeIntoFile() throws ValidationException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("outputData.json"), addressBookMap);
            System.out.println("successfully");
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    /**
     * Method for writing addressBook data into CSV file using openCSV library.
     *
     * @param filePath : file name or full file path
     */
    private static void writeCSVIntoFile(String filePath) throws ValidationException {
        File file = new File(filePath);
        try {
            List<Person> list = addressBookMap.entrySet().stream().flatMap(e -> e.getValue().entrySet().stream()).map(m -> m.getValue()).collect(Collectors.toList());
            list.stream().forEach(e -> System.out.println(e));

            CsvMapper mapper = new CsvMapper();
            mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

            CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                    .addColumn("firstName")
                    .addColumn("lastName")
                    .addColumn("address")
                    .addColumn("city")
                    .addColumn("state")
                    .addColumn("zip")
                    .addColumn("phone")
                    .build();

            ObjectWriter writer = mapper.writerFor(Person.class).with(schema);

            writer.writeValues(file).writeAll(list);

            System.out.println("Users saved to csv file under path: " + file);
        } catch (IOException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    /**
     * Method for reading CSV file into program
     * @param filePath : file path
     * @throws IOException
     */
    private static void readCSV(String filePath) throws IOException {
        File csvFile = new File(filePath);

        CsvMapper csvMapper = new CsvMapper();

        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(Person.class)
                .withHeader()
                .withColumnSeparator(',')
                .withComments();

        MappingIterator<Person> addressBookItem = csvMapper
                .readerWithTypedSchemaFor(Person.class)
                .with(csvSchema)
                .readValues(csvFile);

        List<Person> users = addressBookItem.readAll();
        users.forEach(System.out::println);
    }

}
