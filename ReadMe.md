# Getting Started

## DownStream systems

#
* discount api - /discount POST, This returns discount if any.
* notification api - /notification POST
* sales api        - /sales/journey/tracker POST
* aggregator api   - /agg/search/flights
* internal search api - /flight/search

## For calling flight search api, use below contract

### URL :- http://localhost:{REPLACE-WITH-PORT-FROM-CONSOLE}/flight
### HTTP Method :- POST
`{
 "fromDate" :"2018-10-22",
 "toDate": "2018-10-22",
 "departure" : "MUM",
 "arrival" : "DUB"
 }`

## For calling price search api, use below contract

### URL :- http://localhost:{REPLACE-WITH-PORT-FROM-CONSOLE}/price
### HTTP Method :- POST 
`{
 "date" :"2021-06-26",
 "flightNumber": "AB-1"
 }`





