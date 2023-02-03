# SQL and RDBMS by solving SQL queries


## Introduction
SQL query questions, Setup a PSQL instance using docker, setup the pgadmin 4


## Table Setup (DDL)

```
  CREATE TABLE cd.members
    (
       memid integer NOT NULL, 
       surname character varying(200) NOT NULL, 
       firstname character varying(200) NOT NULL, 
       address character varying(300) NOT NULL, 
       zipcode integer NOT NULL, 
       telephone character varying(20) NOT NULL, 
       recommendedby integer,
       joindate timestamp NOT NULL,
       CONSTRAINT members_pk PRIMARY KEY (memid),
       CONSTRAINT fk_members_recommendedby FOREIGN KEY (recommendedby)
            REFERENCES cd.members(memid) ON DELETE SET NULL
    );
```

```
  CREATE TABLE cd.facilities
    (
       facid integer NOT NULL, 
       name character varying(100) NOT NULL, 
       membercost numeric NOT NULL, 
       guestcost numeric NOT NULL, 
       initialoutlay numeric NOT NULL, 
       monthlymaintenance numeric NOT NULL, 
       CONSTRAINT facilities_pk PRIMARY KEY (facid)
    );
```

```
CREATE TABLE cd.bookings
    (
       bookid integer NOT NULL, 
       facid integer NOT NULL, 
       memid integer NOT NULL, 
       starttime timestamp NOT NULL,
       slots integer NOT NULL,
       CONSTRAINT bookings_pk PRIMARY KEY (bookid),
       CONSTRAINT fk_bookings_facid FOREIGN KEY (facid) REFERENCES cd.facilities(facid),
       CONSTRAINT fk_bookings_memid FOREIGN KEY (memid) REFERENCES cd.members(memid)
    );
```

## Scripts

- [clubdata.sql](https://github.com/Jarvis-Consulting-Group/jarvis_data_eng-viraniyagnik/blob/sql_queries/sql/clubdata.sql)
to Load sample data into your database

- [queries.sql](https://github.com/Jarvis-Consulting-Group/jarvis_data_eng-viraniyagnik/blob/sql_queries/sql/queries.sql)
SQL exercise 

 

## Database and Tables
![image](https://user-images.githubusercontent.com/77527826/216482280-4ff36481-7a1a-46c2-88bb-efe605de099b.png)       



