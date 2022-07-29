### Domain Driven Design
- looking at software from top down
- focus should be on business, rather than technology
- domain is the business we're trying to solve
    - banking, insurance, e-commerce
- models of the domain, make our software conform to that
- closing the gap between business reality and code
- ex: Windows 8, well made and looks good, but didn't add features that were helpful/wanted
- Domain Driven Design is not inherently related to OOP, but in practice it makes use of OOP concepts
- [Wiki Page](https://en.wikipedia.org/wiki/Domain-driven_design)

### Azure Devops
- a separate portal from what we've been using
- has a lot of Github-like features such as repositories, wikis (like Github issues), and Pipelines (like Github actions)

### Github Actions
- We can use Github actions with Azure to create pipelines and deploy our back-end

#### Instructions
- Make sure we have a Spring Boot (or different tech stack) Application hosted on Github
- Feel free to fork https://github.com/roryeiffe/azure-demo-7-27-2022.git
- Head over to Azure Portal
- Fill in the subscription, resource group, give a unique name
- Under Publish, select "Code", but there is the option to Docker if we wanted to
- For Runtime stack, pick whatever language/version you're using, for me Java 17
- Pick Embedded Server, because we're using Spring, and jar files
- We can leave OS as Linux
- Pick Region that's closes to you/customers
- Feel free to change size for the plan, click on dev/test to get the free version, but use the default option to use Github actions
    - check the deployment tab to make sure Github is supported
- You can check out the other tabs, or head right to Review+Create
- Check out the resource, click on the link, and see the default page
- To make our code run on it, we can go to the deployment center
- Pick Github as source and sign in
- Choose your organization, repo, and branch that you want to trigger the build
- Overwrite the previous workflow
- On the Github repo, we can click on the actions tab to see the build/deployment in progress
- Because we have it set up to redeploy when we push to main, we can test that out
    - make a change to the code
    - push to main
    - wait for the project to deploy and then check out the link and see if it changed


