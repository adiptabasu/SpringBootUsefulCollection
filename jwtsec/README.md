# jwtsec

---

This basically acts a simple JWT setup for bigger projects.

In this setup, the private route of products can only be accessed after a valid JWT Token is provided.

## Login API

> localhost:8080/auth/login
> 

The above is a post request, and the following is a sample of the body.

```json
{
    "email":"test@gmail.com",
    "password":"password"
}
```

Now, this will provide the following sample response

```json
{
    "email": "adiptabasu28@gmail.com",
    "authToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLGFkaXB0YWJhc3UyOEBnbWFpbC5jb20iLCJpc3MiOiJBZGlwdGEiLCJpYXQiOjE2NTQ3MTg1MTEsImV4cCI6MTY1NDgwNDkxMX0.HKdz3nYmbwDqSlJGts9d4-f3xooQ64CN-kKcPriBPsvNfcOrFRh1Czaei4mXJMkD7-u0R72NejZftrbMKHLjYg"
}
```

This authToken value needs to be passed as the Bearer Token

---

## Adding Product API

> localhost:8080/products
> 

The request is a post request, and the sample body for it will be,

And for this you will need to add a Bearer Token in the header.

The format is,

In the header, the Key will be “Authorization” and the value will be “Bearer <token>”, where <token> is the token we get on login.

```json
{
    "name":"iPhone",
    "price":999
}
```

## Fetching All Products API

> localhost:8080/products
> 

The request is a get request that will have the same Authorization header

A sample success response is as follows,

```json
[
    {
        "id": 1,
        "name": "iPhone",
        "price": 999.0
    },
    {
        "id": 2,
        "name": "iPhone",
        "price": 999.0
    }
]
```