## This README contains information about how to test and run the server

### A summary on how the system works:
> We will first begin by describing OAuth mechanism. OAuth is the Google Protocol that enables to reach Google APIs without the user's sensitive information like passwords. Basically, the protocol provides with a refresh and an access token. Access token is used to call Google APIs directly, but they have a short lifetime. Refresh tokens are used to create access tokens. 
> 
> In our application, the server has a gmail account. Thus, by OAuth, the server reaches to that account and therefore its capabilities.
> 
> Each time the server is being run, the server is in need of an access token (and a refresh token). To provide this, we, as a real human user, manually go to the URL http://localhost:8080/google/oauth/start. This sends a code to our server, which is used to get a refresh and access token, while using **Authorization** information, secret and special to our server. 
> 
> **Thus, if the server is desired to be run, one must update the application.properties file manually, with the externally provided information about the sensitive server information.**
>
> _Note that these sensitive information must not be pushed to the remote repository._ That is why the gitignore file is configured accordingly. Also note that during the server execution, the access and refresh tokens are saved **locally** to _server/secrets/_ directory. This file is not being pushed to the remote repository either.
> 
> Once the access token is provided, we use Google API Client libraries to create and modify Google Forms.


### System Setup

The following checklist will enable you to run the server.

### _Oauth 2_
* Update the application.properties file. (_When desired, ask for the file from the relevant person. It will be provided externally._)
* Run the ServerApplication file.
* Go to your browser and go to the URL provided above.
* Log in to fullhouseevalit@gmail.com (_If in this stage you want to use your own email, make contact with the relevant person. Your email can be added to the testers list_)
* A blank white page will come up.
* Now, you can use Postman to imitate the client and send HTTP requests.
* If any problem occurs, contact the relevant developer.

### Google Cloud Authorization

The Pub/Sub subscription (the system that fetches the responses to the surveys in the project) requires a **ONE TIME** setup. You will need to complete this guide only for once in your local environment.
* Install Google Cloud CLI 
> https://dl.google.com/dl/cloudsdk/channels/rapid/GoogleCloudSDKInstaller.exe
* Complete the setup.
* run the following command on your command prompt:
> gcloud auth application-default login
* Sign in with the _fullhouseevalit@gmail.com_ account (_Connect the relevant person if you do not have access to the password)_.
* Choose the project **eval-it**.
