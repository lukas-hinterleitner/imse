#!/bin/bash
mkdir -p /tmp/mysql-data
docker run --name mysql-imse --rm -v /tmp/mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=imseimse -e MYSQL_DATABASE=imse -p 33333:3306 -it mysql:8.0.31
