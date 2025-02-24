# Hotel booking service

## If you need to start app, use mock-db profile

## curl example
curl --location 'http://localhost:8080/api/orders' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=AD6462D5443B6FB025E26D4421425B78' \
--data '{
"roomId": 1,
"userId": 1,
"from": "2024-01-01",
"to": "2024-01-01"
}'
