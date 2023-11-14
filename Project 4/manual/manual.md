# Project4 - Java Project with Ant Build Script

## Introduction
The ChocAn System is a console-based application designed to manage user credentials, generate reports, and interact with a ChocAn database. This manual provides step-by-step instructions on how to run the program, test its features, and provides team performance.


## System Requirements
To run the ChocAn System, ensure that the following requirements are met:

- `git` for cloning the repository.
- `apache` ant for building the project
- `java` for running the project.

## Installation
Follow these steps to install the ChocAn System:

1)	Open a terminal or command prompt.

2)	Clone the repository:

```shell
git clone https://bitbucket.org/azaman2/team12/src/master/
```

3)  Change into the project directory.


## Running the Program
To run the ChocAn System, perform the following steps:

1)	Run the `main` target to resolve dependencies, clean the build directories, compile, package, and run the application.

```shell
ant main
```

1)  You can also run the application directly through java.

```shell
java -jar release/ChocAn.jar
```

## Testing Features
Follow these steps to test specific features of the ChocAn System:

First, login to the system.
Steps to Log in:

-	The system displays a login prompt.
-	Enter the ChocAn provided ID.
-	Enter the password.
-	The system authenticates the user.
-	Upon successful authentication, the system identifies the user's role (Manager, Operator, or Provider).
-	The corresponding menu is displayed.
-	The user can then choose options from the displayed menu.

The application is split into 3 different menus:

| Manager Menu    | Operator Menu               | Provider Menu            |
|-----------------|-----------------------------|--------------------------|
| Generate Report | Add Member                  | Validate Member          |
| Email Report    | Update Member               | Add Service              |
|                 | Delete Member               | Update Service           |
|                 | Add Provider                | Delete Service           |
|                 | Update Provider             | Generate Provider Report |
|                 | Delete Provider             | Email Provider Report    |
|                 | Generate Provider Directory |                          |


## Operator Menu (Testing Steps):

### Add Member:
1)	Select the option from the Operator Menu.
2)	The system will prompt you for information to add a new member (e.g., ID, name, address).
3)	Verify that the member is added to the system.

### Update Member:
1)	Select the option from the Operator Menu.
2)	The system will prompt you to enter the ID of the member to be updated.
3)	Provide updated information for the member (e.g., name, address).

### Delete Member:
1)	Select the option from the Operator Menu.
2)	The system will prompt you to enter the ID of the member to be deleted.
3)	Verify that the member is removed from the system.

### Add Provider:
1)	Select the option from the Operator Menu.
2)	The system will prompt you for information to add a new provider (e.g., ID, name, address).
3)	Verify that the provider is added to the system.

### Update Provider:
1)	Select option 5 from the Operator Menu.
2)	The system will prompt you to enter the ID of the provider to be updated.
3)	Provide updated information for the provider (e.g., name, address).
4)	Verify that the provider's information is updated in the system.

### Delete Provider:
1)	Select option 6 from the Operator Menu.
2)	The system will prompt you to enter the ID of the provider to be deleted.
3)	Verify that the provider is removed from the system.

### Generate Provider Directory:
1)	Select option 7 from the Operator Menu.
2)	The system will generate a directory of all providers.
3)	Verify that the directory is created and contains the necessary information about each provider.

## Provider Menu (Testing Steps):

### Validate Member:
1)	Select the option from the Provider Menu.
2)	The system will prompt you to enter the member's ID for validation.
3)	Enter a valid member ID.
4)	Verify that the system validates the member.

### Add Service:
1)	Select the option from the Provider Menu.
2)	The system will prompt you to enter information to add a new service (e.g., service code, name, fee).
3)	Verify that the service is added to the system.

### Update Service:
1)	Select the option from the Provider Menu.
2)	The system will prompt you to enter the service code to be updated.
3)	Provide updated information for the service (e.g., name, fee).
4)	Verify that the service's information is updated in the system.

### Delete Service:
1)	Select the option from the Provider Menu.
2)	The system will prompt you to enter the service code to be deleted.
3)	Verify that the service is removed from the system.

### Generate Provider Report:
1)	Select the option from the Provider Menu.
2)	The system should generate a report for the provider, including information about provided services.
3)	Verify that the report is created and contains the correct information.

### Email Provider Report:
1)	Select the option from the Provider Menu.
2)	The system will output a .txt file containing the contents of the Provider Report.

## Manager Menu (Testing Steps)
### Generate Report:
1)	Select option 1 from the Manager Menu.
2)	The system will generate a report, which contains information on members.
3)	Verify that the report is created and contains relevant information.

### Email Report:
1)	Select option 2 from the Manager Menu.
2)	The system will output a .txt file containing the contents of the Manager Report.

Thank you for choosing Chocoholics Anonymous. If you encounter any issues or have questions, refer to this manual for assistance.

## Task Distribution Table

| Name              | Percentage | Tasks Contributed                                                      |
|-------------------|------------|------------------------------------------------------------------------|
| Dylan Mather      |            |                                                                        |
| Maddox Guthrie    |            | Operator Menu + Controller<br/>Login Menu + Controller<br/>Daily Timer |
| Nischal Bhattarai |            |                                                                        |
| Emily Otter       |            |                                                                        |
| Egan Trahant      |            |                                                                        |
| Kaela Camara      |            |                                                                        |