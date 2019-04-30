# Accounts & Transactions Application

This is a basic spring boot application, created with the help of spring boot starter.

The choice of build tool is maven. Gradle also can be a valid choice of build tool, there is no hard dependency on Gradle.

###Assumptions:

1. Currently I have used the in-memory h2 db
2. All the required SQL statements are under src/main/resources/data.sql
3. Application will run on port 8080
3. There 100 sample accounts are taken, and total 1000 transactions related to these accounts
4. This application was built and run using IntelliJ IDEA, but it can be started via command line as well with the help of `mvn spring-boot:run` as well.
5. Both endpoints are paginated and supports basic HATEOAS features
6. The basic static pagination available is used, the first page starts from index 0

###Endpoints:

1. Accounts Endpoint - This list the accounts available to User `http://localhost:8080/accounts/page/0/size/5`
- Sample Response:
```
{
    "_embedded": {
        "accountList": [
            {
                "accountNumber": 11928233,
                "accountType": "SAVINGS",
                "balanceDate": "2019-04-29",
                "currency": "AUD",
                "accountName": "AUSavings741",
                "openingBalance": 94204.62
            },
            {
                "accountNumber": 16101289,
                "accountType": "SAVINGS",
                "balanceDate": "2019-04-29",
                "currency": "AUD",
                "accountName": "AUSavings882",
                "openingBalance": 94569.65
            },
            {
                "accountNumber": 17672200,
                "accountType": "SAVINGS",
                "balanceDate": "2019-04-29",
                "currency": "SGD",
                "accountName": "SGSavings937",
                "openingBalance": 28362.14
            },
            {
                "accountNumber": 38131447,
                "accountType": "SAVINGS",
                "balanceDate": "2019-04-29",
                "currency": "AUD",
                "accountName": "AUSavings479",
                "openingBalance": 6275.89
            },
            {
                "accountNumber": 44546569,
                "accountType": "CURRENT",
                "balanceDate": "2019-04-29",
                "currency": "SGD",
                "accountName": "SGCurrent950",
                "openingBalance": 13615.92
            }
        ]
    },
    "_links": {
        "next": {
            "href": "http://localhost:8080/accounts/page/1/size/5"
        },
        "self": {
            "href": "http://localhost:8080/accounts/page/0/size/5"
        }
    }
}
```
2. Transactions Endpoint - This will the transaction related to a particular account `http://localhost:8080/transactions/accounts/204478037/page/0/size/5`
- Sample Response:
```
{
    "_embedded": {
        "transactionList": [
            {
                "identifier": 693,
                "account": {
                    "accountNumber": 204478037,
                    "accountType": "SAVINGS",
                    "balanceDate": "2019-04-29",
                    "currency": "AUD",
                    "accountName": "AUSavings211",
                    "openingBalance": 39844.32
                },
                "valueDate": "2019-04-19",
                "currency": "SGD",
                "debitAmount": 643.45,
                "transactionType": "DEBIT",
                "transactionNarrative": "Maecenas ut massa quis augue luctus tincidunt."
            },
            {
                "identifier": 642,
                "account": {
                    "accountNumber": 204478037,
                    "accountType": "SAVINGS",
                    "balanceDate": "2019-04-29",
                    "currency": "AUD",
                    "accountName": "AUSavings211",
                    "openingBalance": 39844.32
                },
                "valueDate": "2019-04-08",
                "currency": "SGD",
                "debitAmount": 1274.6,
                "transactionType": "DEBIT",
                "transactionNarrative": "Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis."
            },
            {
                "identifier": 876,
                "account": {
                    "accountNumber": 204478037,
                    "accountType": "SAVINGS",
                    "balanceDate": "2019-04-29",
                    "currency": "AUD",
                    "accountName": "AUSavings211",
                    "openingBalance": 39844.32
                },
                "valueDate": "2019-02-14",
                "currency": "AUD",
                "creditAmount": 1498.34,
                "transactionType": "CREDIT",
                "transactionNarrative": "Nulla mollis molestie lorem."
            },
            {
                "identifier": 389,
                "account": {
                    "accountNumber": 204478037,
                    "accountType": "SAVINGS",
                    "balanceDate": "2019-04-29",
                    "currency": "AUD",
                    "accountName": "AUSavings211",
                    "openingBalance": 39844.32
                },
                "valueDate": "2018-10-14",
                "currency": "SGD",
                "creditAmount": 3425.56,
                "transactionType": "CREDIT",
                "transactionNarrative": "Duis at velit eu est congue elementum."
            },
            {
                "identifier": 602,
                "account": {
                    "accountNumber": 204478037,
                    "accountType": "SAVINGS",
                    "balanceDate": "2019-04-29",
                    "currency": "AUD",
                    "accountName": "AUSavings211",
                    "openingBalance": 39844.32
                },
                "valueDate": "2018-09-20",
                "currency": "SGD",
                "creditAmount": 1211.59,
                "transactionType": "CREDIT",
                "transactionNarrative": "Vestibulum sed magna at nunc commodo placerat."
            }
        ]
    },
    "_links": {
        "next": {
            "href": "http://localhost:8080/transactions/accounts/204478037/page/1/size/5"
        },
        "self": {
            "href": "http://localhost:8080/transactions/accounts/204478037/page/0/size/5"
        }
    }
}
```