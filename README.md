# EventsApp

## Martin Bombala, Richard Bubr�n

### Zadanie

Vo vami zvolenom prostred� vytvorte datab�zov� aplik�ciu, ktor� komplexne rie�i ni��ie definovan� scen�re vo vami zvolenej dom�ne. Presn� rozsah a konkretiz�ciu scen�rov si dohodnete s Va�im cvi�iacim na cvi�en�. Projekt sa rie�i vo dvojiciach, pri�om sa o�ak�va, �e na synchroniz�ciu pr�ce so spolu�iakom / spolu�ia�kou pou�ijete **git**.

### Opis dom�ny 

Vytvorili sme aplik�ciu Eventsapp, ktor� sl��i pre pou�ivate�ov, ktor� si vedia v aplik�ci� vytvori� svoj vlastn� ��et a n�sledne sa do nej prihl�si�. Po prihl�sen� do aplik�cie sa pou��vate�om zobrazuj� posty eventov, ktor� sleduj�. Pou��vate� m��e na jednotliv� posty zada� ak� m� na nich n�zor, �i u� kladn�, alebo z�porn�. Pou��vate� si m��e v aplik�ci� vyhlada� eventy, pod�a krit�ri�, ktor� si s�m nastav� a n�sledne m��e zvoli� mo�nos�, aby sledoval pr�spevky pre dan� udalosti. Pou��vate� m� mo�nos� manipul�cie  so svoj�m vlastn�m profilom, �o znamen�, �e si m��e svoj profil kedyko�vek upravi� alebo vymaza�.

### Popis scen�rov

Po spusten� aplik�cie sa na obrazovke zobrazia dve pol��ka a dva tla�idl�. Prv� pol��ko je pre zadanie nicknamu v podobe mailu, a druh� pre password. Po vyplnen� vstupn�ch �dajov pou��vate� stla�� tla�idlo Login. 

**1.Login**

Tla�idlo Login spust� pr�kaz ```"SELECT * FROM \"user\" WHERE email LIKE '" + username + "' AND password LIKE '" + password + "'"``` , ktor� zist� �i sa v datab�ze nach�dza dan� pou�ivate� s dan�m heslom. Ak �no obrazovka sa presunie do hlavn�ho okna, inak sa zobraz� oznam o tom, �e proces Login neprebehol �spe�ne.

![Login](./Pictures/login.jpg)

**2.Registration**

Na obrazovke po spusten� aplik�cie sa tak isto nach�dza tla�idlo Registration. Po stla�en� tohto tla�idla sa presunie obrazovka do �a��ieho okna, kde m� pou��vate� mo�nos� sa zaregistrova� (vytvori� nov�ho usera). V tomto okne vypln� pu��va�e� potrebn� �daje sa stla�� tla�idlo Register, ktor� spust� pr�kaz ```"INSERT INTO public.user (name, surname, password, email, sex) VALUES" + " ('" + name + "', '" + surname + "', '" + password + "', '" + email + "', " + sex + ");"``` , ktor� vlo�� pou��vate�a do datab�zy.
	
![Registration](./Pictures/registration.jpg)	
	
**3.Home** 

Po �spe�nom logine sa zobraz� hlavn� okno, v ktorom sa vykresluje na �avej strane obrazovky navigation bar, ktor� zobrazuje tla�idl� home, profil, events a logout a na pravej strane obrazovky sa zobrazuj� posty eventov, ktor� m� pou��vate� lajknut�. Toto zobrazenie postov zabezpe�uje pr�kaz ```"SELECT p.*, e.name, coalesce(sss.opinion, 0) AS opinion, coalesce(sub.like_count, 0) AS like_count FROM \"user\" AS u JOIN event_like AS el ON u.id = el.user_id JOIN event AS e" + " JOIN post AS p On p.event_id = e.id" + " ON e.id = el.event_id" + " LEFT JOIN (SELECT p.opinion, p.post_id FROM \"user\" AS u JOIN posts_like AS p ON u.id = p.user_id WHERE u.id = " + userId + " ) AS sss" + " ON sss.post_id = p.id LEFT JOIN (SELECT p.post_id, SUM(p.opinion) AS like_count FROM posts_like AS p GROUP BY p.post_id) AS sub" + " ON sub.post_id = p.id" + " WHERE u.id = " + userId + " ORDER BY p.id " + "LIMIT 3 OFFSET " + actualPosition*3;``` , ktor� sa star� aj o str�nkovanie. Toto hlavn� okno sa zobraz� pou�ivate�ovi aj po stla�en� tla�idla Home.
Na to aby pou��vate� mohol prv� kr�t lajkn�� post vyu��vame pr�kaz ```"INSERT INTO posts_like (user_id, post_id, opinion) VALUES" + "  (" + getUserId() + "," + postId + "," + opinion + ");"``` . Ak u� pu��vate� m� na post zadan� opinion a chce ho zmeni� vyu��va sa pr�kaz ```UPDATE posts_like " + "SET opinion = " + opinion + " " + "WHERE user_id = " + getUserId() + " AND " + "post_id = " + postId + ";"```. Na zistenie, �i u� pou��vate� lajkol nejak� post vyu��vame pr�kaz ```"SELECT * FROM posts_like WHERE user_id = " + getUserId() + " AND post_id = " + postId + ";"```
	
![Home](./Pictures/posty.jpg)	
	
**4.Profil**

