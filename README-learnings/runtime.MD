# Local Runtime Environment
`colima start --cpu 4 --memory 8 --disk 100 --vm-type vz`

*Using Colima Instead of Docker*
(Memory savings)
-- Installing Postgress
```aiignore
docker run -d \
  --name postgres \
  -e POSTGRES_PASSWORD=secret \
  -p 5432:5432 \
  -v pgdata:/var/lib/postgresql \
  postgres:18

# Connect to it  
docker exec -it postgres psql -U postgres
```

