CREATE TABLE IF NOT EXISTS public.users
(
    id bigserial NOT NULL,
    username character varying(255),
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_username_key UNIQUE (username)
)

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.product_type
(
    id serial NOT NULL DEFAULT,
    name character varying(10),
    CONSTRAINT product_type_pkey PRIMARY KEY (id),
    CONSTRAINT uname UNIQUE (name)
)
ALTER TABLE IF EXISTS public.product_type
    OWNER to postgres;

 CREATE TABLE IF NOT EXISTS public.products
 (
     id bigserial NOT NULL,
     user_id bigint NOT NULL,
     balance numeric(10,2),
     acc_num character varying(20),
     product_type_id bigint,
     CONSTRAINT products_pkey PRIMARY KEY (id),
     CONSTRAINT product_type_fkey FOREIGN KEY (product_type_id)
         REFERENCES public.product_type (id) MATCH SIMPLE
         ON UPDATE NO ACTION
         ON DELETE NO ACTION
         NOT VALID,
     CONSTRAINT users_fkey FOREIGN KEY (user_id)
         REFERENCES public.users (id) MATCH SIMPLE
         ON UPDATE NO ACTION
         ON DELETE NO ACTION
         NOT VALID
 )

 ALTER TABLE IF EXISTS public.products
     OWNER to postgres;

INSERT INTO public.users(username) VALUES ('Ann');
INSERT INTO public.users(username) VALUES ('John');

INSERT INTO public.product_type(name) VALUES ('CARD');
INSERT INTO public.product_type(name) VALUES ('ACCOUNT');

INSERT INTO public.products(user_id, balance, acc_num, product_type_id)
	VALUES (1, 2000, '123456', 1);
INSERT INTO public.products(user_id, balance, acc_num, product_type_id)
	VALUES (2, 3400, '654321', 2);