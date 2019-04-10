delete from CompradorEntity;
delete from ComicEntity_CompradorEntity;
delete from CompradorEntity_ComicDeseoEntity;
delete from ComicDeseoEntity

insert into CompradorEntity(id, alias, email, intereses, nombre, foto) values (100,'heavyRock','juan@hotmail.com','Musica','Juan Pedro', 'http://www.esvivir.com/uploads/eres_persona_motivada_711_26121651.jpg');
insert into CompradorEntity(id, alias, email, intereses, nombre, foto) values (200,'mariaDB','maria@yahoo.com','Juegos','Maria Davalos','');
insert into CompradorEntity(id, alias, email, intereses, nombre, foto) values (300,'lucyLane','lucia@gmail.com','Manga','Lucia Lane','');
insert into CompradorEntity(id, alias, email, intereses, nombre, foto) values (400,'tobiasForge','tobias@outlook.com','Anime','Tobias Forge','');
insert into CompradorEntity(id, alias, email, intereses, nombre, foto) values (500,'heroeSilencio','enrique@uniandes.edu.co','Heroes','Enrique Bunbury','');
insert into CompradorEntity(id, alias, email, intereses, nombre, foto) values (600,'locoAsesino','marcos@hotmail.com','Marvel','Marco Aurelio','https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Marcus_Aurelius_Glyptothek_Munich.jpg/245px-Marcus_Aurelius_Glyptothek_Munich.jpg');
insert into CompradorEntity(id, alias, email, intereses, nombre, foto) values (700,'bebecito','bebecito@hotmail.com','DC Comics','Bebecito','');
insert into CompradorEntity(id, alias, email, intereses, nombre, foto) values (800,'sherlockHolmes','sherlock@hotmail.com','Terror','Sherlock Holmes','https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Statue_of_Sherlock_Holmes_in_Edinburgh.jpg/180px-Statue_of_Sherlock_Holmes_in_Edinburgh.jpg');


insert into ComicDeseoEntity(fechaAgregado) values ("1998");

insert into ComicDeseoEntity(fechaAgregado) values ("1991");

insert into ComicDeseoEntity(fechaAgregado) values ("1876");

insert into ComicDeseoEntity(fechaAgregado) values ("1928");

insert into ComicDeseoEntity(fechaAgregado) values ("1201")
