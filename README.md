# CER - Currency Exchange Rate Project

This is my first more complex algorithm. 

CER Project is designed as a program that runs at a given time (using CRON, Time Scheduler, ..).  

 Purpose - application that withdraws current exchange rate from the National Bank of Serbia (NBS) Service. 
 Application is used for fetching, processing and storing exchange rate data into database, as well as interacting with services that provide access to this data.


NBS publishes exchange rate lists every morning (except weekends and working days) at 8 AM.
The program adresses the service asking for exchange rate lists and then writes them into the database

*Necessary*
* Java 17

*Features*

- **Command-Line Argument Parsing**: The application allows user to input parameters via the command line

  
| Argument | Short | Description                    | Required |
| ------- | ------- | -------                    | ------- |
|host      |-h     |The host of the database server.|Yes       |
|port|-p|The port number of the database server.|Yes|
|database|-d|The name of the database to connect to.|Yes|
|username|-U|The username used for authentication with the database.|Yes|
|password|-P|The password associated with the username for database access.|Yes|


- **Exchange Rate Processing**: The application fetches excahnge rate data from a service, processes it, and stores it into the database using the 'ExchangeRateService'

  ## How to use
1. **Clone the project**:

 ```bash
    git clone https://github.com/your-username/nbs-cer.git
```
2. **Navigate to the project Directory**:

```bash
    cd nbs-cer
```
3. **Build the Project**:
If you use Maven:
```bash
    mvn clean install
```
If you use Gradle:
```bash
    gradle build
```
4. **Run the Application**:
   After building the project you will be able to run the application with the following command:
```bash
    java -jar target/nbs-cer.jar  -p 17710 -d b1 -U user -P password123* -h localhost
```
This is just an example, replace parameters with the appropriate values for your database.

## Technical Details
- **Database**: PostgreSQL
- **JDK version**: 11 or higher
- **Dependencies**:
     - 'PostgreSQL JDBC Driver' for database connectivity
     - 'Jakarta XML Bind' for automating the mapping between XML documents and Java objects
     - 'Apache Commons Cli' for presenting, processing, and validating a Command Line Interface
     - 'Metro Glassfish Web Services Runtime' for interacting with SOAP web services
     - 'Apache HttpClient' for making HTTP requests
