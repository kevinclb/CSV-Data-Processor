import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //creating the database we're going to use
        Database d = new Database();


        // Asking user for file name
        System.out.println("Enter the file name: ");
        Scanner in = new Scanner(System.in);

        // Setting the file name
        String fileName = in.nextLine();

        // Creating a file object for the file
        File ratesFile = new File("src/"+fileName);

        // Try to scan the file we've made, if not catch FileNotFound exception
        try {
            Scanner scanner = new Scanner(ratesFile).useDelimiter(",");
            String header = scanner.nextLine(); //make the header the first line
            System.out.println(header); //print the header
            while (scanner.hasNextLine()) { //while the header has a next line, store it, then use another scanner to take part the lines of data
                String dataline = scanner.nextLine();
                Scanner s = new Scanner(dataline).useDelimiter(",");
                d.addCompany(new Company(s.nextInt(), s.nextInt(), s.next(), s.next(), s.next(), s.next(), s.nextDouble(), s.nextDouble(), s.nextDouble()));
            }

            scanner.close();
        } catch (FileNotFoundException fileNotFound){
            System.out.println("The file wasn't found");
        } catch (Exception e){
            System.out.println("There was an error.");
            e.printStackTrace();
        }

        try {
            System.out.println("The average rate is:   " + d.calculateAverage()); //use the average rate method
            System.out.println(Database.calculateMax()); //use the maximum method
            System.out.println(Database.calculateMin()); //use the minimum method
        } catch (NoValue f) {
            System.out.println("There is an error\n"); //catch a potential error
        }



    }


}

class Database {
    static ArrayList<Company> database; //create an array list of companies
    public Database(){
        database = new ArrayList<>();
    }

    public void addCompany(Company c){ //add a company using built in add method
        database.add(c);
    }

    public double calculateAverage(){ //calculate the average by iterating the companies,
        double sum = 0;               //adding their total to the sum,
        for (Company c : database){   //then dividing that by the total number of companies, database.size()
            sum += c.comm_rate;
        }
        return sum / database.size();
    }

    @Override
    public String toString() { //overriding generic toString method to turn every company into a string line.
        StringBuilder s = new StringBuilder();
        for (Company c : database){
            s.append(c.zipCode)
                    .append(", ")
                    .append(c.eiaID)
                    .append(", ")
                    .append(c.utility_name)
                    .append(", ")
                    .append(c.state)
                    .append(", ")
                    .append(c.service_type)
                    .append(", ")
                    .append(c.ownership)
                    .append(", ")
                    .append(c.comm_rate)
                    .append(", ")
                    .append(c.ind_rate)
                    .append(", ")
                    .append(c.res_rate)
                    .append("\n");
        }
        return s.toString();
    }

    public static String calculateMax() throws NoValue{ //iterating every company looking for a maximum rate
        double maxRate = 0;
        String compName = "";
        if (database.isEmpty()){
            throw new NoValue("There can be no value, the database is empty..");
        } else {
            for (Company c : database){
                if (c.comm_rate > maxRate){
                    maxRate = c.comm_rate;
                    compName = c.utility_name;
                }
            }
        }
        return "The maximum rate is:   " + maxRate + " from the utility company:   " + compName;
    }

    public static String calculateMin() throws NoValue{ //iterating every company looking for a minimum rate
        double minRate = 0.0;
        String compName = "";
        if (database.isEmpty()){
            throw new NoValue("There can be no minimum value, the database is empty..");
        } else {
            for (Company c : database){
                if (c.comm_rate <= minRate){
                    minRate = c.comm_rate;
                    compName = c.utility_name;
                }
            }
        }
        return "The minimum rate is:   " + minRate + " from the utility company:   " + compName;
    }


}

class NoValue extends Exception{ //creating a custom exception
    public NoValue(String message){
        super(message);
    }
}

class Company{ //the blueprint for a company according to our data
    public int zipCode;
    public int eiaID;
    String utility_name;
    String state;
    String service_type;
    String ownership;
    double comm_rate;
    double ind_rate;
    double res_rate;

    public Company(int zip, int ID, String util, String stateName, String service, String ownership, double commRate, double indRate, double resRate){
        this.zipCode = zip; //initializing the company object with data passed to it's constructor
        this.eiaID = ID;
        this.utility_name = util;
        this.state = stateName;
        this.service_type = service;
        this.ownership = ownership;
        this.comm_rate = commRate;
        this.ind_rate = indRate;
        this.res_rate = resRate;
    }


    //unused methods
//    public int getZipCode() {
//        return zipCode;
//    }
//
//    public void setZipCode(int zipCode) {
//        this.zipCode = zipCode;
//    }
//
//    public int getEiaID() {
//        return eiaID;
//    }
//
//    public void setEiaID(int eiaID) {
//        this.eiaID = eiaID;
//    }
//
//    public String getUtility_name() {
//        return utility_name;
//    }
//
//    public void setUtility_name(String utility_name) {
//        this.utility_name = utility_name;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getService_type() {
//        return service_type;
//    }
//
//    public void setService_type(String service_type) {
//        this.service_type = service_type;
//    }
//
//    public String getOwnership() {
//        return ownership;
//    }
//
//    public void setOwnership(String ownership) {
//        this.ownership = ownership;
//    }
//
//    public double getComm_rate() {
//        return comm_rate;
//    }
//
//    public void setComm_rate(double comm_rate) {
//        this.comm_rate = comm_rate;
//    }
//
//    public double getInd_rate() {
//        return ind_rate;
//    }
//
//    public void setInd_rate(double ind_rate) {
//        this.ind_rate = ind_rate;
//    }
//
//    public double getRes_rate() {
//        return res_rate;
//    }
//
//    public void setRes_rate(double res_rate) {
//        this.res_rate = res_rate;
//    }

}