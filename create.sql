create sequence taco_order_seq start with 1 increment by 50;
create sequence taco_seq start with 1 increment by 50;
create table ingredient
(
    id   varchar(255) not null,
    name varchar(255),
    type varchar(255) check (type in ('WRAP', 'PROTEIN', 'VEGGIES', 'CHEESE', 'SAUCE')),
    primary key (id)
);
create table taco
(
    created_at timestamp(6),
    id         bigint       not null,
    name       varchar(255) not null,
    primary key (id)
);
create table taco_ingredients
(
    taco_id        bigint       not null,
    ingredients_id varchar(255) not null
);
create table taco_order
(
    delivery_state  varchar(2),
    id              bigint not null,
    placed_at       timestamp(6),
    cc_expiration   varchar(255),
    cc_number       varchar(255),
    cccvv           varchar(255),
    delivery_city   varchar(255),
    delivery_name   varchar(255),
    delivery_street varchar(255),
    delivery_zip    varchar(255),
    primary key (id)
);
create table taco_order_tacos
(
    taco_order_id bigint not null,
    tacos_id      bigint not null unique
);
alter table if exists taco_ingredients
    add constraint FK7y679y77n5e75s3ss1v7ff14j foreign key (ingredients_id) references ingredient;
alter table if exists taco_ingredients
    add constraint FK27rycuh3mjaepnba0j6m8xl4q foreign key (taco_id) references taco;
alter table if exists taco_order_tacos
    add constraint FKfwvqtnjfview9e5f7bfqtd1ns foreign key (tacos_id) references taco;
alter table if exists taco_order_tacos
    add constraint FKs8yteduju5tndbivxbmdrbsyy foreign key (taco_order_id) references taco_order;
create sequence taco_order_seq start with 1 increment by 50;
create sequence taco_seq start with 1 increment by 50;
create table ingredient (id varchar(255) not null, name varchar(255), type varchar(255) check (type in ('WRAP','PROTEIN','VEGGIES','CHEESE','SAUCE')), primary key (id));
create table taco (created_at timestamp(6), id bigint not null, name varchar(255) not null, primary key (id));
create table taco_ingredients (taco_id bigint not null, ingredients_id varchar(255) not null);
create table taco_order (delivery_state varchar(2), id bigint not null, placed_at timestamp(6), cc_expiration varchar(255), cc_number varchar(255), cccvv varchar(255), delivery_city varchar(255), delivery_name varchar(255), delivery_street varchar(255), delivery_zip varchar(255), primary key (id));
create table taco_order_tacos (taco_order_id bigint not null, tacos_id bigint not null unique);
alter table if exists taco_ingredients add constraint FK7y679y77n5e75s3ss1v7ff14j foreign key (ingredients_id) references ingredient;
alter table if exists taco_ingredients add constraint FK27rycuh3mjaepnba0j6m8xl4q foreign key (taco_id) references taco;
alter table if exists taco_order_tacos add constraint FKfwvqtnjfview9e5f7bfqtd1ns foreign key (tacos_id) references taco;
alter table if exists taco_order_tacos add constraint FKs8yteduju5tndbivxbmdrbsyy foreign key (taco_order_id) references taco_order;
create sequence taco_order_seq start with 1 increment by 50;
create sequence taco_seq start with 1 increment by 50;
create table ingredient (id varchar(255) not null, name varchar(255), type varchar(255) check (type in ('WRAP','PROTEIN','VEGGIES','CHEESE','SAUCE')), primary key (id));
create table taco (created_at timestamp(6), id bigint not null, name varchar(255) not null, primary key (id));
create table taco_ingredients (taco_id bigint not null, ingredients_id varchar(255) not null);
create table taco_order (delivery_state varchar(2), id bigint not null, placed_at timestamp(6), cc_expiration varchar(255), cc_number varchar(255), cccvv varchar(255), delivery_city varchar(255), delivery_name varchar(255), delivery_street varchar(255), delivery_zip varchar(255), primary key (id));
create table taco_order_tacos (taco_order_id bigint not null, tacos_id bigint not null unique);
alter table if exists taco_ingredients add constraint FK7y679y77n5e75s3ss1v7ff14j foreign key (ingredients_id) references ingredient;
alter table if exists taco_ingredients add constraint FK27rycuh3mjaepnba0j6m8xl4q foreign key (taco_id) references taco;
alter table if exists taco_order_tacos add constraint FKfwvqtnjfview9e5f7bfqtd1ns foreign key (tacos_id) references taco;
alter table if exists taco_order_tacos add constraint FKs8yteduju5tndbivxbmdrbsyy foreign key (taco_order_id) references taco_order;
create sequence taco_order_seq start with 1 increment by 50;
create sequence taco_seq start with 1 increment by 50;
create table ingredient (id varchar(255) not null, name varchar(255), type varchar(255) check (type in ('WRAP','PROTEIN','VEGGIES','CHEESE','SAUCE')), primary key (id));
create table taco (created_at timestamp(6), id bigint not null, name varchar(255) not null, primary key (id));
create table taco_ingredients (taco_id bigint not null, ingredients_id varchar(255) not null);
create table taco_order (delivery_state varchar(2), id bigint not null, placed_at timestamp(6), cc_expiration varchar(255), cc_number varchar(255), cccvv varchar(255), delivery_city varchar(255), delivery_name varchar(255), delivery_street varchar(255), delivery_zip varchar(255), primary key (id));
create table taco_order_tacos (taco_order_id bigint not null, tacos_id bigint not null unique);
alter table if exists taco_ingredients add constraint FK7y679y77n5e75s3ss1v7ff14j foreign key (ingredients_id) references ingredient;
alter table if exists taco_ingredients add constraint FK27rycuh3mjaepnba0j6m8xl4q foreign key (taco_id) references taco;
alter table if exists taco_order_tacos add constraint FKfwvqtnjfview9e5f7bfqtd1ns foreign key (tacos_id) references taco;
alter table if exists taco_order_tacos add constraint FKs8yteduju5tndbivxbmdrbsyy foreign key (taco_order_id) references taco_order;
create sequence taco_order_seq start with 1 increment by 50;
create sequence taco_seq start with 1 increment by 50;
create table ingredient (id varchar(255) not null, name varchar(255), type varchar(255) check (type in ('WRAP','PROTEIN','VEGGIES','CHEESE','SAUCE')), primary key (id));
create table taco (created_at timestamp(6), id bigint not null, name varchar(255) not null, primary key (id));
create table taco_ingredients (taco_id bigint not null, ingredients_id varchar(255) not null);
create table taco_order (delivery_state varchar(2), id bigint not null, placed_at timestamp(6), cc_expiration varchar(255), cc_number varchar(255), cccvv varchar(255), delivery_city varchar(255), delivery_name varchar(255), delivery_street varchar(255), delivery_zip varchar(255), primary key (id));
create table taco_order_tacos (taco_order_id bigint not null, tacos_id bigint not null unique);
alter table if exists taco_ingredients add constraint FK7y679y77n5e75s3ss1v7ff14j foreign key (ingredients_id) references ingredient;
alter table if exists taco_ingredients add constraint FK27rycuh3mjaepnba0j6m8xl4q foreign key (taco_id) references taco;
alter table if exists taco_order_tacos add constraint FKfwvqtnjfview9e5f7bfqtd1ns foreign key (tacos_id) references taco;
alter table if exists taco_order_tacos add constraint FKs8yteduju5tndbivxbmdrbsyy foreign key (taco_order_id) references taco_order;
