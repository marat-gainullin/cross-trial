drop table Subscription
create table Subscription (id number primary key, amount number, description varchar(255))

-- alter table Transfer add foreign key (fromCross) references Subscription(crossId)
-- alter table Transfer add foreign key (toCross) references Subscription(crossId)

insert into Subscription(id, amount, description) values (15800002589632588, 5000000, 'Subscription of Pizza Hutt')
insert into Subscription(id, amount, description) values (25800002589632588, 3000000, 'Subscription of CIA')
insert into Subscription(id, amount, description) values (35800002589632588, 2000000, 'Jurassic park subscription')
insert into Subscription(id, amount, description) values (45800002589632588, 1000000, 'University subscription')
insert into Subscription(id, amount, description) values (55800002589632588, 900000, 'Inter subscription')
insert into Subscription(id, amount, description) values (65800002589632588, 800000, 'FinTech subscription')
