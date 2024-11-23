Converter Endpoints:

- Root:                                   /conversion
- Get supported currencies:               GET     /conversion/currency
- Set rate:                               POST    /conversion/rate/{from}/{to}/{rate}
- Get rates:                              GET     /conversion/rate
- Convert:                                POST    /conversion/{from}/{to}/{amount}
- Convert:                                POST    /conversion/string/{from}/{to}/{amount}
- All convertions:                        GET    /conversion/

The converter app supports following currencies:

    - TRY
    - SAR
    - USD
    - EUR

To work with the converter, first set conversion rates among currencies through "set rate" endpoint. Then ypu can convert among currencies.


