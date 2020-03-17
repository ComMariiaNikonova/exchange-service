# exchange-service

To start application you need:

1. Up mysql db by docker:
docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=pass -e MYSQL_USER=exch -e MYSQL_DATABASE=data_exch -d mysql

2. Build with gradle: 
gradle build

3. Start application with:
java -jar <jar>

4. Perform request with Auth header (user hardcoded):
Authorization: Basic dXNlcjpwYXNz

