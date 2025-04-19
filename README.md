№Veterinary Clinic System
Description:
This is a Java application for managing a veterinary clinic, which processes information about animals (patients), their owners, and their visits. The system allows tracking the treatment history of the animals and saving this data to a file for future use.

Key Features:
Animals:

The Animal class stores basic information about the animals: name, type, and age.

Three subclasses:

Dog with an additional attribute for breed.

Cat with an attribute for sterilization status.

ExoticAnimal with an attribute for special needs.

Owners:

The Owner class stores information about the pet's owner: name and mobile phone number.

Visits:

The Visit class contains details about the visit: date, animal, veterinarian, and problem.

Treatment:

The Treatment interface defines a method for applying treatment.

Two classes implement the interface:

Medication for medicines (with information about dosage and duration).

Surgery for operations (with information about time duration).

Recording Visit History to a File:

The visit history is saved to a file named visits.txt with the format: date, animal, owner, veterinarian, problem, and treatment.

Requirements:
Java 11 or higher.

The ability to write information to a file (the directory path must contain valid characters).

How to Run:

Use an IDE like IntelliJ IDEA or Eclipse.

You can also use the javac command for compilation if you're working with the command line.

Enter information about animals, owners, and visits.

Check the visits.txt file for the recorded history.

Example Usage:
The program allows the user to add animals, input information about their owners, and register visits to the clinic, including selecting a treatment (medication or surgery).
