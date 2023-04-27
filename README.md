# Reward REST APIs

## Setup Reward project
```
# clone to local
git clone https://github.com/dangtuan21/reward.git

# cd to reward folder
cd reward

# build
./gradlew build

# test
./gradlew test

# start REST server
./gradlew run
```
## Open API Spec
Navigate to:
```
http://localhost:8080/swagger/reward-app-1.0.yml
```


## Run Reward project
In browser, nagivate to following urls to see the results
### list all transactions
```
http://localhost:8080/rewards/transactions
```
### view points of all customers
```
http://localhost:8080/rewards
```

### view points of a customer, with customerId = cust4
```
http://localhost:8080/rewards/cust4
```
