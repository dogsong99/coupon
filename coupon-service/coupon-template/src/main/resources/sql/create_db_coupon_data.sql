-- 登录 MySQL 服务器
mysql -h 127.0.0.1 -u root -p 12345678

-- 创建数据库 coupon_data
CREATE DATABASE IF NOT EXISTS coupon_data;

-- 登录 MySQL 服务器, 并进入到 imooc_coupon_data 数据库中
mysql -h localhost -u root -p Djangobbs -D coupon_data
