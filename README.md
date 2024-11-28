# CER - Currency Exchange Rate Project

This is my first more complex algorithm. 

CER Project is designed as a program that runs at a given time (using CRON, Time Scheduler, ..).  

 Purpose - application that withdraws current exchange rate from the National Bank of Serbia (NBS) Service. 
 Application is used for fetching, processing and storing exchange rate data into database, as well as interacting with services that provide access to this data.


NBS publishes exchange rate lists every morning (except weekends and working days) at 8 AM.
The program adresses the service asking for exchange rate lists and then writes them into the database

*Necesseary*
* Java 17

*Features*

- **Command-Line Argument Parsing**: The application allows user to input parameters via the command line
  
| Argument | Short | Meaning | Required |
|host|-h|||




