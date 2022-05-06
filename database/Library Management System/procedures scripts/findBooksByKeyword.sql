drop procedure if exists lms.findBooksByKeyword;

delimiter //
create procedure lms.findBooksByKeyword(IN keyword varchar(64))
begin
    SELECT *
	FROM books
	WHERE
		ISBN LIKE keyword 
        OR 
        information LIKE keyword 
		OR 
		title LIKE keyword ;
end //
delimiter ;