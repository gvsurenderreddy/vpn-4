CREATE TABLE radacct (
  radacctid bigint(21) NOT NULL auto_increment,
  acctsessionid varchar(64) NOT NULL default '',
  acctuniqueid varchar(32) NOT NULL default '',
  username varchar(64) NOT NULL default '',
  groupname varchar(64) NOT NULL default '',
  realm varchar(64) default '',
  nasipaddress varchar(15) NOT NULL default '',
  nasportid varchar(15) default NULL,
  nasporttype varchar(32) default NULL,
  acctstarttime datetime NULL default NULL,
  acctstoptime datetime NULL default NULL,
  acctsessiontime BIGINT(20) default NULL,
  acctauthentic varchar(32) default NULL,
  connectinfo_start varchar(50) default NULL,
  connectinfo_stop varchar(50) default NULL,
  acctinputoctets bigint(20) default NULL,
  acctoutputoctets bigint(20) default NULL,
  calledstationid varchar(50) NOT NULL default '',
  callingstationid varchar(50) NOT NULL default '',
  acctterminatecause varchar(32) NOT NULL default '',
  servicetype varchar(32) default NULL,
  framedprotocol varchar(32) default NULL,
  framedipaddress varchar(15) NOT NULL default '',
  acctstartdelay int(12) default NULL,
  acctstopdelay int(12) default NULL,
  xascendsessionsvrkey varchar(10) default NULL,
  PRIMARY KEY  (radacctid),
  KEY username (username),
  KEY framedipaddress (framedipaddress),
  KEY acctsessionid (acctsessionid),
  KEY acctsessiontime (acctsessiontime),
  KEY acctuniqueid (acctuniqueid),
  KEY acctstarttime (acctstarttime),
  KEY acctstoptime (acctstoptime),
  KEY nasipaddress (nasipaddress)
) ;


ALTER TABLE radacct CHANGE acctsessiontime acctsessiontime BIGINT(20);


#
# Table structure for table 'radcheck'
#
/* radcheck will be replaced by view to get info dynamically.

CREATE TABLE radcheck (
  id int(11) unsigned NOT NULL auto_increment,
  username varchar(64) NOT NULL default '',
  attribute varchar(64)  NOT NULL default '',
  op char(2) NOT NULL DEFAULT '==',
  value varchar(253) NOT NULL default '',
  PRIMARY KEY  (id),
  KEY username (username(32))
) ;

 */

#
# Table structure for table 'radgroupcheck'
#

CREATE TABLE radgroupcheck (
  id int(11) unsigned NOT NULL auto_increment,
  groupname varchar(64) NOT NULL default '',
  attribute varchar(64)  NOT NULL default '',
  op char(2) NOT NULL DEFAULT '==',
  value varchar(253)  NOT NULL default '',
  PRIMARY KEY  (id),
  KEY groupname (groupname(32))
) ;

#
# Table structure for table 'radgroupreply'
#

CREATE TABLE radgroupreply (
  id int(11) unsigned NOT NULL auto_increment,
  groupname varchar(64) NOT NULL default '',
  attribute varchar(64)  NOT NULL default '',
  op char(2) NOT NULL DEFAULT '=',
  value varchar(253)  NOT NULL default '',
  PRIMARY KEY  (id),
  KEY groupname (groupname(32))
) ;


#
# Table structure for table 'radusergroup'
#

CREATE TABLE radusergroup (
  username varchar(64) NOT NULL default '',
  groupname varchar(64) NOT NULL default '',
  priority int(11) NOT NULL default '1',
  KEY username (username(32))
) ;

#
# Table structure for table 'radpostauth'
#

CREATE TABLE radpostauth (
  id int(11) NOT NULL auto_increment,
  username varchar(64) NOT NULL default '',
  pass varchar(64) NOT NULL default '',
  reply varchar(32) NOT NULL default '',
  authdate timestamp NOT NULL,
  PRIMARY KEY  (id)
) ;