Po stla�en� tla�idl� Profile sa pou��vate�ovi zobraz� okno, v ktorom je mo�n� meni� pou�ivate�sk� �daje. Najprv sa mu uk�� p�vodn� osobn� �daje. Tieto �daje m��e zmeni� a zmenu zaznamen� tla�idlom Submit, ktor� sp���a pr�kaz ```"UPDATE \"user\" " + "SET name = '"+name+"', " + "    surname = '"+surname+"', " + "    sex = "+sex+" " + "WHERE id = "+Data.getInstance().getUser().getId()+";"``` . V okne, ktor� sa zobraz� po stla�en� tla�idla Profile, je tak isto �a��ie tla�idlo Delete, ktor� zabezpe�uje vymazanie pou��vate�a z datab�zy prostredn�ctvom pr�kazu ```"DELETE FROM \"user\" " + "WHERE id = "+Data.getInstance().getUser().getId()+";"```

![Profil](./Pictures/profil.jpg)

**5.Event**

Po stla�en� tla�idla Events, sa pou��vate�ovi zobraz� okno, v ktorom si m��e vyfiltrova� a zobrazi� eventy pod�a svojich predst�v. Eventu sa filtruj� pomocou pr�kazu 
		
		queryBuilder = new StringBuilder("SELECT * FROM event WHERE ");
        if(name != null && !name.isEmpty()) {
            queryBuilder.append("lower(name) LIKE LOWER('").append(name).append("%') AND ");
        }
 
        if(country != null && !country.isEmpty()) {
            queryBuilder.append("lower(country) LIKE lower('").append(country).append("%') AND ");
        }
 
        if(city != null && !city.isEmpty()) {
            queryBuilder.append("lower(city) LIKE LOWER('").append(city).append("%') AND ");
        }
 
        queryBuilder.append("ticket_price < ").append(to).append(" AND ticket_price > ").append(from);
 
        queryBuilder.append(" LIMIT 3 OFFSET ").append(actualPosition*3);

Pou��vate� m� mo�nos� za�a� sledova� posty vyhladan�ho eventu prostredn�ctvom pr�kazu ```"INSERT INTO event_like (user_id, event_id, opinion) VALUES ("+Data.getInstance().getUser().getId()+","+id+",1);"```

![Event](./Pictures/eventy.jpg)

### Data Model
	
![Alternative Text](./Pictures/DataModel.png)

#### event
Tabu�ka event obsahuje jednotliv� eventy
#### event_like
Tabu�ka event_like je v�zobn� tabu�ka, pre zaznamen�vanie, ktor� eventy lajkli jednotliv� pou�ivatelia
#### post
Tabu�ka post obsahuje jednotliv� posty
#### post_like
Tabu�ka post_like je v�zobn� tabu�ka, pre zaznamen�vanie, ktor� posty lajkli jednotliv� pou�ivatelia
#### user
Tabu�ka user obsahuje jednotliv�ch userov
#### artist
Tabu�ka artist obsahuje jednotliv�ch umelcov
#### artist_like
Tabu�ka artist_like je v�zobn� tabu�ka, pre zaznamen�vanie, ktor�ch umelcov lajkli jednotliv� pou�ivatelia
#### concert
Tabu�ka concert obsahuje jednotliv� koncerty
#### style
Tabu�ka concert obsahuje jednotliv� �t�ly
#### artist_style
Tabu�ka artist_style je v�zobn� tabu�ka, pre zaznamen�vanie, ak� �t�ly preferuj� jednotliv� umelci
#### ticket
Tabu�ka concert obsahuje jednotliv� l�stky

### Optimaliz�cia

Aby aplik�cia prebiehala v �o najoptim�lnej�om �ase aj ke� tabu�ky obsahuj� obrovsk� mno�stvo d�t, sme vytvorili indexy pre vyh�ad�vanie d�t v tabu�ke:

	CREATE INDEX login_index ON "user" (email, PASSWORD)
	CREATE INDEX event_filter_index ON event (LOWER(NAME) VARCHAR_PATTERN_OPS, LOWER(country) VARCHAR_PATTERN_OPS, LOWER(city) VARCHAR_PATTERN_OPS, ticket_price)
	CREATE INDEX event_filter_index_ticket ON event (ticket_price);
	CREATE INDEX event_filter_index_name_ticket ON event (LOWER(NAME) VARCHAR_PATTERN_OPS, ticket_price);
	CREATE INDEX event_filter_index_country_ticket ON event (LOWER(country) VARCHAR_PATTERN_OPS, ticket_price);
	CREATE INDEX event_filter_index_city_ticket ON event (LOWER(city) VARCHAR_PATTERN_OPS, ticket_price);
	CREATE INDEX event_filter_index_name_country_ticket ON event (LOWER(NAME) VARCHAR_PATTERN_OPS, LOWER(country) VARCHAR_PATTERN_OPS, ticket_price);
	CREATE INDEX event_filter_index_name_city_ticket ON event (LOWER(NAME) VARCHAR_PATTERN_OPS, LOWER(city) VARCHAR_PATTERN_OPS, ticket_price);
	CREATE INDEX event_filter_index_country_city_ticket ON event (LOWER(country) VARCHAR_PATTERN_OPS, LOWER(city) VARCHAR_PATTERN_OPS, ticket_price);

### Technick� dokument�cia

Program je naprogramovan� v Jave a na spojenie s datab�zou sme pou�ili jdbc connector, datab�zu sme rie�ili cez PostgreSQL. 

 


