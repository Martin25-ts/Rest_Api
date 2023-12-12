CREATE TABLE IF NOT EXISTS listings (
   id serial,
   user_id INTEGER NOT NULL,
   listing_type TEXT NOT NULL,
   price INTEGER NOT NULL,
   created_at BIGINT NOT NULL,
   updated_at BIGINT NOT NULL
);