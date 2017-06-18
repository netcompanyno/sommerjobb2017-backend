# Backdend

## Oppsett

### Database
1. Installer postgresql
2. `psql postgres`
3. `CREATE USER sommerjobber WITH PASSWORD 'secret'`
4. `CREATE DATABASE sommerjobb2017 OWNER sommerjobber`
5. `CREATE USER oauth WITH PASSWORD 'secret'`
6. `CREATE DATABASE oauth OWNER oauth`

### Installasjon
1. `mvn clean install`
2. `java -jar target/sommerjobb2017.jar`
