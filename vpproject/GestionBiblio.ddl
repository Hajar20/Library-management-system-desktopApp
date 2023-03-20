CREATE TABLE Locataire (ID int(10) NOT NULL AUTO_INCREMENT, NomPrenom varchar(255), Niveau varchar(255), Filiere varchar(255), LivreID int(10) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE Livre (ID int(10) NOT NULL AUTO_INCREMENT, Titre varchar(255), Genre varchar(255), Auteur varchar(255), Quantite int(10) NOT NULL, PRIMARY KEY (ID));
ALTER TABLE Locataire ADD INDEX FKLocataire664225 (LivreID), ADD CONSTRAINT FKLocataire664225 FOREIGN KEY (LivreID) REFERENCES Livre (ID);
