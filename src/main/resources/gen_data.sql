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