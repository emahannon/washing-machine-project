/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 14.1 		*/
/*  Created On : 21-Nov-2021 6:53:43 PM 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */

/* Drop Tables */

DROP TABLE IF EXISTS "building" CASCADE
;

DROP TABLE IF EXISTS "complaint_about_student" CASCADE
;

DROP TABLE IF EXISTS "complaint_about_wm" CASCADE
;

DROP TABLE IF EXISTS "key" CASCADE
;

DROP TABLE IF EXISTS "penelties" CASCADE
;

DROP TABLE IF EXISTS "receptionist" CASCADE
;

DROP TABLE IF EXISTS "reservation" CASCADE
;

DROP TABLE IF EXISTS "room" CASCADE
;

DROP TABLE IF EXISTS "room_history" CASCADE
;

DROP TABLE IF EXISTS "student" CASCADE
;

DROP TABLE IF EXISTS "swap_request" CASCADE
;

DROP TABLE IF EXISTS "washing_machine" CASCADE
;

/* Create Tables */

CREATE SEQUENCE hibernate_sequence START 1;

CREATE SEQUENCE my_seq_gen START 1;

CREATE TABLE "building"
(
	building_id integer NOT NULL,
	name varchar(50) NULL,
	address varchar(100) NOT NULL,
	number integer NOT NULL,
	location integer NOT NULL
)
;

CREATE TABLE "complaint_about_student"
(
	complaint_student_id integer NOT NULL,
	created_time timestamp without time zone NOT NULL,
	description varchar(500) NOT NULL,
	status varchar(50) NOT NULL,
	cloed_time timestamp without time zone NULL,
	receptionist_id integer NULL,
	student_id integer NOT NULL
)
;

CREATE TABLE "complaint_about_wm"
(
	complaint_wm_id integer NOT NULL,
	created_time timestamp without time zone NOT NULL,
	description varchar(500) NOT NULL,
	status varchar(50) NOT NULL,
	closed_time timestamp without time zone NULL,
	reservation_id integer NOT NULL,
	washing_machine_id integer NOT NULL,
	receptionist_id integer NULL
)
;

CREATE TABLE "key"
(
	key_id integer NOT NULL,
	student_id integer NULL,
	return_status boolean NOT NULL,
	washing_machine_id integer NOT NULL
)
;

CREATE TABLE "penelties"
(
	penelties_id integer NOT NULL,
	points_before integer NOT NULL,
	start_date timestamp without time zone NOT NULL,
	complaint_student_id integer NOT NULL
)
;

CREATE TABLE "receptionist"
(
	receptionist_id integer NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	building_id integer NOT NULL,
	complaint_wm_id integer NULL
)
;

CREATE TABLE "reservation"
(
	reservation_id integer NOT NULL,
	created_date date NOT NULL,
	start_time timestamp without time zone NOT NULL,
	end_time timestamp without time zone NOT NULL,
	student_id integer NOT NULL,
	washing_machine_id integer NOT NULL,
	swap_req_id integer NULL
)
;

CREATE TABLE "room"
(
	room_id integer NOT NULL,
	number integer NOT NULL,
	building_id integer NOT NULL
)
;

CREATE TABLE "room_history"
(
	student_id integer NOT NULL,
	room_id integer NOT NULL,
	start_date date NOT NULL,
	end_date date NULL
)
;

CREATE TABLE "student"
(
	student_id integer NOT NULL,
	current_occupent boolean NOT NULL,
	penelty_points integer NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL
)
;

CREATE TABLE "swap_request"
(
	swap_req_id integer NOT NULL,
	time_req timestamp without time zone NOT NULL,
	reason varchar(500) NULL,
	status boolean NOT NULL,
	student_id integer NOT NULL
)
;

CREATE TABLE "washing_machine"
(
	washing_machine_id integer NOT NULL,
	status boolean NOT NULL,
	floor_bumber integer NOT NULL,
	building_id integer NOT NULL
)
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE "building" ADD CONSTRAINT "PK_BUILDING"
	PRIMARY KEY (building_id)
;

ALTER TABLE "complaint_about_student" ADD CONSTRAINT "PK_COMPLAINT_ON_STUDENT"
	PRIMARY KEY (complaint_student_id)
;

ALTER TABLE "complaint_about_wm" ADD CONSTRAINT "PK_COMPLAINT_ABOUT_WM"
	PRIMARY KEY (complaint_wm_id)
;

CREATE INDEX "IXFK_COMPLAINT_ABOUT_WM_RECEPTIONIST" ON "complaint_about_wm" (receptionist_id ASC)
;

ALTER TABLE "key" ADD CONSTRAINT "PK_Key"
	PRIMARY KEY (key_id)
;

ALTER TABLE "penelties" ADD CONSTRAINT "PK_PENELTIES"
	PRIMARY KEY (penelties_id)
;

ALTER TABLE "receptionist" ADD CONSTRAINT "PK_RECEPTIONIST"
	PRIMARY KEY (receptionist_id)
;

CREATE INDEX "IXFK_RECEPTIONIST_BUILDING" ON "receptionist" (building_id ASC)
;

