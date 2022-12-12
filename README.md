# AL 2000


[![forthebadge](http://forthebadge.com/images/badges/built-with-love.svg)](http://forthebadge.com)  

CyberVideo est une compagnie qui veut installer une machine, AL 2000, dans
des magasin de location de films afin de permettre l’automatisation de la location de
films. Les clients peuvent parcourir le catalogue de film présent dans la machine et
dans la base de données de cyber vidéo pour louer des Bluray, ou avoir
temporairement accès au film via un QR code. Le client peut louer des films soit en
tant qu’abonné, soit en tant que visiteur.

## Executer le programme principale

L'exécutable jar fourni lance le programme principal en simulant que la carte d’abonné de XXXXX est inséré dans l’AL2000. **Attention** cette exécutable ne réinitialise pas la base de données.

### Exécution des tests

Pour lancer les tests unitaires, nous passons par une IDE. **Attention** le driver *ojdbc8.jar* doit être dans le Build Path. Lancer *TestBD* permet de réinitialiser la base de données. Des tests plus poussés demandent de modifier directement la *Main*.

## Fabriqué avec

* [VisualStudioCode](https://code.visualstudio.com/) - SQL (BDD) et éditeur de texte
* [Eclips](https://www.eclipse.org/) - Java (Front-end and Back-end)

## Auteurs
* **Joan Besante** _alias_ [besantej](https://github.com/besantej)
* **Timothée Rognon** _alias_ [ar7-612](https://github.com/ar7-612)
* **Lucas Sauvayre** _alias_ [lsa2222](https://github.com/Lsa2222)
* **Noé Romain** _alias_ [IronLyzhard](https://github.com/IronLyzhard)
