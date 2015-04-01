DROP INDEX radcheck_ux01 ON radcheck;

ALTER TABLE users RENAME TO old_users;

-- create tables here

INSERT INTO users (email, password, signature, activated,
                   registered_time, activated_time, last_login_time )
SELECT r.username AS email, r.value AS password, r.username AS signature, TRUE AS activated,
       u.registered_time, u.registered_time AS activated_time, u.last_login_time
FROM radcheck r, old_users u
WHERE r.id = u.user_id;


INSERT INTO users (email, password, signature, activation_code, activated, 
                   registered_time, activated_time, last_login_time)
SELECT t.email, t.password, t.email AS signature, t.activation_code, FALSE AS activated,
       t.registered_time, '1970-01-01 00:00:00' AS activated_time, '1970-01-01 00:00:00' AS last_login_time
FROM temporary_users t
WHERE t.email NOT IN (SELECT email FROM users);


ALTER TABLE radcheck ADD UNIQUE (username, attribute);


INSERT INTO radcheck (username, attribute, op, value)
SELECT u.email AS username, 'Max-Monthly-Bandwidth' AS attribute, 
       ':=' AS op, (100 * 1024 * 1024) AS value
FROM users u WHERE u.activated = 1;

