import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

class Animal {
    private String name;
    private String type;
    private int age;

    public Animal(String name, String type, int age) {
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public String getName() { return name; }

    public String getType() { return type; }

    public int getAge() { return age; }
}

class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, "dog", age);
        this.breed = breed;
    }

    public String getBreed() { return breed; }

}

class Cat extends Animal {
    private boolean isSterilized;

    public Cat(String name, int age, boolean isSterilized) {
        super(name, "cat", age);
        this.isSterilized = isSterilized;
    }

    public boolean getIsSterilized() { return isSterilized; }

}

class ExoticAnimal extends Animal {
    private String specialNeeds;

    public ExoticAnimal(String name, int age, String specialNeeds) {
        super(name, "exotic", age);
        this.specialNeeds = specialNeeds;
    }

    public String getSpecialNeeds() { return specialNeeds; }

}

class Owner {
    private String name;
    private String mobile;

    public Owner(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() { return name; }

    public String getMobile() { return mobile; }

}

class Visit {
    private LocalDate date;
    private Animal animal;
    private String doctor;
    private String problem;

    public Visit(LocalDate date, Animal animal, String doctor, String problem) {
        this.date = date;
        this.animal = animal;
        this.doctor = doctor;
        this.problem = problem;
    }

    public LocalDate getDate() { return date; }

    public Animal getAnimal() { return animal; }

    public String getDoctor() { return doctor; }

    public String getProblem() { return problem; }

}

interface Treatment {
    public String applyTreatment();
}

class Medication implements Treatment {
    private String medicine;

    public Medication(String medicine) {
        this.medicine = medicine;
    }

    public String getMedicine() { return medicine; }

    @Override
    public String applyTreatment() {
        int days,dosage;
        Scanner scan = new Scanner(System.in);
        System.out.println("How many days is the medication for?");
        days= scan.nextInt();
        System.out.println("What is the dosage?(in ml)");
        dosage= scan.nextInt();
        return "Medication: " + medicine + ", days: " + days + ", dosage: " + dosage + " ml a day.";
    }
}

class Surgery implements Treatment {
    private String surgeryType;

    public Surgery(String surgeryType) {
        this.surgeryType = surgeryType;
    }

    public String getSurgeryType() { return surgeryType; }

    @Override
    public String applyTreatment() {
        int time;
        Scanner scan = new Scanner(System.in);
        System.out.println("How much time does the " + surgeryType + " take?(in minutes)");
        time = scan.nextInt();
        return "Surgery type: " + surgeryType + " takes " + time + " minutes.";
    }
}

public class Main {
    public static void main(String[] args) {
        int patients;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to our Veterinary Clinic!\n");
        System.out.println("How many patients do you want to add? ");
        patients = scan.nextInt();

        try (FileWriter writer = new FileWriter("visits.txt", true)) {
            for (int i = 0; i < patients; i++) {
                System.out.println("Patient №" + (i + 1));
                System.out.println("Type of the patient (dog, cat, exotic): ");
                String type = scan.next().toLowerCase();

                Animal animal = null;

                System.out.println("Name: ");
                String name = scan.next();
                System.out.println("Age: ");
                int age = scan.nextInt();

                switch (type) {
                    case "dog":
                        System.out.println("Breed: ");
                        String breed = scan.next();
                        animal = new Dog(name, age, breed);
                        break;
                    case "cat":
                        System.out.println("Is sterilized? (true/false): ");
                        boolean isSterilized = scan.nextBoolean();
                        animal = new Cat(name, age, isSterilized);
                        break;
                    case "exotic":
                        scan.nextLine();
                        System.out.println("Special needs: ");
                        String needs = scan.nextLine();
                        animal = new ExoticAnimal(name, age, needs);
                        break;
                    default:
                        System.out.println("Unknown animal type.");
                        continue;
                }

                scan.nextLine();
                System.out.println("Owner name: ");
                String ownerName = scan.nextLine();
                System.out.println("Owner mobile: ");
                String ownerPhone = scan.nextLine();
                Owner owner = new Owner(ownerName, ownerPhone);

                System.out.println("Doctor's name: ");
                String doctor = scan.nextLine();
                System.out.println("Describe the problem: ");
                String problem = scan.nextLine();

                System.out.print("Treatment type (medication/surgery): ");
                String treatmentType = scan.next().toLowerCase();

                Treatment treatment;
                String treatmentResult;

                if (treatmentType.equals("medication")) {
                    System.out.println("Medication name: ");
                    String medName = scan.next();
                    treatment = new Medication(medName);
                } else {
                    System.out.println("Surgery type: ");
                    String surgeryName = scan.next();
                    treatment = new Surgery(surgeryName);
                }

                treatmentResult = treatment.applyTreatment();

                Visit visit = new Visit(LocalDate.now(), animal, doctor, problem);

                // Запис във файл
                writer.write("Date: " + visit.getDate()
                        + " | Animal: " + animal.getName() + " (" + animal.getType() + ", " + animal.getAge() + " years)"
                        + " | Owner: " + owner.getName() + " (" + owner.getMobile() + ")"
                        + " | Doctor: " + doctor
                        + " | Problem: " + problem
                        + " | Treatment: " + treatmentResult + "\n");

                System.out.println("Visit saved.\n");
            }

            System.out.println("All visits recorded in visits.txt.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        scan.close();
    }
}