create table order_location
(
    id        bigint auto_increment
        primary key,
    date      datetime(6) null,
    latitude  double      null,
    longitude double      null
);

create table orders
(
    id                      bigint auto_increment
        primary key,
    operator_id             bigint not null,
    end_order_location_id   bigint null,
    start_order_location_id bigint null,
    constraint FK_end_order_id
        foreign key (end_order_location_id) references order_location (id),
    constraint FK_start_order_id
        foreign key (start_order_location_id) references order_location (id)
);

create table orders_services
(
    order_id    bigint not null,
    services_id bigint not null,
    constraint FK_order_id
        foreign key (order_id) references orders (id),
    constraint FK_services_id
        foreign key (services_id) references assistances (id)
);