# ########################################################################### #
# Application specific schema                                                 #
# ########################################################################### #


CREATE TABLE radcheck (
  id int(11) unsigned NOT NULL,
  username varchar(64) NOT NULL,
  attribute varchar(64)  NOT NULL,
  op char(2) NOT NULL DEFAULT '==',
  value varchar(253) NOT NULL default '',
  PRIMARY KEY  (id),
  UNIQUE KEY (username, attribute)
);

CREATE TABLE radcheck_back (
  id int(11) unsigned NOT NULL,
  username varchar(64) NOT NULL,
  attribute varchar(64)  NOT NULL,
  op char(2) NOT NULL DEFAULT '==',
  value varchar(253) NOT NULL default '',
  PRIMARY KEY  (id),
  UNIQUE KEY (username, attribute)
);

CREATE TABLE radreply (
  id int(11) unsigned NOT NULL,
  username varchar(64) NOT NULL,
  attribute varchar(64) NOT NULL,
  op char(2) NOT NULL DEFAULT '=',
  value varchar(253) NOT NULL default '',
  PRIMARY KEY  (id),
  UNIQUE KEY (username, attribute)
) ;

CREATE TABLE radreply_back (
  id int(11) unsigned NOT NULL,
  username varchar(64) NOT NULL,
  attribute varchar(64) NOT NULL,
  op char(2) NOT NULL DEFAULT '=',
  value varchar(253) NOT NULL default '',
  PRIMARY KEY  (id),
  UNIQUE KEY (username, attribute)
) ;

CREATE TABLE users (
  user_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  email varchar(100) NOT NULL,
  password varchar(100) NOT NULL,
  signature varchar(100) NOT NULL,
  activated BOOLEAN,
  activation_code varchar(100),
  registered_time timestamp,
  activated_time timestamp,
  last_login_time timestamp,
  referral int(11),
  PRIMARY KEY (`user_id`),
  UNIQUE KEY (signature),
  UNIQUE KEY (email)
);


ALTER TABLE users ADD blocked BOOLEAN DEFAULT 0;
DROP INDEX users_ix01 ON users;

CREATE INDEX users_ix01 ON users (activated, blocked);
CREATE INDEX users_ix02 ON users (activated, activated_time);
CREATE INDEX users_ix03 ON users (referral);


CREATE TABLE user_roles (
  user_id int(11) unsigned NOT NULL,
  role_name varchar(100) NOT NULL,
  PRIMARY KEY (user_id, role_name)
);

CREATE INDEX user_roles_ix01 ON user_roles (user_id);


CREATE TABLE user_logs (
  log_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  user_id int(11) unsigned NOT NULL,
  log_time timestamp NOT NULL,
  log_type varchar(100),
  message varchar(200),
  PRIMARY KEY (`log_id`),
  FOREIGN KEY (user_id) REFERENCES `users`(user_id)
);

CREATE INDEX user_logs_ix01 ON user_logs (user_id);

CREATE TABLE products (
  product_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  duration int(11) NOT NULL,
  bandwidth int(11) NOT NULL,
  price double,
  available boolean,
  description varchar(500),
  PRIMARY KEY (`product_id`),
  UNIQUE KEY (duration, bandwidth)
);


-- cannot create foreign key constraints due to mysql limitation
CREATE TABLE purchases (
  purchase_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  user_id int(11) unsigned NOT NULL,
  product_id int(11) NOT NULL,
  purchase_time timestamp NOT NULL,
  bandwidth int(11) NOT NULL,
  duration int(11) NOT NULL,
  price double NOT NULL,
  PRIMARY KEY (`purchase_id`)
--  FOREIGN KEY (user_id) REFERENCES `users`(user_id),
--  FOREIGN KEY (product_id) REFERENCES `products`(product_id)
);

