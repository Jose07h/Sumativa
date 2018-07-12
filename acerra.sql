create database made;
use made;

create table tipos(id int primary key auto_increment,descripcion varchar(30));
create table puestos(id int primary key auto_increment, puesto varchar(30));
create table direciones(id int primary key auto_increment, pueblo varchar(30),calle varchar(30));
create table productos(id int primary key auto_increment,nombre varchar(30),cantidad int,precio int,id_tipo int ,descripcion varchar(100));
create table personal(id int primary key auto_increment,nombre varchar(30),ap varchar(30),am varchar(30),edad int ,id_puesto int);
create table ventas(id int primary key auto_increment,total int, direccion varchar(50),id_persona int);
create table asigna_pro(id int primary key auto_increment, id_venta varchar(30),id_producto int);
create table p(id int primary key auto_increment,nombre varchar(30),cantidad int);
insert into tipos values(1,'madera dura');
insert into tipos values(2,'madera blanda');

insert into puestos values(700,'Encargado');
insert into puestos values(701,'Cajero');

insert into direciones values(800,'La capilla','Calle independencia');
insert into direciones values(801,'El calvario','Alfareros');
insert into direciones values(802,'Agua fria','Tierras blancas');
insert into direciones values(803,'Aan Antonio','S.Pablo');
insert into direciones values(804,'La peña','Calle del tambor');
insert into direciones values(805,'Centro','Ortigas');
insert into direciones values(806,'Santa maria','Salitre');
insert into direciones values(807,'Monte alto','Calle colibri');

INSERT INTO productos VALUES ('50', 'Pino', '45', 23,2,'El pino es considerado una madera blanda y posee una textura uniforme');
INSERT INTO productos VALUES ('52', 'Cedro', '74',234, 2,'El cedro es una madera blanda de color rojizo,  muy conocida por su olor dulce.  ');
INSERT INTO productos VALUES ('53', 'Caoba', '23',12 ,1,'Madera ligera y blanda, comparable con el pino. Peso relativamente bajo con buena resistencia y elasticidad');
INSERT INTO productos VALUES ('54', 'Roble', '76',123, 1,'Esta madera de grano fino muy resistente  tiene un hermoso color marrón rojizo.');
INSERT INTO productos VALUES ('55', 'Nogal', '23',34, 1,'El Roble esta dentro de las maderas duras, pero no posee grano fino como la caoba o cerezo.');
INSERT INTO productos VALUES ('56', 'Teca', '786',24, 1,'El nogal es una de las maderas mas duras que existen. ');
INSERT INTO productos VALUES ('57', 'Olivo', '45',53, 1,'El nogal es una de las maderas mas duras que existen. ');
INSERT INTO productos VALUES ('58', 'Cerezo', '563',23, 1,'El nogal es una de las maderas mas duras que existen. ');
INSERT INTO productos VALUES ('59', 'Olmo', '75',53, 1,'El nogal es una de las maderas mas duras que existen. ');

INSERT INTO personal  VALUES ('100', 'Juan ', 'Rodriguez', 'Quientero', '34',700);
INSERT INTO personal  VALUES ('101', 'Manuel', 'BARRERA', 'ROJAS', '54',701);
INSERT INTO personal  VALUES ('102', 'GUILLERMO', 'TORRES', 'VILLEGAS', '26',701);
INSERT INTO personal  VALUES ('103', 'ARMANDO', 'DIAZ ', 'CARRILLO ', '25',701);
INSERT INTO personal  VALUES ('104', 'HECTOR', 'SALINAS', 'VALDIVIA', '52',701);


INSERT INTO ventas  VALUES ('301', 12, '802', '102');
INSERT INTO ventas  VALUES ('302', 4 ,'802', '102');
INSERT INTO ventas  VALUES ('303' ,34, '803', '102');


insert into asigna_pro values(401,301,52);
insert into asigna_pro values(404,301,58);
insert into asigna_pro values(406,301,50);


DROP PROCEDURE IF EXISTs inserta_productos;
DELIMITER $
CREATE PROCEDURE inserta_productos(in nombre_e varchar(30), in cantidad_e varchar(30),precio_e int , in tipo_e varchar(30),
								   in descripcion_e varchar(30))BEGIN
DECLARE te int;
DECLARE cp int;
DECLARE cap int;
DECLARE ncap int;

set te= (select id from tipos where tipos.descripcion=tipo_e);
set cp= (select id from productos where productos.nombre=nombre_e);
set cap= (select cantidad from productos where productos.id=cp);
set ncap=cap+cantidad_e;

if	(cp is null)then
	insert into productos (nombre,cantidad,precio,id_tipo,descripcion)values (nombre_e,cantidad_e,precio_e,te,descripcion_e);
else
	update productos set cantidad=ncap where id=cp;
end if;
END $
DELIMITER ;


DROP PROCEDURE IF EXISTs actualiza_productos;
DELIMITER $
CREATE PROCEDURE actualiza_productos(in id_e int,in nombre_e varchar(30), in cantidad_e varchar(30),
										precio_e int, in tipo_e varchar(30), in descripcion_e varchar(30))BEGIN
