-- Database: damacaNaN

-- DROP DATABASE IF EXISTS damacaNaN;

CREATE DATABASE damacaNaN
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Turkish_Turkey.1254'
    LC_CTYPE = 'Turkish_Turkey.1254'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;




-- SCHEMA: public

-- DROP SCHEMA IF EXISTS public ;

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT USAGE ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO pg_database_owner;





-- Extension: pgcrypto

-- DROP EXTENSION pgcrypto;

CREATE EXTENSION IF NOT EXISTS pgcrypto
    SCHEMA public
    VERSION "1.3";
    
    


-- Table: public.role

-- DROP TABLE IF EXISTS public.role;

CREATE TABLE IF NOT EXISTS public.role
(
    role_id bigint NOT NULL,
    name character varying COLLATE pg_catalog."default",
    CONSTRAINT role_pkey PRIMARY KEY (role_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.role
    OWNER to postgres;



-- Table: public.permission

-- DROP TABLE IF EXISTS public.permission;

CREATE TABLE IF NOT EXISTS public.permission
(
    permission_id bigint NOT NULL,
    name character varying COLLATE pg_catalog."default",
    CONSTRAINT permission_pkey PRIMARY KEY (permission_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.permission
    OWNER to postgres;
    
    
    
-- SEQUENCE: public.permission_seq

-- DROP SEQUENCE IF EXISTS public.permission_seq;

CREATE SEQUENCE IF NOT EXISTS public.permission_seq
    INCREMENT 1
    START 23
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY permission.permission_id;

ALTER SEQUENCE public.permission_seq
    OWNER TO postgres;
    


    

-- Table: public.role_permission

-- DROP TABLE IF EXISTS public.role_permission;

CREATE TABLE IF NOT EXISTS public.role_permission
(
    role_permission_id bigint NOT NULL,
    role_id bigint,
    permission_id bigint,
    CONSTRAINT role_permission_pkey PRIMARY KEY (role_permission_id),
    CONSTRAINT permission_id FOREIGN KEY (permission_id)
        REFERENCES public.permission (permission_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT role_id FOREIGN KEY (role_id)
        REFERENCES public.role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.role_permission
    OWNER to postgres;



-- SEQUENCE: public.rolepermission_seq

-- DROP SEQUENCE IF EXISTS public.rolepermission_seq;

CREATE SEQUENCE IF NOT EXISTS public.rolepermission_seq
    INCREMENT 1
    START 34
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY role_permission.role_permission_id;

ALTER SEQUENCE public.rolepermission_seq
    OWNER TO postgres;





-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    user_id bigint NOT NULL,
    name character varying COLLATE pg_catalog."default",
    surname character varying COLLATE pg_catalog."default",
    email text COLLATE pg_catalog."default",
    password text COLLATE pg_catalog."default",
    role_id bigint,
    creation_date timestamp without time zone,
    update_date timestamp without time zone,
    pp_path bytea,
    is_deleted boolean,
    CONSTRAINT user_pkey PRIMARY KEY (user_id),
    CONSTRAINT role_id FOREIGN KEY (role_id)
        REFERENCES public.role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;




-- SEQUENCE: public.user_seq

-- DROP SEQUENCE IF EXISTS public.user_seq;

CREATE SEQUENCE IF NOT EXISTS public.user_seq
    INCREMENT 1
    START 3
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.user_seq
    OWNER TO postgres;





-- Table: public.category

-- DROP TABLE IF EXISTS public.category;

CREATE TABLE IF NOT EXISTS public.category
(
    category_id bigint NOT NULL,
    category_name text COLLATE pg_catalog."default",
    CONSTRAINT "Category_pkey" PRIMARY KEY (category_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.category
    OWNER to postgres;


-- SEQUENCE: public.category_seq

-- DROP SEQUENCE IF EXISTS public.category_seq;

CREATE SEQUENCE IF NOT EXISTS public.category_seq
    INCREMENT 1
    START 5
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY category.category_id;

ALTER SEQUENCE public.category_seq
    OWNER TO postgres;




-- Table: public.sub_category

-- DROP TABLE IF EXISTS public.sub_category;

CREATE TABLE IF NOT EXISTS public.sub_category
(
    subcategory_id bigint NOT NULL,
    subcategory_name text COLLATE pg_catalog."default",
    category_id bigint,
    CONSTRAINT sub_category_pkey PRIMARY KEY (subcategory_id),
    CONSTRAINT category_id FOREIGN KEY (category_id)
        REFERENCES public.category (category_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.sub_category
    OWNER to postgres;




-- SEQUENCE: public.subcategory_seq

-- DROP SEQUENCE IF EXISTS public.subcategory_seq;

CREATE SEQUENCE IF NOT EXISTS public.subcategory_seq
    INCREMENT 1
    START 26
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY sub_category.subcategory_id;

ALTER SEQUENCE public.subcategory_seq
    OWNER TO postgres;
    




-- Table: public.post

-- DROP TABLE IF EXISTS public.post;

CREATE TABLE IF NOT EXISTS public.post
(
    post_id bigint NOT NULL,
    name text COLLATE pg_catalog."default",
    description text COLLATE pg_catalog."default",
    user_id bigint,
    customer_id bigint,
    price double precision,
    status character varying COLLATE pg_catalog."default",
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    is_deleted boolean,
    is_sold boolean,
    sold_date timestamp without time zone,
    update_date timestamp without time zone,
    category_id bigint,
    subcategory_id bigint,
    CONSTRAINT post_pkey PRIMARY KEY (post_id),
    CONSTRAINT category_id FOREIGN KEY (category_id)
        REFERENCES public.category (category_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT subcategory_id FOREIGN KEY (subcategory_id)
        REFERENCES public.sub_category (subcategory_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.post
    OWNER to postgres;



-- SEQUENCE: public.post_seq

-- DROP SEQUENCE IF EXISTS public.post_seq;

CREATE SEQUENCE IF NOT EXISTS public.post_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.post_seq
    OWNER TO postgres;





-- Table: public.post_files

-- DROP TABLE IF EXISTS public.post_files;

CREATE TABLE IF NOT EXISTS public.post_files
(
    file_id bigint NOT NULL,
    post_id bigint,
    is_deleted boolean,
    file bytea,
    file_media_type text COLLATE pg_catalog."default",
    CONSTRAINT post_files_pkey PRIMARY KEY (file_id),
    CONSTRAINT post_id FOREIGN KEY (post_id)
        REFERENCES public.post (post_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.post_files
    OWNER to postgres;




-- SEQUENCE: public.postfiles_seq

-- DROP SEQUENCE IF EXISTS public.postfiles_seq;

CREATE SEQUENCE IF NOT EXISTS public.postfiles_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY post_files.file_id;

ALTER SEQUENCE public.postfiles_seq
    OWNER TO postgres;





-- Table: public.reviews

-- DROP TABLE IF EXISTS public.reviews;

CREATE TABLE IF NOT EXISTS public.reviews
(
    review_id bigint NOT NULL,
    user_id bigint,
    seller_id bigint,
    post_id bigint,
    point_scale double precision,
    description text COLLATE pg_catalog."default",
    create_date timestamp without time zone,
    is_deleted boolean DEFAULT 'false',
    CONSTRAINT reviews_pkey PRIMARY KEY (review_id),
    CONSTRAINT post_id FOREIGN KEY (post_id)
        REFERENCES public.post (post_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT seller_id FOREIGN KEY (seller_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.reviews
    OWNER to postgres;




-- SEQUENCE: public.review_seq

-- DROP SEQUENCE IF EXISTS public.review_seq;

CREATE SEQUENCE IF NOT EXISTS public.review_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY reviews.review_id;

ALTER SEQUENCE public.review_seq
    OWNER TO postgres;





-- Table: public.message

-- DROP TABLE IF EXISTS public.message;

CREATE TABLE IF NOT EXISTS public.message
(
    message_id bigint NOT NULL,
    subject character varying COLLATE pg_catalog."default",
    message_body text COLLATE pg_catalog."default",
    creation_date timestamp without time zone,
    parent_message_id bigint NOT NULL,
    expiration_date timestamp without time zone,
    creator_id bigint,
    recipient_id bigint,
    is_read boolean,
    CONSTRAINT message_pkey PRIMARY KEY (message_id),
    CONSTRAINT creator_id FOREIGN KEY (creator_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT recipient_id FOREIGN KEY (recipient_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.message
    OWNER to postgres;


-- SEQUENCE: public.message_seq

-- DROP SEQUENCE IF EXISTS public.message_seq;

CREATE SEQUENCE IF NOT EXISTS public.message_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY message.message_id;

ALTER SEQUENCE public.message_seq
    OWNER TO postgres;





-- Table: public.favourites

-- DROP TABLE IF EXISTS public.favourites;

CREATE TABLE IF NOT EXISTS public.favourites
(
    user_id bigint NOT NULL,
    post_id bigint NOT NULL,
    CONSTRAINT favourites_pkey PRIMARY KEY (user_id, post_id)
    INCLUDE(user_id, post_id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.favourites
    OWNER to postgres;




-- Table: public.action_log

-- DROP TABLE IF EXISTS public.action_log;

CREATE TABLE IF NOT EXISTS public.action_log
(
    action_id bigint NOT NULL,
    is_user_act boolean,
    is_system_act boolean,
    description text COLLATE pg_catalog."default",
    action_date timestamp without time zone,
    user_id bigint,
    CONSTRAINT action_log_pkey PRIMARY KEY (action_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.action_log
    OWNER to postgres;



-- SEQUENCE: public.action_seq

-- DROP SEQUENCE IF EXISTS public.action_seq;

CREATE SEQUENCE IF NOT EXISTS public.action_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY action_log.action_id;

ALTER SEQUENCE public.action_seq
    OWNER TO postgres;