CREATE INDEX purchases_ix01 ON purchases (purchase_time);
CREATE INDEX purchases_ix02 ON purchases (duration);
CREATE INDEX purchases_ix03 ON purchases (user_id);
CREATE INDEX purchases_ix04 ON purchases (product_id);

CREATE TABLE usages (
  usage_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  user_id int(11) unsigned NOT NULL,
  year int(11) unsigned NOT NULL,
  month int(11) unsigned NOT NULL,
  session_time bigint(20),
  upload bigint(20),
  download bigint(20),
  PRIMARY KEY (`usage_id`)
--  FOREIGN KEY (user_id) REFERENCES `users`(user_id)
); 

CREATE INDEX usages_ix01 ON usages (user_id);

CREATE TABLE preferences (
  pref_name varchar(100) NOT NULL,
  pref_value varchar(1000),
  PRIMARY KEY (`pref_name`)
);

INSERT INTO preferences (pref_name, pref_value) VALUES ('free-bandwidth', 100000);
INSERT INTO preferences (pref_name, pref_value) VALUES ('referral-level', 2);
INSERT INTO preferences (pref_name, pref_value) VALUES ('referral-bonus', 100);
INSERT INTO preferences (pref_name, pref_value) VALUES ('referral-max', 50);
INSERT INTO preferences (pref_name, pref_value) VALUES ('free-limit-from', '7');
INSERT INTO preferences (pref_name, pref_value) VALUES ('free-limit-to', '9');


CREATE TABLE usage_details (
  account_id bigint(21) NOT NULL,
  acctsessionid varchar(64) NOT NULL default '',
  acctuniqueid varchar(32) NOT NULL default '',
  username varchar(64) NOT NULL default '',
  nasipaddress varchar(15) NOT NULL default '',
  nasportid varchar(15) default NULL,
  nasporttype varchar(32) default NULL,
  acctstarttime datetime NULL default NULL,
  acctstoptime datetime NULL default NULL,
  acctsessiontime BIGINT(20) default NULL,
  acctauthentic varchar(32) default NULL,
  acctinputoctets bigint(20) default NULL,
  acctoutputoctets bigint(20) default NULL,
  calledstationid varchar(50) NOT NULL default '',
  callingstationid varchar(50) NOT NULL default '',
  acctterminatecause varchar(32) NOT NULL default '',
  framedipaddress varchar(15) NOT NULL default '',
  PRIMARY KEY  (account_id),
  KEY username (username),
  KEY framedipaddress (framedipaddress),
  KEY acctsessiontime (acctsessiontime),
  KEY acctstarttime (acctstarttime),
  KEY acctstoptime (acctstoptime),
  KEY nasipaddress (nasipaddress)
);


CREATE TABLE black_lists (
  pattern varchar(100) NOT NULL,
  PRIMARY KEY (pattern)
);


CREATE TABLE custom_emails (
  email varchar(100) NOT NULL,
  PRIMARY KEY (`email`)
);


CREATE TABLE recommendations (
  recommend_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  user_id int(11) unsigned NOT NULL,
  url varchar(300) NOT NULL,
  description varchar(500),
  posted_time timestamp,
  PRIMARY KEY (`recommend_id`),
  KEY user_id (user_id)
);


CREATE TABLE recommendation_likes (
  recommend_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  user_id int(11) unsigned NOT NULL,
  is_like boolean NOT NULL,
  PRIMARY KEY (`recommend_id`, `user_id`)
);


CREATE TABLE servers (
  server_id int(11) unsigned NOT NULL AUTO_INCREMENT,
  server_ip varchar(20) NOT NULL,
  for_public boolean NOT NULL,
  for_registered boolean NOT NULL,
  for_purchased boolean NOT NULL,
  description varchar(1000),
  PRIMARY KEY (`server_id`)
);

