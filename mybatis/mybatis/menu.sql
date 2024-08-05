DROP TABLE IF EXISTS menu;
CREATE TABLE menu(
    id integer PRIMARY KEY AUTOINCREMENT,
    item text,
    price integer,
    quantity integer
);

INSERT INTO menu(item, price)
VALUES (#{item},#{price});

UPDATE menu
SET item=#{item}, price=#{price}
WHERE id= #{id};

SELECT * FROM menu
WHERE id=#{id};

DELETE FROM menu
WHERE id = #{id};

UPDATE menu
SET quantity=10
WHERE id= 1;