## Azure SQL
- Similar to RDS
- Host SQL database on Azure and connect to it

## Instructions

### Created the Database
- Create Azure Database for PostgreSQL
- Pick flexible or single. I picked single
- We're met with the create page
- Give subscription, resource group, name (must be unique)
- "None" for data source
- Pick region and version (I picked 11)
- Pick a username and password for the server
- Review + Create (optionally check out the other tabs)

### Connect to the Database
- Navigate to the resource that we created
- Click on "Connection Security" on the left panel
- Click on "Add Client IP Address"
- Scroll down and disable "Enforce SSL connection" (for demo purposes, in reality we'd probably want that extra security)
- We can get all of our connection info from the "Connection Strings" tab on Azure
    - endpoint
    - username
    - port, should be 5432 for postresql

## Cosmos DB
- NoSQL database
- single-digit milisecond performance
- Azure Cosmos DB Core (SQL) API supports Java, Node.js, Python, .NET, and JavaScript
- also support Cassandra API and MongoDB API
- [Overview](https://docs.microsoft.com/en-us/azure/cosmos-db/introduction)
- [Provisioned vs Serverless](https://docs.google.com/spreadsheets/d/1xCCzpQOrRXwv1GNt7am_rZXWDS0fvioLM8Qo01-E0zk/edit#gid=0)
- [SQL vs noSQL](https://docs.microsoft.com/en-us/azure/cosmos-db/relational-nosql)

## Instructions

### Create the Resource
- First, navigate to the Cosmos resource by searcing for it or clicking on All Services
- For API, choose Core (SQL)
- Choose subscription and resource group
- Give the account a name
- Capacity Mode shouldn't matter because we are doing free tier
    - Provisioned throughput lets you allocate request units per second while serverless doesn't require this configuration
- We can look at some of the tabs or skip straight to Review+Create

### Connect to and start using the Database
- Navigate to your resource
- Go to Quick Start (should be redirected)
- Click on the Java tab
- Click on "Create 'Items' container", this will take a minute
- Download and unzip the sample app, explore it in your IDE
    - Try to find in the code where the connection to your Azure account is configured
    - Pay special attention to SyncMain.Java
    - Run the code
- Navigate back to the Azure portal
- Find your way back to the cosmoDB resource
- Click on "Data Explorer" and find the data that was added from running the Java program
- Modify the code to make it upload information about your own family, or some other family

## Exercises
- Host a SQL database on Azure and connect to it
    - Bonus: Take a previously created Java project and connect it to your Azure SQL database
    - link coming soon
- Finish looking through the sample Cosmos Example code
- Create a Cosmos DB database and insert information about your family or a fictional family using Java. 
    - Bonus: Insert different information (other than family)