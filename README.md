# optica
Spring rest implementation

This appliction runs on port 8082.

To add a customer:

POST or PUT Request to - http://localhost:8082/api/customer/add


with json body as below:
{
  
  "id" : 10001,
  "firstName" : "Vishal",
  "surName" : "Arulelango"
}


To view all the customers:

GET Request to - http://localhost:8082/api/customer/all

To delete a customer:

DELETE Request to - http://localhost:8082/api/customer/remove/10001

