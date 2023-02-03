--add new facility into the table
insert into cd.facilities
    (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
    values (9, 'Spa', 20, 30, 100000, 800); 
	
--add the spa to the facilities table, automatically generate the value for the next facid
insert into cd.facilities
    (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
    select (select max(facid) from cd.facilities)+1, 'Spa', 20, 30, 100000, 800;
	
--update the second tennis court. Set initial outlay 10000
update cd.facilities
    set initialoutlay = 10000
    where facid = 1; 
	
--alter the price of the second tennis court so that it costs 10% more than the first one
update cd.facilities facs
    set
        membercost = (select membercost * 1.1 from cd.facilities where facid = 0),
        guestcost = (select guestcost * 1.1 from cd.facilities where facid = 0)
    where facs.facid = 1;  
	
--delete all bookings from the cd.bookings table
delete from cd.bookings; 

--remove member 37
delete from cd.members where memid = 37;  

--produce a list of facilities that charge a fee to members, and that fee is less than 1/50th of the monthly maintenance cost? Return the facid, facility name, member cost, and monthly maintenance of the facilities in question.
select facid, name, membercost, monthlymaintenance 
	from cd.facilities 
	where 
		membercost > 0 and 
		(membercost < monthlymaintenance/50.0);  
		
--produce a list of all facilities with the word 'Tennis' in their name
select *
	from cd.facilities 
	where 
		name like '%Tennis%'; 
		
--retrieve the details of facilities with ID 1 and 5
select *
	from cd.facilities 
	where 
		facid in (1,5); 
		
-- produce a list of members who joined after the start of September 2012? Return the memid, surname, firstname, and joindate of the members in question.
select memid, surname, firstname, joindate 
	from cd.members
	where joindate >= '2012-09-01';
	
	
--combined list of all surnames and all facility names
select surname 
	from cd.members
union
select name
	from cd.facilities;  
	
--produce a list of the start times for bookings by members named 'David Farrell'
select bks.starttime 
	from 
		cd.bookings bks
		inner join cd.members mems
			on mems.memid = bks.memid
	where 
		mems.firstname='David' 
		and mems.surname='Farrell'; 
		
--produce a list of the start times for bookings for tennis courts, for the date '2012-09-21'? Return a list of start time and facility name pairings, ordered by the time
select bks.starttime as start, facs.name as name
	from 
		cd.facilities facs
		inner join cd.bookings bks
			on facs.facid = bks.facid
	where 
		facs.name in ('Tennis Court 2','Tennis Court 1') and
		bks.starttime >= '2012-09-21' and
		bks.starttime < '2012-09-22'
order by bks.starttime; 

-- list of all members, including the individual who recommended them (if any)? Ensure that results are ordered by (surname, firstname).
select mems.firstname as memfname, mems.surname as memsname, recs.firstname as recfname, recs.surname as recsname
	from 
		cd.members mems
		left outer join cd.members recs
			on recs.memid = mems.recommendedby
order by memsname, memfname;

--list of all members who have recommended another member? Ensure that there are no duplicates in the list, and that results are ordered by (surname, firstname)
select distinct recs.firstname as firstname, recs.surname as surname
	from 
		cd.members mems
		inner join cd.members recs
			on recs.memid = mems.recommendedby
order by surname, firstname;

--list of all members, including the individual who recommended them (if any), without using any joins? Ensure that there are no duplicates in the list, and that each firstname + surname pairing is formatted as a column and ordered.
select distinct mems.firstname || ' ' ||  mems.surname as member,
	(select recs.firstname || ' ' || recs.surname as recommender 
		from cd.members recs 
		where recs.memid = mems.recommendedby
	)
	from 
		cd.members mems
order by member; 

--Produce a count of the number of recommendations each member has made. Order by member ID.
select recommendedby, count(*) 
	from cd.members
	where recommendedby is not null
	group by recommendedby
order by recommendedby;

--Produce a list of the total number of slots booked per facility. For now, just produce an output table consisting of facility id and slots, sorted by facility id.
select facid, sum(slots) as "Total Slots"
	from cd.bookings
	group by facid
order by facid;  

--Produce a list of the total number of slots booked per facility in the month of September 2012. Produce an output table consisting of facility id and slots, sorted by the number of slots.
select facid, sum(slots) as "Total Slots"
	from cd.bookings
	where
		starttime >= '2012-09-01'
		and starttime < '2012-10-01'
	group by facid
order by sum(slots); 

--Produce a list of the total number of slots booked per facility per month in the year of 2012. Produce an output table consisting of facility id and slots, sorted by the id and month.
select facid, extract(month from starttime) as month, sum(slots) as "Total Slots"
	from cd.bookings
	where extract(year from starttime) = 2012
	group by facid, month
order by facid, month; 

--Find the total number of members (including guests) who have made at least one booking.
select count(distinct memid) from cd.bookings  

--Produce a list of each member name, id, and their first booking after September 1st 2012. Order by member ID.
select mems.surname, mems.firstname, mems.memid, min(bks.starttime) as starttime
	from cd.bookings bks
	inner join cd.members mems on
		mems.memid = bks.memid
	where starttime >= '2012-09-01'
	group by mems.surname, mems.firstname, mems.memid
order by mems.memid; 

--Produce a list of member names, with each row containing the total member count. Order by join date, and include guest members.
select count(*) over(), firstname, surname
	from cd.members
order by joindate   

--Produce a monotonically increasing numbered list of members (including guests), ordered by their date of joining. Remember that member IDs are not guaranteed to be sequential.
select row_number() over(order by joindate), firstname, surname
	from cd.members
order by joindate

--Output the facility id that has the highest number of slots booked. Ensure that in the event of a tie, all tieing results get output.
select facid, total from (
	select facid, sum(slots) total, rank() over (order by sum(slots) desc) rank
        	from cd.bookings
		group by facid
	) as ranked
	where rank = 1   
	
--Output the names of all members, formatted as 'Surname, Firstname'
select surname || ', ' || firstname as name from cd.members     

-- find all the telephone numbers that contain parentheses, returning the member ID and telephone number sorted by member ID.
select memid, telephone from cd.members where telephone ~ '[()]';   

--produce a count of how many members you have whose surname starts with each letter of the alphabet. Sort by the letter, and don't worry about printing out a letter if the count is 0.
select substr (mems.surname,1,1) as letter, count(*) as count 
    from cd.members mems
    group by letter
    order by letter  
