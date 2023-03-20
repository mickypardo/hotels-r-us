--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-03-20 14:23:40

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'ISO_8859_8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 16398)
-- Name: hotelsrus_database; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA hotelsrus_database;


ALTER SCHEMA hotelsrus_database OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 16409)
-- Name: availabilities; Type: TABLE; Schema: hotelsrus_database; Owner: postgres
--

CREATE TABLE hotelsrus_database.availabilities (
    id_availability integer NOT NULL,
    date date NOT NULL,
    id_hotel integer,
    rooms integer
);


ALTER TABLE hotelsrus_database.availabilities OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16428)
-- Name: availabilities_id_availability_seq; Type: SEQUENCE; Schema: hotelsrus_database; Owner: postgres
--

ALTER TABLE hotelsrus_database.availabilities ALTER COLUMN id_availability ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME hotelsrus_database.availabilities_id_availability_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 217 (class 1259 OID 16404)
-- Name: bookings; Type: TABLE; Schema: hotelsrus_database; Owner: postgres
--

CREATE TABLE hotelsrus_database.bookings (
    id_booking integer NOT NULL,
    id_hotel integer,
    date_from date NOT NULL,
    date_to date NOT NULL,
    email character varying(50) NOT NULL
);


ALTER TABLE hotelsrus_database.bookings OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16427)
-- Name: booking_id_booking_seq; Type: SEQUENCE; Schema: hotelsrus_database; Owner: postgres
--

ALTER TABLE hotelsrus_database.bookings ALTER COLUMN id_booking ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME hotelsrus_database.booking_id_booking_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 16399)
-- Name: hotels; Type: TABLE; Schema: hotelsrus_database; Owner: postgres
--

CREATE TABLE hotelsrus_database.hotels (
    id_hotel integer NOT NULL,
    name character varying(30) NOT NULL,
    category integer
);


ALTER TABLE hotelsrus_database.hotels OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16426)
-- Name: hotels_id_hotel_seq; Type: SEQUENCE; Schema: hotelsrus_database; Owner: postgres
--

ALTER TABLE hotelsrus_database.hotels ALTER COLUMN id_hotel ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME hotelsrus_database.hotels_id_hotel_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3345 (class 0 OID 16409)
-- Dependencies: 218
-- Data for Name: availabilities; Type: TABLE DATA; Schema: hotelsrus_database; Owner: postgres
--

COPY hotelsrus_database.availabilities (id_availability, date, id_hotel, rooms) FROM stdin;
24	2023-04-02	12	3
25	2023-04-03	12	3
26	2023-04-04	13	3
27	2023-04-05	14	3
28	2023-04-06	15	3
\.


--
-- TOC entry 3344 (class 0 OID 16404)
-- Dependencies: 217
-- Data for Name: bookings; Type: TABLE DATA; Schema: hotelsrus_database; Owner: postgres
--

COPY hotelsrus_database.bookings (id_booking, id_hotel, date_from, date_to, email) FROM stdin;
1	12	2023-04-02	2023-04-03	juangonzaloredondo@gmail.com
\.


--
-- TOC entry 3343 (class 0 OID 16399)
-- Dependencies: 216
-- Data for Name: hotels; Type: TABLE DATA; Schema: hotelsrus_database; Owner: postgres
--

COPY hotelsrus_database.hotels (id_hotel, name, category) FROM stdin;
12	Casimiro Valley	2
13	Sargento Pimienta	3
14	Ohana Palmer	5
15	Santa Ana Beach	5
16	Leo Sagitarium	4
\.


--
-- TOC entry 3354 (class 0 OID 0)
-- Dependencies: 221
-- Name: availabilities_id_availability_seq; Type: SEQUENCE SET; Schema: hotelsrus_database; Owner: postgres
--

SELECT pg_catalog.setval('hotelsrus_database.availabilities_id_availability_seq', 29, true);


--
-- TOC entry 3355 (class 0 OID 0)
-- Dependencies: 220
-- Name: booking_id_booking_seq; Type: SEQUENCE SET; Schema: hotelsrus_database; Owner: postgres
--

