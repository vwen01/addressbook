# Address-Book
A simple spring-boot application that allows users to create and manage their address book of friends

### Prerequisites
- Java 8
- Mongodb
  - Windows: Run "choco install mongodb"
  - Mac: Run "brew tap mongodb/brew  && bbrew install mongodb-community@4.0"

### Requirements From Documentation
You have been asked to develop an address book that allows a user to store (between
successive runs of the program) the name and phone numbers of their friends, with the
following functionality:

- To be able to display the list of friends and their corresponding phone numbers sorted
by their name.
- Given another address book that may or may not contain the same friends, display the
list of friends that are unique to each address book (the union of all the relative
complements). For example given:
    - Book1 = { “Bob”, “Mary”, “Jane” }
    - Book2 = { “Mary”, “John”, “Jane” }
    - The friends that are unique to each address book are:
    - Book1 \ Book2 = { “Bob”, “John” }

### Assumptions
- Friends do not need to be Users
- User can have no Friends
- Friend names must be unique
- MongoDb chosen as I feel its likely that you'd want more fields in the future if you were to develop this out.

### REST APIs
- Get AllUsers:  localhost:8080/user/all
- Get FriendsList :  localhost:8080/user/address_book/{userId}
- Post NewUser: localhost:8080/user/add
  - {"name": "John","addressBook": [{"name": "James","phoneNumber": "123456"}]}
- Post AddFriend: localhost:8080/user/address_book/{userId}
  - payload: {"name": "Friend1","phoneNumber": "111111"}
- Post GetUniqueFriends: localhost:8080/user/address_book/unique/{userId}
  - payload: [{"name": "Friend1","phoneNumber": "11111"}, {"name": "Friend2","phoneNumber": "00000"}]

### Start Mongo

```sh
Mac: 
Start: brew services start mongodb-community@4.4
Stop: brew services stop mongodb-community@4.4

Windows:
Start: Mongod

```

### Build

```sh
./mvn clean build
```

### Run

```sh
./mvn spring-boot:run
```

## Future Improvements
- Unfortunately, due to timing, only had Sunday to work on this, so may not be as polished as I wanted.
- For a sorted list of Friends I would implement a sorted list or sorted set as the data structure (just noticed this).
- More exhaustive testing can be done on integration etc.
- Authentication and Authorisation needs to be implemented for security purposes. I would consider something on the lines 
  of a 0Auth2.0 with JWT token type flow for this project.
- Dockerisation and project to be deployed and run on local, test, stage etc environments on a cloud platform. 
  - One possible solution can be AWS ECS-Fargate
- Create Front End UI in something like React or Angular
