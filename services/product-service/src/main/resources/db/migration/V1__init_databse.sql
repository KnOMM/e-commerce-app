CREATE TABLE IF NOT EXISTS category
(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY
    , description VARCHAR (255)
    , name VARCHAR (255)
);

CREATE TABLE IF NOT EXISTS product
(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY
    , description VARCHAR (255)
    , name VARCHAR (255)
    , available_quantity BIGINT NOT NULL
    , price numeric(38, 2)
    , category_id UUID
        CONSTRAINT category_constraint REFERENCES category
);