SELECT pg_catalog.setval('hotelsrus_database.booking_id_booking_seq', 1, true);


--
-- TOC entry 3356 (class 0 OID 0)
-- Dependencies: 219
-- Name: hotels_id_hotel_seq; Type: SEQUENCE SET; Schema: hotelsrus_database; Owner: postgres
--

SELECT pg_catalog.setval('hotelsrus_database.hotels_id_hotel_seq', 22, true);


--
-- TOC entry 3195 (class 2606 OID 16413)
-- Name: availabilities availabilities_pkey; Type: CONSTRAINT; Schema: hotelsrus_database; Owner: postgres
--

ALTER TABLE ONLY hotelsrus_database.availabilities
    ADD CONSTRAINT availabilities_pkey PRIMARY KEY (id_availability);


--
-- TOC entry 3190 (class 2606 OID 16408)
-- Name: bookings booking_pkey; Type: CONSTRAINT; Schema: hotelsrus_database; Owner: postgres
--

ALTER TABLE ONLY hotelsrus_database.bookings
    ADD CONSTRAINT booking_pkey PRIMARY KEY (id_booking);


--
-- TOC entry 3197 (class 2606 OID 16430)
-- Name: availabilities date_uk; Type: CONSTRAINT; Schema: hotelsrus_database; Owner: postgres
--

ALTER TABLE ONLY hotelsrus_database.availabilities
    ADD CONSTRAINT date_uk UNIQUE (date) INCLUDE (date);


--
-- TOC entry 3192 (class 2606 OID 16434)
-- Name: bookings email_uk; Type: CONSTRAINT; Schema: hotelsrus_database; Owner: postgres
--

ALTER TABLE ONLY hotelsrus_database.bookings
    ADD CONSTRAINT email_uk UNIQUE (email) INCLUDE (email);


--
-- TOC entry 3186 (class 2606 OID 16403)
-- Name: hotels hotels_pkey; Type: CONSTRAINT; Schema: hotelsrus_database; Owner: postgres
--

ALTER TABLE ONLY hotelsrus_database.hotels
    ADD CONSTRAINT hotels_pkey PRIMARY KEY (id_hotel);


--
-- TOC entry 3188 (class 2606 OID 16432)
-- Name: hotels name_uk; Type: CONSTRAINT; Schema: hotelsrus_database; Owner: postgres
--

ALTER TABLE ONLY hotelsrus_database.hotels
    ADD CONSTRAINT name_uk UNIQUE (name) INCLUDE (name);


--
-- TOC entry 3198 (class 1259 OID 16419)
-- Name: fki_availabilities_hotels_fkey; Type: INDEX; Schema: hotelsrus_database; Owner: postgres
--

CREATE INDEX fki_availabilities_hotels_fkey ON hotelsrus_database.availabilities USING btree (id_hotel);


--
-- TOC entry 3193 (class 1259 OID 16425)
-- Name: fki_bookings_hotels_fkey; Type: INDEX; Schema: hotelsrus_database; Owner: postgres
--

CREATE INDEX fki_bookings_hotels_fkey ON hotelsrus_database.bookings USING btree (id_hotel);


--
-- TOC entry 3200 (class 2606 OID 16414)
-- Name: availabilities availabilities_hotels_fkey; Type: FK CONSTRAINT; Schema: hotelsrus_database; Owner: postgres
--

ALTER TABLE ONLY hotelsrus_database.availabilities
    ADD CONSTRAINT availabilities_hotels_fkey FOREIGN KEY (id_hotel) REFERENCES hotelsrus_database.hotels(id_hotel) NOT VALID;


--
-- TOC entry 3199 (class 2606 OID 16420)
-- Name: bookings bookings_hotels_fkey; Type: FK CONSTRAINT; Schema: hotelsrus_database; Owner: postgres
--

ALTER TABLE ONLY hotelsrus_database.bookings
    ADD CONSTRAINT bookings_hotels_fkey FOREIGN KEY (id_hotel) REFERENCES hotelsrus_database.hotels(id_hotel) NOT VALID;


-- Completed on 2023-03-20 14:23:40

--
-- PostgreSQL database dump complete
--

