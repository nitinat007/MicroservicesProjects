# Read Me First

Access the H2 database dashboard at http://localhost:8080/h2-console 
. JDBC url : `jdbc:h2:mem:universitydb`

Access Swagger doc at http://localhost:8080/swagger-ui.html 

APIs sample requests:
`curl -X 'POST' \
'http://localhost:8080/university/courses' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"course_id": 0,
"courseName": "string",
"desc": "string",
"course_fee": 0,
"active": true
}'`

`curl -X 'GET' \
'http://localhost:8080/university/activeCourses' \
-H 'accept: */*'`

`curl -X 'GET' \
'http://localhost:8080/university/courses?course_name=any' \
-H 'accept: */*'`