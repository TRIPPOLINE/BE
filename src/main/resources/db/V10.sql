alter table spot add column location POINT NULL;

update spot set location = POINT(latitude, longitude);

alter table spot modify column location POINT NOT NULL;

alter table spot add spatial index (location);