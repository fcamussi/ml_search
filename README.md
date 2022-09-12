# ML Searcher

Librería de Java para hacer búsquedas utilizando la API de Mercado Libre

## Documentación

La librería contiene dos clases principales, la clase **MLSearcher** para buscar artículos en Mercado Libre y la clase **MLSite** para obtener los sitios (paises) disponibles.

### MLSearcher

#### Métodos estáticos

```
static String stringListToString(List<String> stringList)
```
Convierte una lista de strings en una string

*Parámetros*: stringList - Lista de strings a convertir

Retorna: String


```
static List<String> stringToStringList(String string)
```
Convierte una string en una lista de strings

Parámetros: string - La string a convertir

Retorna: Lista de strings


#### Métodos

```
void setSiteId(String siteId)
```
Setea el id del sitio donde se hará la búsqueda: MLA, MLB, etc.

Parámetros: siteId - Id del sitio


```
void setAgent(String agent)
```
Setea el agente HTTP

Parámetros: agent - Nombre del agente HTTP. Por defecto es MLSearcher


```
void 	setWords(List<String> wordList) 	
```
Setea las palabras a buscar

Parámetros: wordList - Lista de palabras


```
void setMaxResultCount(int maxResultCount)
```
Setea la cantidad máxima de resultados que se exploran en la búsqueda

Parámetros: maxResultCount - Cantidad máxima de resultados. El valor por defecto es 1000, que es la cantidad máxima definida por ML

Throws: Exception - Si maxResultCount es mayor a 1000


```
void setFiltered(boolean filtered)
```
Setea si se hace un filtrado para que las palabras estén contenidas dentro del título + marca + modelo

Parámetros: filtered - true para filtrar, false para no filtrar. Por defecto es true


```
int getResultCount()
```
Consulta la cantidad de resultados que produce la búsqueda

Retorna: La cantidad de resultados

Throws: Exception - Si falla la consulta

    
```
void searchItems()
```
Realiza la búsqueda

Throws: Exception - Si falla la búsqueda o el id del sitio no fue especificado


```
List<Map<String,String>> getFoundItems()
```
Obtiene la lista de artículos encontrados

Retorna: Lista de artículos de tipo Map


### MLSites

#### Métodos

```
public void setAgent(String agent)
```
Setea el agente HTTP

Parámetros: agent - Nombre del agente HTTP. Por defecto es MLSearcher


```
public void request()
```
Consulta y almacena los sitios

Throws: Exception - Si falla la consulta


```
public Map<String,String> getSites()
```
Obtiene los sitios

Retorna: Identificadores y nombres de los sitios


### Ejemplo

El fichero Example.java es un ejemplo de como usar la librería.

![screenshot1](https://user-images.githubusercontent.com/75378876/189576979-17821d38-ef4e-4f5b-acc1-49bdbc5eab78.png)
![screenshot2](https://user-images.githubusercontent.com/75378876/189576981-d8d8626f-cfa4-4d8d-ae1e-44cebb7f5b14.png)
