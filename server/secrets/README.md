* Google Credentials 

In order for the server to use Google APIs, the server is provided with a Google server account. Inside the project, a JSON file containing the credentials of this account    *MUST* be provided. However, it is not secure to place this JSON directly in the repository. Therefore,
> You must put the credentials file under the secrets folder of the server.
> 
> The credentials JSON will be provided externally.