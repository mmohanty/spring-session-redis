# spring-session-redis
Spring session integration with Redis Cache.

1. Requires Redis to be installed on local. If redis is installed in a remote machine then,
configure IP and Port accordingly in application.properties file.


In this application, i have implemented CookieSerializer to customize cookie configuration.

Cookie serializer is suitable for a web application. You have to be careful about setting
path and domain. If any API-Gateway or Reverse Proxy server used in between and context 
path is not same as your application then, client will not be able to read cookies hence 
will send null/empty value to backend and it will be treated as a new session every time.

Wild card can be used in cookies which can be accepted by any domain and path. This will 
compromise security of your application.

For Restful application, its recommended to used HttpSessionIdResolver and set your own 
header for your application to keep track of session. Client needs to pass the header in
every request to be in same session.

 
 
