# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table messages (
  id                            bigint auto_increment not null,
  text                          varchar(255) not null,
  user_id                       bigint,
  description                   varchar(255) not null,
  title                         varchar(255) not null,
  speciality                    varchar(255),
  constraint pk_messages primary key (id)
);

create table usr (
  id                            bigint auto_increment not null,
  username                      varchar(255) not null,
  password                      varchar(255) not null,
  email                         varchar(255),
  constraint pk_usr primary key (id)
);

alter table messages add constraint fk_messages_user_id foreign key (user_id) references usr (id) on delete restrict on update restrict;
create index ix_messages_user_id on messages (user_id);


# --- !Downs

alter table messages drop foreign key fk_messages_user_id;
drop index ix_messages_user_id on messages;

drop table if exists messages;

drop table if exists usr;

