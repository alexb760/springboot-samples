curl -v -X POST localhost:8080/api/v1/drone \
     -H "Content-Type: application/json" \
     -d '{"serial":"S-0001", "model":"Lightweight", "weight": 500}'