INSERT INTO servers (server_ip, for_public, for_registered, for_purchased, description) 
VALUES ('216.218.196.193', false, true, true, '支持AnyConnect 和其他任何方式 VPN');
INSERT INTO servers (server_ip, for_public, for_registered, for_purchased, description) 
VALUES ('64.71.190.197', false, false, true, '支持AnyConnect 和其他任何方式 VPN');
INSERT INTO servers (server_ip, for_public, for_registered, for_purchased, description) 
VALUES ('65.49.44.149', false, false, true, '支持AnyConnect 和其他任何方式 VPN');
INSERT INTO servers (server_ip, for_public, for_registered, for_purchased, description) 
VALUES ('66.220.20.149', false, false, true, '不知支持Anyconnect');


/* ==========================================================================
 * Views
 * ========================================================================== */


CREATE OR REPLACE VIEW check_password AS
SELECT u.email AS username, 'Cleartext-Password' AS attribute, 
       ':=' AS op, u.password AS value
FROM users u
WHERE u.activated = true AND u.blocked = false;


CREATE OR REPLACE VIEW referral_effective AS 
SELECT u.user_id, u.email,
       (SELECT count(*) FROM users u2 
        WHERE u2.referral = u.user_id 
          AND u2.activated = true
          AND u2.activated_time >= DATE_ADD(now(), INTERVAL -1 YEAR)
       ) AS referrals
FROM users u 
WHERE u.activated = true AND u.blocked = false;


CREATE OR REPLACE VIEW bandwidth_bonus AS 
SELECT re.user_id, re.email,
       (SELECT cast(p.pref_value AS UNSIGNED) FROM preferences p WHERE p.pref_name = 'referral-bonus')
        *
        floor(least(re.referrals, (SELECT cast(p.pref_value AS UNSIGNED) FROM preferences p WHERE p.pref_name = 'referral-max'))
              /
              (SELECT cast(p.pref_value AS UNSIGNED) FROM preferences p WHERE p.pref_name = 'referral-level') 
             ) AS bandwidth
FROM referral_effective re
WHERE re.referrals > 0;


CREATE OR REPLACE VIEW bandwidth_free_purchased AS 
SELECT u.user_id, u.email,
       (SELECT cast(p.pref_value AS UNSIGNED)
        FROM preferences p WHERE p.pref_name = 'free-bandwidth'
       ) AS bandwidth
FROM users u, bandwidth_purchased p
WHERE u.activated = true AND u.blocked = false
  AND u.user_id = p.user_id;
  

CREATE OR REPLACE VIEW bandwidth_free_unpurchased AS 
SELECT u.user_id, u.email,
       (SELECT cast(p.pref_value AS UNSIGNED)
        FROM preferences p WHERE p.pref_name = 'free-bandwidth'
       ) AS bandwidth
FROM users u
WHERE u.activated = true AND u.blocked = false
  AND u.user_id NOT IN (SELECT user_id FROM bandwidth_purchased)
  AND (   hour(now()) <  (SELECT cast(p.pref_value AS UNSIGNED) FROM preferences p WHERE p.pref_name = 'free-limit-from')
       OR hour(now()) >= (SELECT cast(p.pref_value AS UNSIGNED) FROM preferences p WHERE p.pref_name = 'free-limit-to'));


CREATE OR REPLACE VIEW bandwidth_purchased AS 
SELECT u.user_id, u.email, sum(p.bandwidth) * 1000 AS bandwidth
FROM users u, purchases p 
WHERE u.user_id = p.user_id  AND u.activated = true AND u.blocked = false
  AND p.purchase_time >= DATE_ADD(now(), INTERVAL -1 * p.duration MONTH)
GROUP BY u.user_id, u.email;


CREATE OR REPLACE VIEW bandwidth_all AS 
  SELECT user_id, email, bandwidth, 'bonus' AS type FROM bandwidth_bonus
UNION
  SELECT user_id, email, bandwidth, 'free_purchased' AS type FROM bandwidth_free_purchased
UNION
  SELECT user_id, email, bandwidth, 'free_unpurchased' AS type FROM bandwidth_free_unpurchased
