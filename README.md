# movies

Como o teste não determina a ordenação é **case-sensitive**.

Para rodar após baixar do git:
<pre>  
mvn clean install
java -Dlogging.level.root=FATAL -Dspring.main.banner-mode=log -jar target/movies-1.0.0.jar
</pre>


**Porque o fonte está em inglês**?

O autor considera essa uma boa prática, pois não se sabe se a equipe que vai manter esse código estará no Cnadá, Israel ou Africa do Sul e o inglês é o que temos mais perto de uma língua universal.
