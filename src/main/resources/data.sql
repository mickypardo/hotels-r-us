insert into hotelsrus_database.hotels(name, category) values('Casimiro Valley', 2);
insert into hotelsrus_database.hotels(name, category) values('Sargento Pimienta', 3);
insert into hotelsrus_database.hotels(name, category) values('Ohana Palmer', 5);
insert into hotelsrus_database.hotels(name, category) values('Santa Ana Beach', 5);
insert into hotelsrus_database.hotels(name, category) values('Leo Sagitarium', 4);

insert into hotelsrus_database.availabilities(date, id_hotel, rooms) values('2023-04-02', 12, 3);
insert into hotelsrus_database.availabilities(date, id_hotel, rooms) values('2023-04-03', 12, 3);
insert into hotelsrus_database.availabilities(date, id_hotel, rooms) values('2023-04-04', 13, 3);
insert into hotelsrus_database.availabilities(date, id_hotel, rooms) values('2023-04-05', 14, 3);
insert into hotelsrus_database.availabilities(date, id_hotel, rooms) values('2023-04-06', 15, 3);

insert into hotelsrus_database.bookings(id_hotel, date_from, date_to, email) 
	values(12, '2023-04-02', '2023-04-03', 'juangonzaloredondo@gmail.com');