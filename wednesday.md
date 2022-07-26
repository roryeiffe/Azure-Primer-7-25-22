## Cosmos DB
- NoSQL database
- single-digit milisecond performance
- Azure Cosmos DB Core (SQL) API supports Java, Node.js, Python, .NET, and JavaScript
- also support Cassandra API and MongoDB API
- [Overview](https://docs.microsoft.com/en-us/azure/cosmos-db/introduction)

## Instructions
- First, navigate to the Cosmos resource by searcing for it or clicking on All Services
- For API, choose Core (SQL)
- Choose subscription and resource group
- Give the account a name
- Capacity Mode shouldn't matter because we are doing free tier
    - Provisioned throughput lets you allocate request units per second while serverless doesn't require this configuration
- We can look at some of the tabs or skip straight to Review+Create
- Navigate to your resource
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