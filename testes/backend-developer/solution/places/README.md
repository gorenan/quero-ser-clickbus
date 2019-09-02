## Places API
 ### Created by:
  #### Renan Ovando
##Objectives:
- This API can create, edit and list places

##Technologies:
 - Java 8;
 - Springboot 2.1.7
 - JUnit 4.12

##Methods:
##### This should be tested by using a client. For example: POSTMAN
###/create
-HTTP method :POST
-Required fields: name, slug and state.
-URL example: /create.

** Request example**

	{
	"name":"Name foo",
	"slug": "Slug foo",
	"state": "State foo"
	}

** Response example**

	{
    "id": "5d6c87721133f3321ae266b2",
    "name": "Name foo",
    "slug": "Slug foo",
    "state": "State foo",
    "createdDate": "2019-09-02T10:07:29.995",
    "updatedDate": "2019-09-02T10:07:29.995"
	}

**HTTP Responses:**
- OK
- Internal Server Error - in case of exception
- Bad Request - in case of missing some required field.

##/getPlacesByName
-HTTP method :GET
-Required fields: name.
-URL example: /getPlacesByName?name=foo.
-Return an array of places

**Response example**
```javascript[
    {
        "id": "5d6c87721133f3321ae266b2",
        "name": "Name foo",
        "slug": "Slug foo",
        "state": "State foo",
        "createdDate": "2019-09-02T10:07:29.995",
        "updatedDate": "2019-09-02T10:07:29.995"
    }
]
```
**HTTP Responses:**
- OK
- Internal Server Error - in case of exception
- Bad Request - in case of missing some required field.


##/getPlace
-HTTP method :GET
-Required fields: id.
-URL example: /getPlacesByName?id=foo.
-Return a specific object of a place

**Response example**
	{
    "id": "5d6c87721133f3321ae266b2",
    "name": "Name foo",
    "slug": "Slug foo",
    "state": "State foo",
    "createdDate": "2019-09-02T00:07:29.995",
    "updatedDate": "2019-09-02T00:07:29.995"
	}

**HTTP Responses:**
- OK
- Internal Server Error - in case of exception
- Bad Request - in case of missing some required field.

##/editPlace
-HTTP method :PUT
-Required fields: there is no required field.
-URL example: /editPlaces

** Request example **
```javascript[
    {
        "id": "5d6c87721133f3321ae266b2",
        "name": "Second Name foo",
        "slug": "Second Slug foo",
        "state": "Second State foo",
        "createdDate": "2019-09-02T10:07:29.995",
        "updatedDate": "2019-09-02T10:07:29.995"
    }
]
```
**Response Example
```javascript[
    {
        "id": "5d6c87721133f3321ae266b2",
        "name": "Second Name foo",
        "slug": "Second Slug foo",
        "state": "Second State foo",
        "createdDate": "2019-09-02T10:07:29.995",
        "updatedDate": "2019-09-02T10:07:29.995"
    }
]
```
**HTTP Responses:**
- OK
- Internal Server Error - in case of exception
- Bad Request - in case of missing some required field.