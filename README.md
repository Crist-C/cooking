# Cooking
This application was built as a technical test for the Mobile Developer position. It consists of a small application that allows you to view cooking recipes.  Used technology:Dagger-Hilt Room Retrofit CleanCode MVVM Jetpack Compose Compose Navigation Coroutines JUnit

Esta aplicación es el resultado de la prueba técnica para el cargo de Desarrollador Android. Consiste en una pequeña aplicación que permite al usuario ver una lista de recetas, ver el detalle de la receta en otra pantalla y finalmente ver la ubicación del origen de esta en un mapa de google. Está construida siguiendo buenas prácticas de desarrollo, incluyendo los principios SOLID y la arquitectura MVVM. Se utilizan varias tecnologías y librerías de Android para garantizar un rendimiento óptimo y una agradable experiencia de usuario.

## Arquitectura
La aplicación sigue una arquitectura basada en capas que garantiza una separación de responsabilidades y una fácil escalabilidad. Las capas principales son:

## 1. Capa de Datos (Data Layer)
En esta capa, se gestionan las fuentes de datos, tanto locales como remotas. Se utiliza la biblioteca Room para la persistencia local y Retrofit para la comunicación con la API.

* Modelos de Datos (DTO): Representan la estructura de los datos provenientes de la API.
* Entidades (Entities): Modelos de datos utilizados para la persistencia en la base de datos local.
* Mappers: Transforman los modelos de datos entre las diferentes capas (DTO <-> Entity <-> Domain Model).
  
## 2. Capa de Dominio (Domain Layer)
Aquí se definen las reglas de negocio y la lógica de la aplicación. Se utilizan modelos de dominio para representar los objetos y las operaciones que se realizan sobre ellos.

## 3. Capa de Presentación (Presentation Layer)
Esta capa se encarga de la interfaz de usuario y la interacción con el usuario. Se emplea el patrón de diseño MVVM y Jetpack Compose para la creación de la UI. La navegación entre pantallas se realiza mediante Compose Navigation y se utilizan Sealed Classes para el paso de parámetros.

* Componentes Genéricos: Se han separado los componentes genéricos (botones, textos, iconos, imágenes) de los específicos de la aplicación, lo que facilita su reutilización en cualquier parte de la aplicación.
* Componentes específicos: Son los componentes reutilizables y específicos para esta app cómo la vista de una Receta.
  
## 4. Inyección de Dependencias (Dependency Injection)
Se utiliza Dagger o Hilt para la gestión de dependencias. Se separa por módulos según qué capa consume las dependencias, con módulos específicos para Data y Domain.

## 5. Capa Core (Constantes Transversales)
Aquí se definen constantes transversales que son utilizadas en toda la aplicación.

## Tecnologías y Librerías
* ROOM: Para la persistencia local de datos.
* Retrofit: Para la comunicación con la API.
* Jetpack Compose: Para la creación de la interfaz de usuario.
* Compose Navigation: Para la navegación entre pantallas.
* Coroutines: Para la gestión de operaciones asíncronas.
* JUnit y Truth: Para pruebas unitarias.
* Coil: Para la carga eficiente de imágenes.
* Gson: Para la serialización y deserialización de objetos JSON.
* Google Maps: Para la integración de mapas y localización.
  
## Usabilidad
La aplicación se ha diseñado pensando en una experiencia de usuario óptima. Se ha seccionado de la siguiente manera según el requimiento de la prueba:

## Pantalla de Inicio (Home)
### 1. Lista de Recetas:
Al iniciar la aplicación, el usuario será dirigido a la pantalla de Home, donde encontrará una lista de recetas de comida.

### 2. Búsqueda de Recetas:
El usuario dispone de un campo de texto para buscar dentro de los elementos de la lista. Puede buscar por nombre, ingredientes, país, región o favoritos. Si no selecciona ningún campo, la búsqueda se realizará en todos los campos.

### 3. Opciones de Búsqueda:
El usuario puede seleccionar los campos por los cuales desea realizar la búsqueda, incluyendo nombre, ingredientes, país, región o favoritos.

### 4. Favoritos:
Cada receta cuenta con un icono en forma de corazón que permite al usuario marcarla como favorita.

### 5. Información de la Receta:

Cada elemento de la lista incluye:
Nombre de la receta.
Tres imágenes de la receta.
Descripción de la receta: Puede expandirse o contraerse con una animación de debounce al hacer clic.

### 6. Ubicación:

Contiene la imagen de la bandera del país, nombre del país, nombre de la región y un icono de ubicación. Al hacer clic, lleva al usuario a la pantalla de Maps mostrando la ubicación de la región de la receta.

### 7. Detalle de la Receta:

Un botón que lleva al usuario a la pantalla de detalle, donde se proporciona más información sobre la receta.

## Pantalla de Detalle

### 1. Carrusel de Imágenes:
Presenta una secuencia de imágenes con un indicador de posición.

### 2. Ubicación:
Igual que en la pantalla de Home, proporciona información sobre la ubicación de la receta. Al hacer clic, lleva al usuario a la pantalla de Maps.

### 3. Lista de Ingredientes:
El usuario puede marcar los ingredientes necesarios para la receta, con un indicador de cuántos elementos del total se han marcado.

### 4. Preparación:
Mediante un viewPager, se guía al usuario a través del procedimiento para la preparación de la receta.

## Pantalla de Mapa

### 1. Mapa de la Región:
Muestra la ubicación en un mapa de la región a la que pertenece la receta.

### 2. Barra Superior:
Contiene el nombre y detalles de la ubicación.

## Estilos
Se ha definido una guía de estilos para la tipografía y los colores, adaptada tanto a temas claros como oscuros y a diferentes tamaños de pantalla.

## Consideraciones Finales
Este proyecto se ha desarrollado siguiendo las mejores prácticas de la industria, lo que garantiza un código limpio, mantenible y escalable. Se ha prestado especial atención a la usabilidad y la experiencia del usuario, para proporcionar una aplicación intuitiva y eficiente.

¡Esperamos que disfrutes explorando la aplicación! Si tienes alguna pregunta o sugerencia, no dudes en comentarmela 😊.