CREATE INDEX "IXFK_RECEPTIONIST_COMPLAINT_ABOUT_WM" ON "receptionist" (complaint_wm_id ASC)
;

ALTER TABLE "reservation" ADD CONSTRAINT "PK_RESERVATION"
	PRIMARY KEY (reservation_id)
;

ALTER TABLE "room" ADD CONSTRAINT "PK_ROOM"
	PRIMARY KEY (room_id)
;

CREATE INDEX "IXFK_ROOM_BUILDING" ON "room" (building_id ASC)
;

ALTER TABLE "room_history" ADD CONSTRAINT "PK_ROOM_HISTORY"
	PRIMARY KEY (student_id,room_id)
;

ALTER TABLE "student" ADD CONSTRAINT "PK_STUDENT"
	PRIMARY KEY (student_id)
;

ALTER TABLE "swap_request" ADD CONSTRAINT "PK_SWAP_REQUEST"
	PRIMARY KEY (swap_req_id)
;

ALTER TABLE "washing_machine" ADD CONSTRAINT "PK_WASHING_MACHINE"
	PRIMARY KEY (washing_machine_id)
;

/* Create Foreign Key Constraints */

ALTER TABLE "complaint_about_student" ADD CONSTRAINT "FK_COMPLAINT_ON_STUDENT_RECEPTIONIST"
	FOREIGN KEY (receptionist_id) REFERENCES "receptionist" (receptionist_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "complaint_about_student" ADD CONSTRAINT "FK_COMPLAINT_ON_STUDENT_STUDENT"
	FOREIGN KEY (student_id) REFERENCES "student" (student_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "complaint_about_wm" ADD CONSTRAINT "FK_COMPLAINT_ABOUT_WM_RECEPTIONIST"
	FOREIGN KEY (receptionist_id) REFERENCES "receptionist" (receptionist_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "complaint_about_wm" ADD CONSTRAINT "FK_COMPLAINT_ABOUT_WM_RESERVATION"
	FOREIGN KEY (reservation_id) REFERENCES "reservation" (reservation_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "complaint_about_wm" ADD CONSTRAINT "FK_COMPLAINT_ABOUT_WM_WASHING_MACHINE"
	FOREIGN KEY (washing_machine_id) REFERENCES "washing_machine" (washing_machine_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "key" ADD CONSTRAINT "FK_KEY_STUDENT"
	FOREIGN KEY (student_id) REFERENCES "student" (student_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "key" ADD CONSTRAINT "FK_KEY_WASHING_MACHINE"
	FOREIGN KEY (washing_machine_id) REFERENCES "washing_machine" (washing_machine_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "penelties" ADD CONSTRAINT "FK_PENELTIES_COMPLAINT_ON_STUDENT"
	FOREIGN KEY (complaint_student_id) REFERENCES "complaint_about_student" (complaint_student_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "receptionist" ADD CONSTRAINT "FK_RECEPTIONIST_BUILDING"
	FOREIGN KEY (building_id) REFERENCES "building" (building_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "reservation" ADD CONSTRAINT "FK_RESERVATION_STUDENT"
	FOREIGN KEY (student_id) REFERENCES "student" (student_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "reservation" ADD CONSTRAINT "FK_RESERVATION_SWAP_REQUEST"
	FOREIGN KEY (swap_req_id) REFERENCES "swap_request" (swap_req_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "reservation" ADD CONSTRAINT "FK_RESERVATION_WASHING_MACHINE"
	FOREIGN KEY (washing_machine_id) REFERENCES "washing_machine" (washing_machine_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "room" ADD CONSTRAINT "FK_ROOM_BUILDING"
	FOREIGN KEY (building_id) REFERENCES "building" (building_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "room_history" ADD CONSTRAINT "FK_ROOM_HISTORY_ROOM"
	FOREIGN KEY (room_id) REFERENCES "room" (room_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "room_history" ADD CONSTRAINT "FK_ROOM_HISTORY_STUDENT"
	FOREIGN KEY (student_id) REFERENCES "student" (student_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "swap_request" ADD CONSTRAINT "FK_SWAP_REQUEST_STUDENT"
	FOREIGN KEY (student_id) REFERENCES "student" (student_id) ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "washing_machine" ADD CONSTRAINT "FK_WASHING_MACHINE_BUILDING"
	FOREIGN KEY (building_id) REFERENCES "building" (building_id) ON DELETE No Action ON UPDATE No Action
;

/* Create Table Comments, Sequences for Autonumber Columns */

COMMENT ON TABLE "building"
	IS 'Building table stores all buildings of univercity'
;

COMMENT ON TABLE "key"
	IS 'Key table stores data of keys for washing machine'
;

COMMENT ON TABLE "reservation"
	IS 'Reservation tables stores reservations of students'
;

COMMENT ON TABLE "room"
	IS 'Room table stores data of rooms in building.'
;

COMMENT ON TABLE "room_history"
	IS 'Room_history table stores student stay dates in the room'
;

COMMENT ON TABLE "student"
	IS 'Student table stores all data of students '
;

COMMENT ON TABLE "washing_machine"
	IS 'Washing_machine table stores all data about washing machine'
;
