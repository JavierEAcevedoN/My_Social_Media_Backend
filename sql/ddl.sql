DROP TABLE IF EXISTS public."user";

CREATE TABLE IF NOT EXISTS public."user"
(
    username character varying(128) COLLATE pg_catalog."default" NOT NULL,
    email character varying(256) COLLATE pg_catalog."default" NOT NULL,
    full_name character varying(512) COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    phone character varying(16) COLLATE pg_catalog."default" NOT NULL,
    birth_date date NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone,
    biography text COLLATE pg_catalog."default",
    profile_photo text COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (username),
    CONSTRAINT user_email_key UNIQUE (email)
);

DROP SEQUENCE IF EXISTS public.follow_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.follow_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS public.follow;

CREATE TABLE IF NOT EXISTS public.follow
(
    id bigint NOT NULL DEFAULT nextval('follow_id_seq'::regclass),
    followed timestamp without time zone NOT NULL,
    follow_from character varying(128) COLLATE pg_catalog."default" NOT NULL,
    follow_to character varying(128) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT follow_pkey PRIMARY KEY (id),
    CONSTRAINT follow_follow_from_follow_to_key UNIQUE (follow_from, follow_to),
    CONSTRAINT follow_follow_from_fkey FOREIGN KEY (follow_from)
        REFERENCES public."user" (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT follow_follow_to_fkey FOREIGN KEY (follow_to)
        REFERENCES public."user" (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

DROP SEQUENCE IF EXISTS public.notification_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.notification_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS public.notification;

CREATE TABLE IF NOT EXISTS public.notification
(
    id bigint NOT NULL DEFAULT nextval('notification_id_seq'::regclass),
    content text COLLATE pg_catalog."default" NOT NULL,
    sended timestamp without time zone NOT NULL,
    readed boolean NOT NULL,
    username character varying(128) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT notification_pkey PRIMARY KEY (id),
    CONSTRAINT notification_username_fkey FOREIGN KEY (username)
        REFERENCES public."user" (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

DROP SEQUENCE IF EXISTS public.publication_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.publication_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS public.publication;

CREATE TABLE IF NOT EXISTS public.publication
(
    id bigint NOT NULL DEFAULT nextval('publication_id_seq'::regclass),
    content character varying(500) COLLATE pg_catalog."default" NOT NULL,
    img_src text COLLATE pg_catalog."default",
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone,
    tags character varying(24)[] COLLATE pg_catalog."default",
    username character varying(128) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT publication_pkey PRIMARY KEY (id),
    CONSTRAINT publication_username_fkey FOREIGN KEY (username)
        REFERENCES public."user" (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

DROP SEQUENCE IF EXISTS public.comment_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.comment_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS public.comment;

CREATE TABLE IF NOT EXISTS public.comment
(
    id bigint NOT NULL DEFAULT nextval('comment_id_seq'::regclass),
    content character varying(200) COLLATE pg_catalog."default" NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone,
    tagged character varying(128) COLLATE pg_catalog."default",
    id_publication bigint NOT NULL,
    username character varying(128) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT comment_pkey PRIMARY KEY (id),
    CONSTRAINT comment_id_publication_fkey FOREIGN KEY (id_publication)
        REFERENCES public.publication (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT comment_tagged_fkey FOREIGN KEY (tagged)
        REFERENCES public."user" (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT comment_username_fkey FOREIGN KEY (username)
        REFERENCES public."user" (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

DROP SEQUENCE IF EXISTS public.like_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.like_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS public."like";

CREATE TABLE IF NOT EXISTS public."like"
(
    id bigint NOT NULL DEFAULT nextval('like_id_seq'::regclass),
    liked date NOT NULL,
    username character varying(128) COLLATE pg_catalog."default" NOT NULL,
    id_publication bigint NOT NULL,
    CONSTRAINT like_pkey PRIMARY KEY (id),
    CONSTRAINT like_username_id_publication_key UNIQUE (username, id_publication),
    CONSTRAINT like_id_publication_fkey FOREIGN KEY (id_publication)
        REFERENCES public.publication (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT like_username_fkey FOREIGN KEY (username)
        REFERENCES public."user" (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);