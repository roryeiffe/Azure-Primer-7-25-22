## Virtual Machines
- a virtual computer that we can interact with 
- we can choose a few different options including operating system, storage, CPU, how to connect, security

### Security Groups
- Security groups help filter traffic in/out of our virtual machine
- Each group has rules and each rule has
    - name - used to identify the rule
    - priority - lower numbers have higher priority
    - source/destination ips
    - protocol
    - direction - inbound/outbound
    - port range
    - action - allow/deny

### Tutorial
#### Create the VM
- On Azure portal, we navigate to the Virtual Machine section
    - by searching
    - clicking on all services
    - clicking on virtual machines
- Create Linux Virtual Machine
- Choose a subscription and resource group
- Pick a region that's closes to you (or your customers)
- Give it a name
- Pick our image, I picked Ubuntu 16
- Authentication type, pick password, make sure to remember username and password
- Open to post 22 SSH, open up 80 for HTTP
- Review + Create

#### Connect
- After the resource is created, we click on "Go to Resource"
- We can view the Security rules on Networking tab on the left
- We can view the way to connect under "Connect"
- ex: ssh <user_name>@20.14.96.167 (It won't be exactly this, but it will look similar)
- it will ask to confirm, click yes
- put in your password
- try out some Linux commands to test it out

#### Running a Web Server
- ```sudo apt-get update```
- ```sudo apt-get install apache2```
- To test it out, go to 20.14.96.167 (the public address of the VM) in your search bar
- Make sure we allow HTTP, (port 80) in our security rules

## Storage
- Azure Storage lets us store data using Azure services
- Benefits:
    - durable
    - scalable
    - secure
    - managed
    - accessible

### Blob storage
- unstructured - list of objects, rather than table like you would see in an SQL database
- can store massive amounts of data
- storing documents, video, audio, files
- Blob - binary large object

### Hosting a static website using BLOB Storage
- create storage account on Azure
    - just make sure name is unique
    - Standard Performance
    - Geo-Redundant Storage
- We can review the other tabs but we don't need to change anything
- Review + Create, wait for deployment
- Now, we want to make use of BLOB, go to capabilities tab
- We click on Create Static Website and give names of index.html and 404.html(sucess and error pages)
- An Azure Storage Container should be created automatically
- create 2 local HTML files that match what we specified on the storage account
- Upload the files into the container
- Finally, by clicking on the primary endpoint, we should see our html page

## Exercises:
- Get an Azure Linux VM up and running and deploy a web server
    - Use [this](https://blog.jineshkumar.com/how-to-host-your-static-website-using-apache2-on-a-linux-machine-deployed-on-azure) to see how to edit the default page
- Deploy a static website using Azure Blob Storage. Feel free to customize it however you want
