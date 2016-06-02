# ROI HUNTER - Facebook User Likes
This project was developed for ROI Hunter. It represents a system that receive a Facebook ID and an Access Token and store informations about the user and the pages who it likes and retreive this informations in a JsonObject

### Prerequisities
- [Apache Maven](https://maven.apache.org/index.html)
- [MySQL] (http://dev.mysql.com/downloads/)
- [PostMan] (https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en)


## Built with
- Java 1.8
- Maven 3.2.1
- Spring Boot (WEB, JPA, TEST, MVC)
- MySQL
- Gson
- Flyway


## Installation
- Clone the git repository (https://github.com/saudborg/FacebookUserLikes)
- In your MySQL Adm create a database named roi_hunter
- In your terminal execute `mvn spring-boot:run`


### How to use 
When you see on your terminal `INFO: Server startup in 8367 ms` you are ready to use it

To test the application you should use an application like PostMan in your browse to access the URLs.
They are

POST - localhost:8080/users
	FB_ID 			- 		Facebook ID
	access_token	-		Access Token

This URL will get all informations about the user and the pages the user liked and store in database
(Both attributes you can get at: https://developers.facebook.com/tools/explorer/)

DELETE - localhost:8080/user/USER_ID
	USER_ID			-	 	Facebook ID

This URL will delete all informations about the user and delete the reference with the pages. But won't delete the page. (1)

GET - localhost:8080/USER_ID
	USER_ID			-		Facebook ID

This URL will retrieve all informations about the user. Like Name, ID, Gender and Profile picture URL

GET - localhost:8080/USER_ID/likes
	USER_ID			-		Facebook ID

This URL will retrieve all informations about the pages that the user liked. Like Name, ID, Description and Image URL



## NOTES

(1) I did it because I consider the page could be insert again in another moment for other user. In this way I don't need one more insert because the page is already on database

One thing that I would like to change in the system is the "delete". I consider delete an object is not the best way. Once one day the same user could  access the system again and they will need one more "save" in database. However if create a flag in the user's table which says if is delete or not, would be more efficient if one day the user back to the system.

I tried to make the reponse in a friendly mode, and I believe the way I did is the best way that I found.


# ABOUT THE DEVELOPING
Was a pleasure work with the Facebook API. I've already made some personal projects and I've never had problem. In this project the only thing I had problem was the response with "data". When I receive this JSON it is not easy to work with it, and the way I found maybe wasn't the best (Creating a Entity just for the attribute Data).

Also I used some class that I've developed before. I use to integrate with other API, like Google Place and Zoomato





