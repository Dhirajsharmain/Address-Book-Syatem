# Address-Book-Syatem

**UC1-CreateAContact**

_Description_ : Ability to create a Contacts in Address Book with first and last names, address, city, state, zip, phone number and email…

**UC2-AddNewContact**

_Description_ : Ability to add a new Contact to Address Book
- Use Console to add person details from AddressBookMain class
- Use Object-Oriented Concepts to manage relationship between AddressBook and Contact Person

**UC3-EditExistingContact**

_Description_ : Ability to edit existing contact person using their name - Use Console to edit person details.

**UC4-DeleteAPerson**

_Description_ : Ability to delete a person using person's name 
- Use Console to delete a person

**UC5-AddMultiplePerson**

_Description_ : Ability to add multiple person to Address Book
- Use Console to add person details one at a time
- Use Collection Class to maintain multiple contact persons in Address Book

**UC6-AddMultipleAddressBook**

_Description_ : Refactor to add multiple Address Book to the System. Each Address Book has a unique Name 
- Use Console to add new Address Book 
- Maintain Dictionary of Address Book Name to Address Book

**UC7-NoDuplicateEntry**

_Description_ : Ability to ensure there is no Duplicate Entry of the same Person in a particular Address Book 
- Duplicate Check is done on Person Name while adding person to Address Book.
- Use Collection Methods to Search Person by Name for Duplicate Entry
- Override equals method to check for Duplicate - Use Java Streams

**UC8-SearchPerson**

_Description_ : Ability to search Person in a City or State across the multiple AddressBook 
- Search Result can show multiple person in the city or state
- Use Java Streams

**UC9-ViewPersonByCity**

_Description_ : Ability to view Persons by City or State - Maintain Dictionary of City and Person as well as State and Person
- Use Collection Library to maintain Dictionary
- Use Java Streams

**UC10-NumberOfContactPersons**

_Description_ : Ability to get number of contact persons i.e. count by City or State
- Search Result will show count by city and by state
- Use Java Streams

**UC11-SortTheEntries**

_Description_ : Ability to sort the entries in the address book alphabetically by Person’s name
- Use Console to sort person details by name
- Use Collection Library for Sorting
- Override toString method to finally Print Person Entry in Console
- Use Java Streams