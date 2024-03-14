insert into Ingredient (id, name, type) values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, type) values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, type) values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, type) values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type) values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type) values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type) values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type) values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type) values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type) values ('SRCR', 'Sour Cream', 'SAUCE');

INSERT INTO public.taco_order(id, delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip,
                              cc_number, cc_expiration, cccvv, placed_at)
SELECT id,
       'name',
       md5(random()::text),
       md5(random()::text),
       'qw',
       md5(random()::text),
       '378282246310005',
       '01/01',
       '123',
       NOW() - '1 day'::INTERVAL * (RANDOM()::int * 100 + 100)
FROM generate_series(1, 100) id;

ALTER SEQUENCE taco_order_seq RESTART WITH 105;

