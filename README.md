# Backdend

## Oppsett

### Database
1. Installer postgresql
2. `psql postgres`
4. `CREATE USER sommerjobber WITH PASSWORD 'secret'`
3. `CREATE DATABASE sommerjobb2017 OWNER sommerjobber`

### Installasjon
1. `mvn clean install`
2. `java -jar target/sommerjobb2017.jar`
