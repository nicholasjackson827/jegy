# Pull base image, MySQL server
FROM mysql/mysql-server:8.0.18

# Add a database
ENV MYSQL_DATABASE jegy
ENV MYSQL_ROOT_PASSWORD changeit

# Add in the schema script to run on build
COPY scripts/schema.sql /docker-entrypoint-initdb.d/