UNION
  SELECT user_id, email, bandwidth, 'purchase' AS type FROM bandwidth_purchased;
  

CREATE OR REPLACE VIEW check_all AS
    SELECT u.email AS username, 'Cleartext-Password' AS attribute, 
           ':=' AS op, u.password AS value
    FROM users u
    WHERE u.activated = true AND u.blocked = false
UNION
    SELECT b.email AS username, 'Max-Monthly-Bandwidth' AS attribute,
            ':=' AS op, sum(b.bandwidth) * 1000 AS value
    FROM bandwidth_all b
    GROUP BY b.email;


CREATE OR REPLACE VIEW stats_user_registration AS
  SELECT DATE(registered_time) AS registered_date, count(*) FROM users
  GROUP BY DATE(registered_time)
  ORDER BY DATE(registered_time) DESC;


CREATE OR REPLACE VIEW reply_timeout AS
  SELECT bp.email AS username, 'Session-Timeout' AS attribute, ':=' AS op, '14400' AS value
  FROM bandwidth_purchased bp
  UNION
  SELECT u.email AS username, 'Session-Timeout' AS attribute, ':=' AS op, '900' AS value
  FROM users u
  WHERE u.user_id NOT IN (SELECT user_id FROM bandwidth_purchased)
    AND u.activated = true AND u.blocked = false;
  

CREATE OR REPLACE VIEW reply_all AS
  SELECT rt.username, rt.attribute, rt.op, rt.value
  FROM reply_timeout rt;
  
/*
UNION
  SELECT round(rand() * 1000000000) AS id, u.email AS username, 'Acct-Interim-Interval' AS attribute, ':=' AS op, '300' AS value
  FROM users u WHERE u.activated = 1
  */;

DELIMITER $$

CREATE PROCEDURE `radius`.`make_radcheck` ()
BEGIN
	DELETE FROM radcheck_back;
    
    INSERT INTO radcheck_back (id, username, attribute, op, value)
    SELECT (@rownum:=@rownum+1) AS id, username, attribute, ':=' AS op, value 
    FROM check_all, (SELECT @rownum:=0) r;

	RENAME TABLE radcheck TO radcheck_temp;
    RENAME TABLE radcheck_back TO radcheck;
	RENAME TABLE radcheck_temp TO radcheck_back;
END

CREATE PROCEDURE `radius`.`make_radreply` ()
BEGIN
	DELETE FROM radreply_back;
    
    INSERT INTO radreply_back (id, username, attribute, op, value)
    SELECT (@rownum:=@rownum+1) AS id, username, attribute, ':=' AS op, value 
    FROM reply_all, (SELECT @rownum:=0) r;

	RENAME TABLE radreply TO radreply_temp;
    RENAME TABLE radreply_back TO radreply;
	RENAME TABLE radreply_temp TO radreply_back;
END


  
INSERT INTO products (bandwidth, duration, price, available, description)
      SELECT 2,   12, 20,  1, ''
UNION SELECT 5,   6,  25,  1, ''
UNION SELECT 5,   12, 40,  1, ''
UNION SELECT 10,  3,  24,  1, ''
UNION SELECT 10,  6,  38,  1, ''
UNION SELECT 10,  12, 60,  1, ''
UNION SELECT 20,  1,  17,  1, ''
UNION SELECT 20,  3,  40,  1, ''
UNION SELECT 20,  6,  63,  1, ''
UNION SELECT 20,  12, 100,  1, ''
UNION SELECT 50,  1,  35,  1, ''
UNION SELECT 50,  3,  80,  1, ''
UNION SELECT 50,  6,  125, 1, ''
UNION SELECT 50,  12, 200, 1, ''
UNION SELECT 100, 1,  55,  0, ''
UNION SELECT 100, 3,  125, 0, ''
UNION SELECT 100, 6,  200, 0, ''
UNION SELECT 100, 12, 320, 0, ''
;


