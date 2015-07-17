alter table author add version number(19, 0);
update author set version=0;
alter table author modify version not null;

alter table book add version number(19, 0);
update book set version = 0;
alter table book modify version not null;