# ML Searcher

Librería de Java para hacer búsquedas utilizando la API de Mercado Libre

## Documentación

La librería contiene dos clases, la clase MLSearcher para buscar artículos en Mercado Libre y la clase MLSite para obtener los sitios (paises) disponibles.

### MLSearcher

#### Métodos estáticos

```
static String stringListToString(List<String> stringList)
```
Convierte una lista de strings en una string

Parameters:

    stringList - Lista de strings a convertir

Returns:

    String

```
static List<String> stringToStringList(String string)
```
Convierte una string en una lista de strings

Parameters:
    string - La string a convertir
Returns:
    Lista de strings

#### Métodos

```
void setSiteId(String siteId)
```
Setea el id del sitio donde se hará la búsqueda: MLA, MLB, etc.

Parameters:
    siteId - Id del sitio

```
void setAgent(String agent)
```
Setea el agente HTTP

Parameters:
    agent - Nombre del agente HTTP
    Por defecto es MLSearcher

```
void 	setWords(List<String> wordList) 	
```
Setea las palabras a buscar

Parameters:
    wordList - Lista de palabras

```
void setMaxResultCount(int maxResultCount)
```
Setea la cantidad máxima de resultados que se exploran en la búsqueda

Parameters:
    maxResultCount - Cantidad máxima de resultados
    El valor por defecto es 1000, que es la cantidad máxima definida por ML
Throws:
    Exception - Si maxResultCount es mayor a 1000

```
void setFiltered(boolean filtered)
```
Setea si se hace un filtrado para que las palabras estén contenidas dentro del título + marca + modelo

Parameters:
    filtered - true para filtrar, false para no filtrar
    Por defecto es true

```
int getResultCount()
```
Consulta la cantidad de resultados que produce la búsqueda

Returns:
    La cantidad de resultados
Throws:
    Exception - Si falla la consulta
    
```
void searchItems()
```
Realiza la búsqueda

Throws:
    Exception - Si falla la búsqueda o el id del sitio no fue especificado

```
List<Map<String,String>> getFoundItems()
```
Obtiene la lista de artículos encontrados

Returns:
    Lista de artículos de tipo Map
