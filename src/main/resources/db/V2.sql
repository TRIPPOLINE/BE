use trip;

alter table users change column role role_id INT;

insert into role(role_id, role_name) values(1,'amdin');
insert into role(role_id, role_name) values(2,'user');

alter table users
add constraint fk_role_id
foreign key (role_id)
references role(role_id)
on update cascade;