DECLARE te int;
set te= (select id from tipos where tipos.descripcion=tipo_e);
	update productos set nombre=nombre_e, cantidad=cantidad_e,precio=precio_e,id_tipo=te, descripcion=descripcion_e where id=id_e;
END $
DELIMITER ;



DROP PROCEDURE IF EXISTs inserta_direciones;
DELIMITER $
CREATE PROCEDURE inserta_direciones(in pueblo_e varchar(30), in calle_e varchar(30), out res varchar(50))BEGIN
DECLARE cd int;
set cd= (select id from direciones where pueblo=pueblo_e and calle=calle_e);
if	(cd is null)then
	insert into direciones (pueblo,calle)values (pueblo_e,calle_e);
else 
	set res='Direccion existente';
	select res;
end if;
END $
DELIMITER ;


DROP PROCEDURE IF EXISTs actualiza_direciones;
DELIMITER $
CREATE PROCEDURE actualiza_direciones(in id_e int,in pueblo_e varchar(30), in calle_e varchar(30), out res varchar(50))BEGIN
DECLARE cd int;
set cd= (select id from direciones where pueblo=pueblo_e and calle=calle_e);
if	(cd is null)then
	update direciones set pueblo=pueblo_e,calle=calle_e where id=id_e;
else 
	set res='Direccion existente';
	select res;
end if;
END $
DELIMITER ;


DROP PROCEDURE IF EXISTs inserta_puestos;
DELIMITER $
CREATE PROCEDURE inserta_puestos(in descr_e varchar(30), out res varchar(50))BEGIN
DECLARE cp int;
set cp= (select id from puestos where puesto=descr_e);
if	(cp is null)then
	insert into puestos (puesto) values(descr_e);
else 
	set res='Puesto existente';
	select res;
end if;
END $
DELIMITER ;

DROP PROCEDURE IF EXISTs actualiza_puestos;
DELIMITER $
CREATE PROCEDURE actualiza_puestos(in id_e int ,in descr_e varchar(30), out res varchar(50))BEGIN
DECLARE cp int;
set cp= (select id from puestos where puesto=descr_e);
if	(cp is null)then
	update puestos set puesto=descr_e where id=id_e;
else 
	set res='Puesto existente';
	select res;
end if;
END $
DELIMITER ;

DROP PROCEDURE IF EXISTs inserta_empleados;
DELIMITER $
CREATE PROCEDURE inserta_empleados(in nombre_e varchar(50) ,in ap_e varchar(30),in am_e varchar(30)
								  ,in edad_e int,in puesto_e varchar(30), out res varchar(50))BEGIN
DECLARE ce int;
DECLARE cp int;
set cp= (select id from puestos where puesto=puesto_e);
set ce= (select id from personal where nombre=nombre_e and ap=ap_e and am=am_e and edad=edad_e and id_puesto=cp);
if	(ce is null)then
	insert into personal (nombre,ap,am,edad,id_puesto)values(nombre_e,ap_e,am_e,edad_e,cp);
else 
	set res='Empleado existente';
	select res;
end if;
END $
DELIMITER ;


DROP PROCEDURE IF EXISTs actualiza_empleados;
DELIMITER $
CREATE PROCEDURE actualiza_empleados(in id_e int,in nombre_e varchar(50) ,in ap_e varchar(30),in am_e varchar(30)
								  ,in edad_e int,in puesto_e varchar(30), out res varchar(50))BEGIN
DECLARE ce int;
DECLARE cp int;
set cp= (select id from puestos where puesto=puesto_e);
set ce= (select id from personal where nombre=nombre_e and ap=ap_e and am=am_e and edad=edad_e and id_puesto=cp);
if	(ce is null)then
	update personal set nombre=nombre_e,ap=ap_e,am=am_e,edad=edad_e,id_puesto=cp where id=id_e;
else 
	set res='Empleado existente';
	select res;
end if;
END $
DELIMITER ;




DROP PROCEDURE IF EXISTs comprar;
DELIMITER $
CREATE  PROCEDURE comprar(in id_t int,in pro varchar(30), in can int ,out res varchar(40))BEGIN
DECLARE cp int;
DECLARE i int;
DECLARE cant int;
DECLARE ncant int;
set cant= (select cantidad from productos where nombre=pro);
set i= (select id from productos where nombre=pro);
select i;
set cp= (select precio from productos where nombre=pro);
set ncant=cant-can;
insert into asigna_pro (id_venta,id_producto)values (id_t,i);
update productos set cantidad=ncant where id=i;
set res=cp*can;
select res;
END $
DELIMITER ;

DROP PROCEDURE IF EXISTs inserta_ventas;
DELIMITER $
CREATE  PROCEDURE inserta_ventas(in tot int ,in dir varchar(30),in per varchar(40))BEGIN
DECLARE cps int;
DECLARE cd int;
set cps= (select id from personal where nombre=per);
set cd= (select  id from direciones where pueblo=dir);
insert into ventas (total,direccion,id_persona) values (tot,cd,cps);
END $
DELIMITER ;



