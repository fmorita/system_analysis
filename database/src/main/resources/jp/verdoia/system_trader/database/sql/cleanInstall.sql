DROP TABLE IF EXISTS tmp;
DROP TABLE IF EXISTS currency;
DROP TABLE IF EXISTS currency_pair;

CREATE TABLE currency (
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY CHECK id > 0,
	name VARCHAR(5) NOT NULL CHECK LENGTH(name) > 0,
	code CHAR(3) NOT NULL CHECK LENGTH(code) = 3,
	UNIQUE(name),
	UNIQUE(code)
);

CREATE TABLE currency_pair (
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY check id > 0,
	counter INTEGER NOT NULL,
	base INTEGER NOT NULL,
	UNIQUE(counter, base),
	FOREIGN KEY (counter, base) REFERENCES currency(id, id)
);

INSERT INTO currency(name, code) VALUES
	('円', 'JPY'),
	('米ドル', 'USD'),
	('ユーロ', 'EUR'),
	('ポンド', 'GBP');

CREATE MEMORY TABLE tmp(
	counter CHAR(3),
	base CHAR(3),
	FOREIGN KEY (counter, base) REFERENCES currency(code, code)
);
INSERT INTO tmp VALUES ('USD', 'JPY'), ('EUR', 'JPY'), ('GBP', 'JPY'), ('EUR', 'USD');
INSERT INTO currency_pair(counter, base)
	SELECT counter.id, base.id FROM tmp JOIN currency AS counter ON tmp.counter = counter.code JOIN currency AS base ON tmp.base = base.code;
DROP TABLE tmp;